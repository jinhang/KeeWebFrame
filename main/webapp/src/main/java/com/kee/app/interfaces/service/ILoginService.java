package com.kee.app.interfaces.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import com.kee.app.json.model.ResultModel1;
import com.kee.task.Msg;

public interface ILoginService {
	
	@WebMethod
    public Msg<String> login(@WebParam(name="username")String username,@WebParam(name="password")String password,
    		@WebParam(name="type")Integer type,@WebParam(name="dtype")Integer dtype,
    		@WebParam(name="dserial")String dserial);
	
	
	@WebMethod
    public Msg<String> getSwcurityCode(@WebParam(name="username")String username,@WebParam(name="type")Integer type);
	
	@WebMethod
    public Msg<String> register(@WebParam(name="username")String username,
    		@WebParam(name="password")String password,@WebParam(name="securitycode")String securitycode,@WebParam(name="type")Integer type);

	@WebMethod
    public Msg<String> changePassword(@WebParam(name="username")String username,@WebParam(name="password")String password,
    		@WebParam(name="securitycode")String securitycode,@WebParam(name="type")Integer type);
}
