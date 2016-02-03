package com.kee.app.interfaces.impl;

import javax.jws.WebService;


import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;








import org.jooq.DSLContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.kee.app.interfaces.service.ILoginService;
import com.kee.common.sms.RandomCode;
import com.kee.model.tables.pojos.Center;
import com.kee.model.tables.pojos.Message;
import com.kee.model.tables.pojos.Note;
import com.kee.model.tables.pojos.Shop;
import com.kee.model.tables.pojos.User;
import com.kee.task.Msg;
import com.wfs.dialect.jooq.cluster.JClient;
import com.wfs.engine.Context;
import com.wfs.engine.common.EngineHelper;

import cn.kee.common.helper.CenterHelper;
import cn.kee.common.helper.MessageHelper;
import cn.kee.common.helper.NoteHelper;
import cn.kee.common.helper.ShopHelper;
import cn.kee.common.helper.UserHelper;
import cn.kee.common.jooq.CodeEnum.MessageType;
import cn.kee.common.jooq.CodeEnum.SendType;


@WebService
@SOAPBinding(style = Style.RPC)
public class LoginService implements ILoginService{

	@Override
	public Msg<String> login(String username, String password,Integer type,Integer dtype,String dserial){
		Msg rmsg = new Msg<String>();
		try{
			Msg<User> msg = UserHelper.checkUser(EngineHelper.getContext(), username, password,type,dtype,dserial);
			if(msg.getResult()){
				rmsg.setValue(msg.getValue().getUserid());
			}else{
				if(msg.getNode()!=null && msg.getNode().equals("WAIT_AUDIT")){
					rmsg.setValue("您的业务员身份还没有通过审核");
				}else{
					rmsg.setValue("用户名或者密码错误");
				}
			}
			rmsg.setResult(msg.getResult());
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
		}
		return rmsg;
	}

	@Override
	public Msg<String> getSwcurityCode(String username,Integer type){
		Msg rmsg = new Msg<String>();
		String securitycode=RandomCode.createRandom(true, 6);
		try{
			Msg<String> msg=UserHelper.createUser(EngineHelper.getContext(), username, securitycode,type);
			if(msg.getResult()){
				Message mes = new Message();
				mes.setType(MessageType.SM.value);
				mes.setUserid(msg.getValue());
				mes.setContext("验证码:"+securitycode);
				mes.setSendtype(SendType.STUE.value);
				mes.setIssms(1);
				mes.setMobile(username);
				mes.setState(0);
				Msg<String> mmsg=MessageHelper.saveMes(EngineHelper.getContext(), mes);
				if(mmsg.getResult()){
					if(!(MessageHelper.sendSMSMes(EngineHelper.getContext(), mmsg.getValue()))){
						rmsg.setResult(false);
						return rmsg;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
			return rmsg;
		}
		rmsg.setResult(true);
		rmsg.setValue(securitycode);
		return rmsg;
	}

	@Override
	public Msg<String> register(String username, String password,
			String securitycode,Integer type) {
		Context context = EngineHelper.getContext();
		JClient client = context.getJc().getDefaultClient();
		DataSourceTransactionManager manager = client.getTransactionManager();
		TransactionStatus tx =  manager.getTransaction(new DefaultTransactionDefinition());
		Boolean flag=true;
		Msg<String> rmsg = new Msg<String>();
		try{
			Msg<String> msg =UserHelper.registerUserInfo(context, username, password, securitycode,type);
			if(msg.getResult()){
				if(CenterHelper.CheckCenterUnique(context, msg.getValue())){
					Center c = new Center();
					c.setAccount(new Double(0));
					c.setCoins(new Double(0));
					c.setIntegral(0);
					c.setLevel("LV1");
					c.setUserid(msg.getValue());
					CenterHelper.addCenter(context, c);
				}				
				if(type==1){
					if(NoteHelper.CheckUnique(context, msg.getValue())){
						Note note = new Note();
						note.setContext("您的快件正在派送，请准备签收！APP签收下载地址：http://ewp.kucangbao.com/picture/ewpuser.apk   ");
						note.setName("模板1");
						note.setUserid(msg.getValue());
						NoteHelper.addNote(context, note);
					}					
					if(ShopHelper.CheckUnique(context, msg.getValue())){
						Shop s = new Shop();
						s.setFollownum(0);
						s.setTrafficnum(0);
						s.setUserid(msg.getValue());
						ShopHelper.addShop(context, s);
					}
				}
			}else{
				flag=false;
				manager.rollback(tx);
				return rmsg;
			}
		}catch(Exception e){
			e.printStackTrace();
			manager.rollback(tx);
			flag=false;
			return rmsg;
		}finally {
			if (tx != null) {
				if (!tx.isCompleted())
					manager.commit(tx);
			}
		}
		rmsg.setResult(flag);
		return rmsg;
	}
	
	@Override
	public Msg<String> changePassword(String userid, String password,String securitycode,Integer type) {
		Context context = EngineHelper.getContext();		
		return UserHelper.updateUserPassword(context, userid, securitycode, password,type);
	}

}
