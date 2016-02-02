package com.kcb.common.sms;

import com.alibaba.fastjson.JSON;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.baidu.yun.push.model.PushMsgToTagRequest;
import com.baidu.yun.push.model.PushMsgToTagResponse;
import com.kcb.app.json.model.AndroidMessageJson;
import com.kcb.app.json.model.Aps;
import com.kcb.app.json.model.CustomContent;
import com.kcb.app.json.model.IosMessageJson;
import com.kcb.app.json.model.LightAppCtrlKeys;
import com.kcb.common.constant.Constants;
import com.wfs.common.helper.MessageHelper;
import com.wfs.common.helper.UserHelper;
import com.wfs.common.jooq.CodeEnum.PushMesType;
import com.wfs.engine.Context;
import com.wfs.model.tables.pojos.Message;
import com.wfs.model.tables.pojos.User;
import com.wfs.task.Msg;

public class PushMsgToSingleDevice {
	
	public static Msg<String> PushMsgToTag(Context context,String messageid)
            throws PushClientException,PushServerException {		
		Msg<String> rmsg = new Msg<String>();
		Message mes=MessageHelper.getMesById(context, messageid);
		if(mes.getTargetid()==null || mes.getTargetid().equals("")){
			rmsg.setResult(true);
			return rmsg;
		}
		User user = UserHelper.getUserById(context, mes.getTargetid());
		AndroidMessageJson amj = new AndroidMessageJson();
		IosMessageJson omj = new IosMessageJson();
		CustomContent cc = new CustomContent();
		LightAppCtrlKeys lack = new LightAppCtrlKeys();
		Aps aps = new Aps();
		lack.setDisplay_in_notification_bar(1);
		lack.setEnter_msg_center(0);
		cc.setContext(mes.getContext());
		cc.setUserid(mes.getTargetid());
        /*1. 创建PushKeyPair
         *用于app的合法身份认证
         *apikey和secretKey可在应用详情中获取
         */
        String apiKey = "";
        String secretKey = "";
		if(user.getDtype()==3){
			apiKey = Constants.PUSH_ANDROID_APIKEY;
	        secretKey = Constants.PUSH_ANDROID_SECRETKEY;
		}else{
			apiKey = Constants.PUSH_IOS_APIKEY;
	        secretKey = Constants.PUSH_IOS_SECRETKEY;
		}
//        apiKey = "HT8IFpXod74AicH7kA1AuS6X";
//        secretKey = "dqu6dbpbEYZkcO7qL0jfoukTKG268Xqb";
        PushKeyPair pair = new PushKeyPair(apiKey,secretKey);

        // 2. 创建BaiduPushClient，访问SDK接口
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. 注册YunLogHandler，获取本次请求的交互信息
//        pushClient.setChannelLogHandler (new YunLogHandler () {
//            @Override
//            public void onHandle (YunLogEvent event) {
//                System.out.println(event.getMessage());
//            }
//        });

        try {
        	String mess= "";
        	switch(user.getDtype()){
        		case 3:
        			amj.setTitle("通知：");
        			amj.setDescription(PushMesType.chageKeyToValue(mes.getPushtype()));
        			amj.setNotification_builder_id(1);
        			amj.setLightapp_ctrl_keys(lack);
        			amj.setOpen_type(user.getDtype());
        			amj.setNet_support(1);
        			amj.setCustom_content(cc);
        			mess = JSON.toJSONString(amj);
        			break;
        		case 4:
        			aps.setAlert(PushMesType.chageKeyToValue(mes.getPushtype()));
        			aps.setContent_available(1);
        			omj.setAps(aps);
        			omj.setCustom_content(cc);
        			omj.setLightapp_ctrl_keys(lack);
        			mess = JSON.toJSONString(omj);
        			break;
        	}
//        	System.out.print(mess);
        // 4. 设置请求参数，创建请求实例
        	PushMsgToTagRequest  request = new PushMsgToTagRequest ().
        		addTagName(user.getUsername()+String.valueOf(user.getType())).
                addMsgExpires(new Integer(3600)).   //设置消息的有效时间,单位秒,默认3600*5.
                addMessageType(1).           //设置消息类型,0表示透传消息,1表示通知,默认为0.
                addMessage(mess).
                addDeviceType(user.getDtype());      //设置设备类型，deviceType => 1 for web, 2 for pc, 
                                       //3 for android, 4 for ios, 5 for wp.
        // 5. 执行Http请求
        	PushMsgToTagResponse  response = pushClient.
            		pushMsgToTag(request);
        // 6. Http请求返回值解析
            System.out.println("msgId: " + response.getMsgId()
                    + ",sendTime: " + response.getSendTime());
            rmsg.setResult(true);
        } catch (PushClientException e) {
            //ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
            //'true' 表示抛出, 'false' 表示捕获。
            if (BaiduPushConstants.ERROROPTTYPE) { 
                throw e;
            } else {
                e.printStackTrace();
            }
            rmsg.setResult(false);
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
            rmsg.setResult(false);
        }
        return rmsg;
    }
}
