package com.kee.app.interfaces.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import com.kee.app.json.model.ResultModel2;
import com.kee.task.Msg;

public interface IOnlineOdersService {
	
	@WebMethod
    public List<ResultModel2> getAddressLibrary(@WebParam(name="userid")String userid,@WebParam(name="type")Integer type);
	
	@WebMethod
    public ResultModel2 getDefultSender(@WebParam(name="userid")String userid,@WebParam(name="type")Integer type);
	
	@WebMethod
    public Msg<String> opAddressLibrary(@WebParam(name="name")String name,@WebParam(name="provincial")String provincial,
    		@WebParam(name="city")String city,@WebParam(name="district")String district,@WebParam(name="tel")String tel,
    		@WebParam(name="address")String address,@WebParam(name="type")Integer type,@WebParam(name="userid")String userid,
    		@WebParam(name="isdefault")Integer isdefault,@WebParam(name="addresslibraryid")String addresslibraryid,
    		@WebParam(name="optype")Integer optype);
	
	@WebMethod
    public Msg<String> saveOnlineOrder(@WebParam(name="takeaddress")String takeaddress,@WebParam(name="senderaddress")String senderaddress,
    		@WebParam(name="stel")String stel,@WebParam(name="sender")String sender,@WebParam(name="senderprovince")String senderprovince,
    		@WebParam(name="senderdistrict")String senderdistrict,@WebParam(name="sendercity")String sendercity,@WebParam(name="address")String address,
    		@WebParam(name="telphone")String telphone,@WebParam(name="consignee")String consignee,@WebParam(name="receiveprovince")String receiveprovince,
    		@WebParam(name="receivecity")String receivecity,@WebParam(name="receivedistrict")String receivedistrict,
    		@WebParam(name="packagetype")Integer packagetype,@WebParam(name="weightinfo")String weightinfo,
    		@WebParam(name="isinsured")Integer isinsured,@WebParam(name="userid")String userid,@WebParam(name="lat")String lat,@WebParam(name="lng")String lng);
	
	@WebMethod
    public Msg<String> cancelOrder(@WebParam(name="orderid")String orderid);

}
