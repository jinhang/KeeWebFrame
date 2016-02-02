package com.kcb.app.interfaces.service;


import javax.jws.WebMethod;
import javax.jws.WebParam;

import com.kcb.app.model.DResult1;
import com.wfs.task.Msg;

public interface IDeliveryPackageService {
	
	@WebMethod
    public Msg<DResult1> getPakcageNum(@WebParam(name="staffid")String staffid);
	
	@WebMethod
    public Msg<String> uploadSignedInfo(@WebParam(name="picture")String picture,
    		@WebParam(name="staffid")String staffid,@WebParam(name="userid")String userid,
    		@WebParam(name="orderid")String orderid,@WebParam(name="type")Integer type);
	
	@WebMethod
    public Msg<String> replyMessage(@WebParam(name="text")String text,@WebParam(name="staffid")String staffid,
    		@WebParam(name="userid")String userid,@WebParam(name="orderid")String orderid,
    		@WebParam(name="type")Integer type,@WebParam(name="evatype")String evatype);

}
