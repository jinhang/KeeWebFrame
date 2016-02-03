package com.wfs.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jooq.Record;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.context.request.async.DeferredResult;

















import com.alibaba.fastjson.JSON;
import com.baidu.mapapi.model.LatLng;
import com.kee.app.interfaces.impl.LoginService;
import com.kee.app.interfaces.impl.OnlineOdersService;
import com.kee.app.interfaces.impl.OrderCenterService;
import com.kee.app.interfaces.impl.UserCenterService;
import com.kee.app.interfaces.service.ILoginService;
import com.kee.app.interfaces.service.IOnlineOdersService;
import com.kee.app.interfaces.service.IOrderCenterService;
import com.kee.app.interfaces.service.IUserCenterService;
import com.kee.app.json.model.LocationListJson;
import com.kee.app.json.model.PoisJson;
import com.kee.app.json.model.ResultModel1;
import com.kee.app.json.model.ResultModel2;
import com.kee.app.model.OrderResult1;
import com.kee.common.constant.Constants;
import com.kee.common.sms.PushMsgToSingleDevice;
import com.wfs.engine.Context;

import cn.kee.common.engine.EngineHelper;
import cn.kee.common.helper.EvaluationHelper;
import cn.kee.common.jooq.JooqHelper;

public class TaskTest {

	//	//异步
	//	@Test
	//	public void testGetTaskdsGridByTaskid() throws Exception {
	//
	//		HashMap map = new HashMap<String, String>();
	//		map.put("taskid", "ec90b7e7e35d43b5befb0542df8f206a");
	//		for (int i = 0; i < 1000; i++) {
	//			DeferredResult<String> result = EngineHelper.excuteTask("task.getTaskdsGridByTaskid", map);
	//		}
	//		
	//		Thread.sleep(500000);
	//
	//	}
	//	
	//	//同步
	//	@Test
	//	public void testGetTaskdsGridByTaskid2() throws Exception{
	////		 com.wfs.engine.Context c = EngineHelper.getContext();
	////		 Msg<String>  a = BaseTasks.order.OrderTest(c, "ec90b7e7e35d43b5befb0542df8f206a");
	////		 System.out.println(a.getValue());
	//	}


	//	@Test
	//	public void testJson() throws Exception{
	//		ILoginService webServiceImpl= new LoginService();
	//		Msg<String> r2s=webServiceImpl.register("13858000170", "123456", "367440", 1);
	//		System.out.println(r2s.getResult());
	//	}

	//	@Test
	//	public void testJson() throws Exception{
	//		IOrderCenterService webServiceImpl= new OrderCenterService();
	//		List<OrderResult1> r2s=webServiceImpl.getOrderList("", 
	//				"", null, "", "", "dcdc8dd944b34a038d8f582b8fe19d77", null, 0, 10);
	//		System.out.println("here");
	//	}

	//	@Test
	//	public void  call(){
	//		Msg<List<LatLng>> rmsg = new Msg<List<LatLng>>();
	//		List<LatLng> lls = new ArrayList();
	//		LocationListJson mrj = new LocationListJson();
	//		try{			
	//			HttpClient hc = new HttpClient();
	////			String urlPath=Constants.HF_url;
	//			String urlPath="http://api.map.baidu.com/geodata/v3/poi/list";
	////			String urlPath="http://api.map.baidu.com/geodata/v3/column/list";
	//			urlPath +="?geotable_id=105955";
	//			urlPath +="&user_id=dcdc8dd944b34a038d8f582b8fe19d77";
	//			urlPath +="&output=json";
	//			urlPath +="&ak=X056jyXVHFNo5I1yxI5kAI27";
	////			urlPath +="&ak=X056jyXVHFNo5I1yxI5kAI27";
	//			System.out.println(urlPath);
	//			HttpMethod method = new GetMethod(urlPath);
	//			hc.executeMethod(method);
	//		     //打印服务器返回的状态
	//		    System.out.println(method.getStatusLine());
	//		    //打印返回的信息
	//		    System.out.println(method.getResponseBodyAsString());
	//		    //释放连接
	//		    method.releaseConnection();
	//		    //解析JSON
	//		    mrj=JSON.parseObject(method.getResponseBodyAsString(),LocationListJson.class);
	//		    List<PoisJson> pjs = mrj.getPois();
	//		    if(pjs!=null){
	//		    	for(int i=0;i<pjs.size();i++){
	//			    	lls.add(new LatLng(pjs.get(i).getLocation()[1],pjs.get(i).getLocation()[0]));		    	
	//			    }
	//		    	rmsg.setResult(true);
	//				rmsg.setValue(lls);
	//		    }else{
	//		    	rmsg.setResult(false);
	//		    }		   
	//		}catch(Exception e){
	//			e.printStackTrace();
	//			rmsg.setResult(false);
	//		}
	//	}

