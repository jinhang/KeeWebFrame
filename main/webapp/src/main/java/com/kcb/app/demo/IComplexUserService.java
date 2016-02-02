package com.kcb.app.demo;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.apache.cxf.feature.Features;

import com.kcb.app.demo.Record;


@WebService

@Features(features = "org.apache.cxf.feature.LoggingFeature")  
@SOAPBinding(style = Style.RPC)

public interface IComplexUserService {

    @WebMethod 
    //public int getExpressByName(String name);
    public List<Record> getExpressByName(String name,int first);
	@WebMethod
    public boolean saveExpressAddress(String sendcode,String province,String city,String district,String recordman);
	@WebMethod
    public boolean conserveExpressAddress(String sendcode,String province,String city,String district,String recordman,String recordmanid);
	@WebMethod
	public long getCountByName(String name);
	@WebMethod
	public boolean userLogin(String username,String userpasswd);
	@WebMethod
	public String accountLogin(String username,String userpasswd);
}