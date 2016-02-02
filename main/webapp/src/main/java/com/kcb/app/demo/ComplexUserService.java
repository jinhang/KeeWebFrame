package com.kcb.app.demo;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.kcb.common.date.DateUtil;
import com.wfos.engine.common.WebConfig;
import com.wfos.engine.wrapper.model.Msg;

 

/**

 * <b>function:</b> WebService传递复杂对象，如JavaBean、Array、List、Map等

 * @author hoojo

 * @createDate 2011-3-18 上午08:22:55

 * @file ComplexUserService.java

 * @package com.hoo.service

 * @project CXFWebService

 * @blog http://blog.csdn.net/IBM_hoojo

 * @email hoojo_@126.com

 * @version 1.0

 */

@WebService//(name = "IComplexUserService", targetNamespace = "http://demo.app.kcb.com/IComplexUserService")
//@Features(features = "org.apache.cxf.feature.LoggingFeature") 

@SOAPBinding(style = Style.RPC)

@SuppressWarnings("deprecation")

public class ComplexUserService  implements IComplexUserService{ 

	 @WebMethod
	 public List<Record> getExpressByName(String name,int first){
	    System.out.println("############# 进入 getExpressByName 方法 ##############");

		List<Record> RecordList = new ArrayList<Record>(); 			
        
//		DwrCallWrapperApp dcab = (DwrCallWrapperApp) WebConfig.getBean("DwrCallWrapperApp");
//		Msg msg1 = dcab.getExpressByName("getExpressByName", name,5,first);

		Map<String, Object> map;

	    for(int i=0;i<3;i++){

			Record record1 = new Record();
//			map = (Map<String, Object>)msg1.getLvalue().get(i);			

		    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sqldate = "2014-05-02";
			
			String address = "测试";
			record1.setId(i);
			record1.setAddress(address);
			record1.setRecordtime(sqldate);			
			record1.setSendcode("1235698");
			RecordList.add(record1);
	    }
	    System.out.println("############# end of getExpressByName 方法 ##############");
        return RecordList;
		
        

	}
	@WebMethod//保存来自手机的条码及地址信息
    public boolean saveExpressAddress(String sendcode,String province,String city,String district,String recordman) {		
		//recordtype=3 表示来自手机端存储
		//SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String datetime = tempDate.format(new java.util.Date());
		Date   now   =   new   Date();  
//		DwrCallWrapperOutstock dcw = (DwrCallWrapperOutstock) WebConfig.getBean("DwrCallWrapperOutstock");
//		Express expressobj = new Express();
//		expressobj.setSendcode(sendcode);
//		expressobj.setProvince(province);
//		expressobj.setCity(city);
//		expressobj.setDistrict(district);
//		expressobj.setRecordtype(3);
//		expressobj.setRecordman(recordman);
//		expressobj.setImporttime(now);
//		
//		
//		Msg msg = dcw.expressWrite("t_write_express", expressobj,null,null);
//   		//Msg msg = dcw.expressWrite("t_write_express", "20130702", "北京","北京市" , "东城区","", 3, null, "james", null, null, null);
//   		//System.out.println("运费:"+msg.getDvalue()+" 网点公司:"+msg.getSvalue());
//		if(msg.getOvalue() != null){
//			//保存成功
//			return true;
//		}
		return false;

    }
	
	/**
	 * saveExpressAddress方法的重载，增加了recordmanid参数
	 */
	@WebMethod
    public boolean conserveExpressAddress(String sendcode,String province,String city,String district,String recordman,String recordmanid) {		
		//recordtype=3 表示来自手机端存储
		//SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String datetime = tempDate.format(new java.util.Date());
		Date   now   =   new   Date();  
//		DwrCallWrapperOutstock dcw = (DwrCallWrapperOutstock) WebConfig.getBean("DwrCallWrapperOutstock");
//		
//		Express expressobj = new Express();
//		expressobj.setSendcode(sendcode);
//		expressobj.setProvince(province);
//		expressobj.setCity(city);
//		expressobj.setDistrict(district);
//		expressobj.setRecordtype(3);
//		expressobj.setRecordman(recordman);
//		expressobj.setImporttime(now);
//		expressobj.setRecordmanid(recordmanid);
//		
//		Msg msg = dcw.expressWrite("t_write_express",expressobj,null,null);
//   		//Msg msg = dcw.expressWrite("t_write_express", "20130702", "北京","北京市" , "东城区","", 3, null, "james", null, null, null);
//   		//System.out.println("运费:"+msg.getDvalue()+" 网点公司:"+msg.getSvalue());
//		if(msg.getOvalue() != null){
//			//保存成功
//			return true;
//		}
		return false;

    }
	
	@WebMethod//获取用户当天通过手机录入的单子
    public long getCountByName(  String name) {		
		DwrCallWrapperApp dca = (DwrCallWrapperApp) WebConfig.getBean("DwrCallWrapperApp");

	    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format = DateFormat.getDateInstance(DateFormat.MEDIUM);

		Date start = DateUtil.getDateSubDays(new Date(), 0);
		String startdatestr = format.format(start);
		//System.out.println(startdatestr);
		Date startdate = java.sql.Date.valueOf(startdatestr);
		//System.out.println(startdatestr);
		
		Date end = DateUtil.getDateSubDays(new Date(), -1);
		String enddatestr = format.format(end);
		//System.out.println(enddatestr);
		Date enddate = java.sql.Date.valueOf(enddatestr);
		//System.out.println(enddatestr);
		
		Msg msg1 = dca.getRecordCountById("getRecordCountById", name,startdate,enddate);
		//msg1.ovalue.getshowtip;
		//System.out.println((Integer)msg1.getOvalue());
		Long count = (Long)msg1.getOvalue();
		//System.out.println(count);
		return count;
    }
	
	@WebMethod
    public boolean userLogin(String username,String userpasswd)  
    {  

		DwrCallWrapperApp dca = (DwrCallWrapperApp) WebConfig.getBean("DwrCallWrapperApp");	
		Msg msg1 = dca.checkUserLogin("checkUserLogin", username,userpasswd);
		if(Integer.valueOf(msg1.ovalue.toString())==0){//账号或密码错误
            System.out.println("############false##########");
            return false;  
		}else{
            System.out.println("############true##########");
            return true; 
		}
    }
	
	/**
	 * 用户登录
	 * @author TangShangWen
	 * @param username
	 * @param userpasswd
	 * @return 用户ID (如果未找到，返回字符串 "Not Found")
	 */
	@WebMethod
	public String accountLogin(String username,String userpasswd) {
		
		DwrCallWrapperApp dca = (DwrCallWrapperApp) WebConfig.getBean("DwrCallWrapperApp");
		
		Msg msg = dca.checkUserLogin("userLoginAndGetUserId", username,userpasswd);
		
		if(msg.lvalue.size() == 0) {
            System.out.println("############登录失败##########");
            // 找不到该用户，返回字符串"Not Found"
            return "Not Found";
		}
		
		// 找到该用户，返回该用户ID
		System.out.println("ListSize:" + msg.lvalue.size());
		Map<String,String> map = (Map<String,String>)msg.getLvalue().get(0); // 如果用户名和密码唯一的话，返回的肯定只有一个
		String userId = map.get("id");
		System.out.println("userId:" + userId);
		return userId;
	}

}