package com.kee.app.interfaces.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import com.kee.app.model.HomeResult1;
import com.kee.model.tables.pojos.Message;
import com.kee.task.Msg;

public interface IHomeService {
	
	@WebMethod
    public Msg<HomeResult1> getOrderStateNum(@WebParam(name="userid")String userid,@WebParam(name="type")Integer type);
	
	@WebMethod
    public List<Message> getWaitOperationOrderMes(@WebParam(name="userid")String userid,@WebParam(name="type")Integer type);
	
	@WebMethod
    public Boolean readMessage(@WebParam(name="messageids")List<String> messageids);
	
	@WebMethod
    public List<Message> getWaitSendMeg(@WebParam(name="userid")String userid,@WebParam(name="type")Integer type);

}
