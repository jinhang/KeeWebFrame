package com.kee.app.interfaces.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import com.kee.app.model.IMOResult;
import com.kee.app.model.OrderDetail;
import com.kee.app.model.OrderResult1;
import com.kee.app.model.OrderResult2;
import com.kee.model.tables.pojos.Imomessage;
import com.kee.task.Msg;

public interface IOrderCenterService {
	
	@WebMethod
    public List<OrderResult1> getOrderList(@WebParam(name="userid")String userid,@WebParam(name="begintime")String begintime,
    		@WebParam(name="endtime")String endtime,@WebParam(name="state")String states,@WebParam(name="suserid")String suserid,
    		@WebParam(name="dstaffid")String dstaffid,@WebParam(name="staffid")String staffid,
    		@WebParam(name="first")Integer first,@WebParam(name="limit")Integer limit);
	
	@WebMethod
    public Msg<OrderResult2> getOnlineOrderFee(@WebParam(name="orderid")String orderid);
	
	@WebMethod
    public Msg<String> confermOnlineOrderPay(@WebParam(name="totalprice")String totalprice,@WebParam(name="serverfee")String serverfee,
    		@WebParam(name="discountfee")String discountfee,@WebParam(name="expresscharge")String expresscharge,
    		@WebParam(name="orderid")String orderid,@WebParam(name="userid")String userid,@WebParam(name="staffid")String staffid,
    		@WebParam(name="payway")String payway,@WebParam(name="payfee")String payfee);
	
	@WebMethod
    public Msg<OrderDetail> getOrderDetail(@WebParam(name="orderid")String orderid,@WebParam(name="evatype")String evatype);
	
	@WebMethod
    public Msg<String> followStaff(@WebParam(name="staffid")String staffid,@WebParam(name="userid")String userid,@WebParam(name="followtype")String followtype);
	
	@WebMethod
    public Msg<String> evaluationStaff(@WebParam(name="staffid")String staffid,@WebParam(name="userid")String userid,@WebParam(name="orderid")String orderid,
    		@WebParam(name="memo")String memo,@WebParam(name="etime")Integer etime,@WebParam(name="eattitude")Integer eattitude,
    		@WebParam(name="isshow")Integer isshow,@WebParam(name="evatype")String evatype);
	
	@WebMethod
    public List<IMOResult> getImoMes(@WebParam(name="orderid")String orderid,@WebParam(name="first")Integer first,
    		@WebParam(name="limit")Integer limit,@WebParam(name="evatype")String evatype);
	
	@WebMethod
    public Msg<String> uploadRecords(@WebParam(name="orderid")String orderid,@WebParam(name="records")String records);

}
