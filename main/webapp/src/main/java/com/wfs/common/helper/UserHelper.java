package com.wfs.common.helper;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.kcb.common.util.UUIDTool;
import com.wfs.common.jooq.Expression;
import com.wfs.engine.Context;

import static com.wfs.model.tables.Evaluation.EVALUATION;
import static com.wfs.model.tables.User.USER;

import com.wfs.model.tables.records.UserRecord;
import com.wfs.model.tables.pojos.Evaluation;
import com.wfs.model.tables.pojos.User;
import com.wfs.task.Msg;

public class UserHelper {
	
	private static final Logger logger = Logger.getLogger(UserHelper.class);
	
	public static Msg<User> checkUser(Context context,String username,String password,
			Integer type,Integer dtype,String dserial) throws Exception {
		DSLContext dSLContext = context.getJc().getDefaultClient().getContext();
		Msg<User> msg = new Msg<User>();
		List<User> user=dSLContext.select(USER.USERID).from(USER).where(USER.USERNAME.eq(username)
				.and(USER.PASSWORD.eq(password).and(USER.TYPE.eq(type)).and(USER.ISIDENTIFIED.eq(1)))).fetchInto(User.class);
		if(user.size()==1){
			msg.setResult(true);
			msg.setValue(user.get(0));
			dSLContext.update(USER).set(USER.DTYPE,dtype)
			.set(USER.DSERIAL,dserial)
			.where(USER.USERID.eq(user.get(0).getUserid())).execute();
		}else{
			user=dSLContext.select(USER.USERID).from(USER).where(USER.USERNAME.eq(username)
					.and(USER.PASSWORD.eq(password).and(USER.TYPE.eq(type)).and(USER.ISIDENTIFIED.eq(0)))).fetchInto(User.class);
			if(user.size()==1){
				msg.setNode("WAIT_AUDIT");
			}
			msg.setResult(false);			
		}
		return msg;
	}
	
	public static Msg<String> createUser(Context context,String username,String securitycode,Integer type){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		UserRecord record = create.newRecord(USER);
		List<User> user = create.select().from(USER)
				.where(USER.USERNAME.eq(username).and(USER.TYPE.eq(type))).fetchInto(User.class);
		if(user.size()>0){
			msg.setValue(user.get(0).getUserid());
			create.update(USER).set(USER.SECURITYCODE,securitycode)
			.where(USER.USERID.eq(user.get(0).getUserid())).execute();
			msg.setResult(true);
			return msg;
		}		
		try{
			record.setUsername(username);
			record.setSecuritycode(securitycode);
			record.setIsidentified(0);
			record.setType(type);
			record.setTelphone(username);
			record.setUserid(UUIDTool.getUUID());
			record.store();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setValue(record.getUserid());
		msg.setResult(true);
		return msg;
	}
	
	public static Msg<String> registerUserInfo(Context context,String username,String password,String securitycode,Integer type){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> rmsg = new Msg<String>();
		try{
			List<User> users = create.select().from(USER)
					.where(USER.USERNAME.eq(username)
					.and(USER.SECURITYCODE.eq(securitycode))
					.and(USER.TYPE.eq(type))
					.and(USER.ISIDENTIFIED.eq(0).or(USER.ISIDENTIFIED.isNull()))).fetchInto(User.class);
			if(users.size()==1){
				if(type==0){
					create.update(USER)
					.set(USER.PASSWORD,password)
					.set(USER.TYPE,type)
					.set(USER.ISIDENTIFIED,1)
					.set(USER.REGISTERTIME,new Timestamp((new Date()).getTime()))
					.where(USER.USERID.eq(users.get(0).getUserid())).execute();
				}else{
					create.update(USER)
					.set(USER.PASSWORD,password)
					.set(USER.TYPE,type)
					.set(USER.REGISTERTIME,new Timestamp((new Date()).getTime()))
					.where(USER.USERID.eq(users.get(0).getUserid())).execute();
				}				
				rmsg.setResult(true);
				rmsg.setValue(users.get(0).getUserid());
			}else{
				rmsg.setResult(false);
			}
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
		}
		return rmsg;
	}
	
	public static User getUserById(Context context,String userid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		User user = new User();
		user = create.fetchOne(USER, USER.USERID.eq(userid)).into(User.class);
		return user;
	}
	
	public static Boolean updateUser(Context context,User user){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		UserRecord record = create.newRecord(USER);
		try{
			create.update(USER).set(USER.AGE,user.getAge())
			.set(USER.IMAGE,user.getImage())
			.set(USER.ISIDENTIFIED,user.getIsidentified())
			.set(USER.NAME,user.getName())
			.set(USER.NICK,user.getNick())
			.set(USER.PASSWORD,user.getPassword())
			.set(USER.REGISTERTIME,user.getRegistertime())
			.set(USER.SECURITYCODE,user.getSecuritycode())
			.set(USER.SEX,user.getSex())
			.set(USER.TELPHONE,user.getTelphone())
			.set(USER.TEMPTEL,user.getTemptel())
			.set(USER.TYPE,user.getType())
			.set(USER.USERNAME,user.getUsername())
			.where(USER.USERID.eq(user.getUserid())).execute();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}		
		return true;
	}
	
	public static Msg<User> getUserByTel(Context context,String username,Integer type){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<User> msg = new Msg<User>();
		List<User> es = create.select().from(USER)
				.where(USER.USERNAME.eq(username).and(USER.TYPE.eq(type))).fetchInto(User.class);
		if(es.size()==1){
			msg.setResult(true);
			msg.setValue(es.get(0));
		}else{
			msg.setResult(false);
		}
		return msg;
	}
	
	public static Msg<String> CertifUser(Context context,String staffid, String name,
			String stationcode, String staffcode){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> rmsg = new Msg<String>();
		try{
			create.update(USER).set(USER.NAME,name)
			.set(USER.STATIONCODE,stationcode)
			.set(USER.STAFFCODE,staffcode)
			.where(USER.USERID.eq(staffid)).execute();
			rmsg.setResult(true);
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
		}		
		return rmsg;
	}
	
	public static Msg<String> updateUserPassword(Context context,String username, String securitycode,String password,Integer type){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> rmsg = new Msg<String>();
		try{
			User user = UserHelper.getUserByTel(context, username,type).getValue();
			if(user.getSecuritycode()!=null && user.getSecuritycode().equals(securitycode)){
				create.update(USER).set(USER.PASSWORD,password)
				.where(USER.USERID.eq(user.getUserid())).execute();
				rmsg.setResult(true);
			}else{
				rmsg.setResult(false);
				rmsg.setValue("验证码错误");
			}			
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
			rmsg.setValue("系统异常");
		}		
		return rmsg;
	}
}
