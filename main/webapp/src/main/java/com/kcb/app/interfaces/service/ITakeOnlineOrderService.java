package com.kcb.app.interfaces.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import com.wfs.task.Msg;

public interface ITakeOnlineOrderService {
	
	@WebMethod
    public Msg<String> confirmOrder(@WebParam(name="staffid")String staffid,@WebParam(name="orderid")String orderid,
    		@WebParam(name="userid")String userid);	
	
	@WebMethod
    public Msg<String> pickUpOrder(@WebParam(name="staffid")String staffid,@WebParam(name="orderid")String orderid,
    		@WebParam(name="userid")String userid,@WebParam(name="sendcode")String sendcode,
    		@WebParam(name="serialnum")String serialnum,@WebParam(name="weight")String weight,
    		@WebParam(name="picture")String picture,@WebParam(name="expresscharge")String expresscharge);

}
