package com.kee.app.interfaces.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import com.kee.app.model.ClientInfo;
import com.kee.app.model.EvationInfo;
import com.kee.app.model.StaffResult1;
import com.kee.app.model.UserInfoResult1;
import com.kee.model.tables.pojos.Shop;
import com.kee.task.Msg;

public interface IUserCenterService {
	
	@WebMethod
    public Msg<UserInfoResult1> getUserInfo(@WebParam(name="userid")String userid);
	
	@WebMethod
    public Msg<String> updateUserInfo(@WebParam(name="userid")String userid,
    		@WebParam(name="image")String image,@WebParam(name="nick")String nick,
    		@WebParam(name="age")Integer age,@WebParam(name="sex")String sex);
	
	@WebMethod
    public List<StaffResult1> getStaffList(@WebParam(name="userid")String userid);
	
	@WebMethod
    public Msg<Shop> getShopInfo(@WebParam(name="staffid")String staffid);
	
	@WebMethod
    public Msg<String> saveOrUpdateShop(@WebParam(name="staffid")String staffid,@WebParam(name="quote")String quote,
    		@WebParam(name="features")String features,@WebParam(name="shopid")String shopid,
    		@WebParam(name="optype")Integer optype);
	
	
	@WebMethod
    public Msg<String> saveOrUpdateClient(@WebParam(name="name")String name,@WebParam(name="telphone")String telphone,
    		@WebParam(name="address")String address,@WebParam(name="staffid")String staffid,@WebParam(name="relationid")String relationid,
    		@WebParam(name="optype")Integer optype,@WebParam(name="landline")String landline);
	
	@WebMethod
    public List<ClientInfo> getClientInfo(@WebParam(name="staffid")String staffid);
	
	@WebMethod
    public Msg<String> validateSecuritycode(@WebParam(name="userid")String userid,@WebParam(name="username")String username);
	
	@WebMethod
    public Msg<String> confirmUser(@WebParam(name="userid")String userid,@WebParam(name="username")String username,
    		@WebParam(name="securitycode")String securitycode);
	
	@WebMethod
    public Msg<String> CertifStaff(@WebParam(name="staffid")String staffid,@WebParam(name="name")String name,
    		@WebParam(name="stationcode")String stationcode,@WebParam(name="staffcode")String staffcode);
	
	@WebMethod
    public Msg<EvationInfo> getEvationInfo(@WebParam(name="staffid")String staffid);

}