	//	@Test
	//	public void testJson() throws Exception{
	//		IUserCenterService webServiceImpl= new UserCenterService();
	////		Msg<String> msg=webServiceImpl.validateUser("1", "15968886766");
	//		Msg<String> msg=webServiceImpl.confirmUser("1", "15968886766", "600498");
	//		System.out.println("here");
	//	}

	//	@Test
	//	public void testJson() throws Exception{
	//		Context context = EngineHelper.getContext();
	//		Msg<String> msg=PushMsgToSingleDevice.PushMsgToTag(context, "e23c4ccb94464d40bb5bfd01d2d5e685");
	//		System.out.println(msg.getResult());
	//	}

	//	@Test
	//	public void testJson() throws Exception{
	//		Context context = EngineHelper.getContext();
	//		
	//		String username = "18858960737,"+ 
	//"18867960213,"+
	//"18858960955,"+ 
	//"18858960294,"+ 
	//"18858960117,"+ 
	//"18858960701,"+ 
	//"18867960171,"+ 
	//"18867960211,"+ 
	//"18858960646,"+ 
	//"18858960537,"+ 
	//"18858960673,"+ 
	//"18858960152,"+ 
	//"18867960297,"+ 
	//"18858960783,"+ 
	//"18858960942,"+ 
	//"18858960201,"+ 
	//"18858960756,"+ 
	//"18858960669,"+ 
	//"18858960821,"+ 
	//"18858960047,"+ 
	//"18858960048,"+ 
	//"18858960571,"+ 
	//"18858960073,"+ 
	//"18858960071,"+ 
	//"18858960175,"+ 
	//"18867960122,"+ 
	//"18858960202,"+ 
	//"18858960313,"+ 
	//"18858960924,"+ 
	//"18858960817,"+ 
	//"18858960562,"+ 
	//"18858960572,"+ 
	//"18858960118,"+ 
	//"18858960328,"+ 
	//"18858960573,"+ 
	//"18858960311,"+ 
	//"18858960965,"+ 
	//"18858960340,"+ 
	//"18858960495,"+ 
	//"18858960151,"+ 
	//"18858960724,"+ 
	//"18858960291,"+ 
	//"18858960172,"+ 
	//"18858960391,"+ 
	//"18858960031,"+ 
	//"18867960216,"+ 
	//"18867960310,"+ 
	//"18858960435,"+ 
	//"18858960801,"+ 
	//"18867960129,"+ 
	//"18858960502,"+ 
	//"18858960611,"+ 
	//"18867960321,"+ 
	//"18867960301,"+ 
	//"18858960672,"+ 
	//"18858960097,"+ 
	//"18867960010,"+ 
	//"18858960846,"+ 
	//"18858960964,"+ 
	//"18867960312,"+ 
	//"18858960618,"+ 
	//"18858960387,"+ 
	//"18858960314,"+ 
	//"18858960268,"+ 
	//"15727935162,"+ 
	//"15727938796,"+ 
	//"15727939360,"+ 
	//"15727939369,"+  
	//"15727933216,"+
	//"15727938312,"+ 
	//"15727933204,"+ 
	//"15727933797,"+
	//"15727935220,"+ 
	//"15727938072,"+ 
	//"15727933570,"+ 
	//"15727935223,"+ 
	//"18367979365,"+ 
	//"18367979127,"+ 
	//"18367979180,"+ 
	//"18367979653,"+ 
	//"18257099080,"+ 
	//"18257099620,"+ 
	//"18257099726,"+ 
	//"18367996869,"+ 
	//"18367979685,"+ 
	//"18367979693,"+ 
	//"18367996250,"+ 
	//"18367996357,"+ 
	//"18367996370,"+ 
	//"18257098085,"+ 
	//"18367979879,"+ 
	//"18858960581,"+ 
	//"18867960015,"+ 
	//"18867960229";
	//		String password="王天兵,"+
	//"赵高中,"+
	//"李代富,"+
	//"杨华贵,"+
	//"周爱国,"+
	//"张中领,"+
	//"张超,"+
	//"蒋志敏,"+
	//"陈振飞,"+
	//"王磊,"+
	//"王用,"+
	//"刘善发,"+
	//"曾加财,"+
	//"史永亮,"+
	//"杨成军,"+
	//"李强3,"+
	//"王玉峰,"+
	//"李小雷,"+
	//"贾振虎,"+
	//"张涵卞,"+
	//"王东华,"+
	//"王正峰,"+
	//"刘幼儿,"+
	//"王毓兴,"+
	//"田志强,"+
	//"徐明月,"+
	//"王继政,"+
	//"宣成勇,"+
	//"范小乐,"+
	//"李宗玉,"+
	//"戚鹏飞,"+
	//"蔡见,"+
	//"马东东,"+
	//"王宏宇,"+
	//"熊伟,"+
	//"杨旭亮,"+
	//"袁聪,"+
	//"马发良,"+
	//"樊中旬,"+
	//"刘培,"+
	//"程沙沙,"+
	//"李小康,"+
	//"商仕林,"+
	//"张英东,"+
	//"王军营,"+
	//"汤红凯,"+
	//"李光勇,"+
	//"王锦标,"+
	//"轩帅领,"+
	//"周明权,"+
	//"谢楠,"+
	//"郭当先,"+
	//"武文,"+
	//"许怀胜,"+
	//"田安,"+
	//"张成靖,"+
	//"张昌,"+
	//"辛二朋,"+
	//"李代富,"+
	//"于津,"+
	//"许兵,"+
	//"钟喜明,"+
	//"徐曾军,"+
	//"郭汉广,"+
	//"苏天俊,"+
	//"赵楠楠,"+
	//"李登攀,"+
	//"程红兵,"+
	//"停机补卡,"+
	//"张鹏飞,"+
	//"王建民,"+
	//"黄凯,"+
	//"汤江征,"+
	//"余辉,"+
	//"王怀征,"+
	//"张龙,"+
	//"魏永锋,"+
	//"杨志杰,"+
	//"王勇,"+
	//"刘绍江,"+
	//"张国中,"+
	//"杨胜华,"+
	//"蒲兴明,"+
	//"刘江,"+
	//"李昌龙,"+
	//"向涛,"+
	//"袁达生,"+
	//"林春贤,"+
	//"不接电话,"+
	//"陈俊明,"+
	//"王继政,"+
	//"王令,"+
	//"柳雨宾,"+
	//"周根良";
	//		String[] usernames = username.split(",");
	//		String[] passwords = password.split(",");
	//		ILoginService webServiceImpl= new LoginService();
	//		for(int i=0;i<usernames.length;i++){
	//			Msg<String> msg=webServiceImpl.getSwcurityCode(usernames[i], 1);
	//			if(msg.getResult()){
	//				webServiceImpl.register(usernames[i], passwords[i], "123456", 1);
	//			}
	//		}
	//	}
    
	@Test
	public void test1() throws Exception{
		Context context = EngineHelper.getContext();
		Boolean msg=EvaluationHelper.StatisEva(context, "93e0d182d965410393e527bac7c35fbc");
		System.out.println(msg);
	}

}