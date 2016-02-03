package com.kee.app.interfaces.service;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import com.kee.task.Msg;

public interface ISignedOrderService {
	
	@WebMethod
    public Msg<String> appointSigned(@WebParam(name="orderid")String orderid,@WebParam(name="begintime")String begintime,
    		@WebParam(name="endtime")String endtime,@WebParam(name="userid")String userid);
	
	@WebMethod
    public Msg<String> signedPackage(@WebParam(name="orderid")String orderid,@WebParam(name="userid")String userid,
    		@WebParam(name="signway")String signway);
	
	@WebMethod
    public Msg<String> refusedPackage(@WebParam(name="pronum")String pronum,@WebParam(name="memo")String memo,
    		@WebParam(name="staffid")String staffid,@WebParam(name="orderid")String orderid);

}
