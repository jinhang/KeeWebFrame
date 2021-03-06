package com.kee.app.interfaces.impl;

import java.util.List;

import com.kee.app.assign.ImageApi;
import com.kee.app.interfaces.service.IUserCenterService;
import com.kee.app.model.ClientInfo;
import com.kee.app.model.EvationInfo;
import com.kee.app.model.StaffResult1;
import com.kee.app.model.UserInfoResult1;
import com.kee.common.sms.RandomCode;
import com.kee.common.util.UUIDTool;
import com.kee.model.tables.pojos.Message;
import com.kee.model.tables.pojos.Relatedinfo;
import com.kee.model.tables.pojos.Relation;
import com.kee.model.tables.pojos.Shop;
import com.kee.model.tables.pojos.User;
import com.kee.task.Msg;
import com.wfs.engine.Context;
import com.wfs.engine.common.EngineHelper;

import cn.kee.common.helper.AddressLibraryHelper;
import cn.kee.common.helper.CenterHelper;
import cn.kee.common.helper.MessageHelper;
import cn.kee.common.helper.RelationHelper;
import cn.kee.common.helper.ShopHelper;
import cn.kee.common.helper.UserHelper;
import cn.kee.common.jooq.CodeEnum.FollowType;
import cn.kee.common.jooq.CodeEnum.MessageType;
import cn.kee.common.jooq.CodeEnum.SendType;

public class UserCenterService implements IUserCenterService{

	@Override
	public Msg<UserInfoResult1> getUserInfo(String userid) {
		return CenterHelper.getUserInfo(EngineHelper.getContext(), userid);
	}

	@Override
	public Msg<String> updateUserInfo(String userid, String image, String nick,
			Integer age, String sex) {
		Context context = EngineHelper.getContext();
		Msg<String> rmsg = new Msg<String>();		
		Msg<String> msg = ImageApi.saveImage(userid+".jpg", image,"user");
		if(msg.getResult()){
			User u = UserHelper.getUserById(context, userid);
			u.setNick(nick);
			u.setAge(age);
			u.setSex(sex);
			u.setImage(msg.getValue());
			if(!(UserHelper.updateUser(context, u))){
				rmsg.setResult(false);
			}
			rmsg.setResult(true);
		}else{
			rmsg.setResult(false);
		}
		return rmsg;
	}

	@Override
	public List<StaffResult1> getStaffList(String userid) {
		return RelationHelper.getFollowStaffs(EngineHelper.getContext(), userid, FollowType.UFS.value).getValue();
	}

	@Override
	public Msg<Shop> getShopInfo(String staffid) {
		return ShopHelper.getShop(EngineHelper.getContext(), staffid);
	}

	@Override
	public Msg<String> saveOrUpdateShop(String staffid, String quote,
			String features, String shopid, Integer optype) {
		Msg<String> rmsg = new Msg<String>();
		Context context = EngineHelper.getContext();
		Shop s = new Shop();		
		s.setUserid(staffid);
		s.setFeatures(features);
		try{
			if(quote!=null && !quote.equals("")){
				String filename = UUIDTool.getUUID()+".jpg";				
				Msg<String> pmsg = ImageApi.saveImage(filename,quote,"shop");
				if(pmsg.getResult()){
					s.setQuote(pmsg.getValue());
				}
			}			
			switch(optype){
			case 0:
				rmsg=ShopHelper.addShop(context, s);
				break;
			case 1:
				s.setShopid(shopid);
				rmsg=ShopHelper.updateShop(context, s);
				break;
			}
			rmsg.setResult(true);			
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
		}
		return rmsg;
	}

	@Override
	public Msg<String> saveOrUpdateClient(String name, String telphone,
			String address, String staffid, String relationid,
			Integer optype, String landline) {
		Msg<String> rmsg = new Msg<String>();
		Context context = EngineHelper.getContext();
		Relation ri = new Relation();		
		ri.setUname(name);
		ri.setTelphone(telphone);
		ri.setAddress(address);
		ri.setStaffid(staffid);
		ri.setLandline(landline);
		ri.setFollowtype(FollowType.SFU.value);
		try{
			switch(optype){
			case 0:
				rmsg=RelationHelper.addClient(context, ri);
				break;
			case 1:
				ri.setRelationid(relationid);
				rmsg=RelationHelper.updateClient(context, ri);
				break;
			}
			rmsg.setResult(true);
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
		}
		return rmsg;
	}
	
	@Override
	public List<ClientInfo> getClientInfo(String staffid) {
		return RelationHelper.getClient(EngineHelper.getContext(), staffid).getValue();
	}

	@Override
	public Msg<String> validateSecuritycode(String userid, String username) {
		Msg<String> rmsg = new Msg<String>();
		Context context = EngineHelper.getContext();
		String securitycode=RandomCode.createRandom(true, 6);
		try{
			User u = new User();
			u = UserHelper.getUserById(context, userid);
			if(u!=null){
				Message mes = new Message();
				mes.setType(MessageType.SM.value);
				mes.setUserid(userid);
				mes.setContext("验证码:"+securitycode);
				mes.setSendtype(SendType.STUE.value);
				mes.setIssms(1);
				mes.setMobile(username);
				mes.setState(0);
				Msg<String> mmsg=MessageHelper.saveMes(context, mes);
				if(mmsg.getResult()){
					if(!(MessageHelper.sendSMSMes(context, mmsg.getValue()))){
						rmsg.setResult(false);
						return rmsg;
					}else{						
						u.setSecuritycode(securitycode);
						u.setTemptel(username);
						UserHelper.updateUser(context, u);
						rmsg.setResult(true);
					}
				}else{
					rmsg.setResult(false);
					return rmsg;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
			return rmsg;
		}
		rmsg.setValue(securitycode);
		return rmsg;
	}

	@Override
	public Msg<String> confirmUser(String userid, String username,
			String securitycode) {
		Msg<String> rmsg = new Msg<String>();
		Context context = EngineHelper.getContext();
		User u = UserHelper.getUserById(context, userid);
		if(u.getSecuritycode().equals(securitycode)){
			u.setUsername(username);
			u.setTelphone(username);
			if(!UserHelper.updateUser(context, u)){
				rmsg.setResult(false);
			}else{
				rmsg.setResult(true);
			}
		}
		return rmsg;
	}

	@Override
	public Msg<String> CertifStaff(String staffid, String name,
			String stationcode, String staffcode) {		
		return UserHelper.CertifUser(EngineHelper.getContext(), staffid, name, stationcode, staffcode);
	}

	@Override
	public Msg<EvationInfo> getEvationInfo(String staffid) {		
		return CenterHelper.getEvationInfo(EngineHelper.getContext(), staffid);
	}

}
