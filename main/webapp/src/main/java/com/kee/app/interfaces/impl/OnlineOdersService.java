package com.kee.app.interfaces.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.alibaba.fastjson.JSON;
import com.kee.app.assign.MapApi;
import com.kee.app.interfaces.service.IOnlineOdersService;
import com.kee.app.json.model.ResultModel2;
import com.kee.model.tables.pojos.Addresslibrary;
import com.kee.model.tables.pojos.Businesslibrary;
import com.kee.model.tables.pojos.Message;
import com.kee.model.tables.pojos.Order;
import com.kee.model.tables.pojos.User;
import com.kee.task.Msg;
import com.wfs.dialect.jooq.cluster.JClient;
import com.wfs.engine.Context;
import com.wfs.engine.annotation.TA;
import com.wfs.engine.annotation.TransactionManager;
import com.wfs.engine.common.EngineHelper;

import cn.kee.common.helper.AddressLibraryHelper;
import cn.kee.common.helper.BusinessLibraryHelper;
import cn.kee.common.helper.MessageHelper;
import cn.kee.common.helper.OrderHelper;
import cn.kee.common.helper.ShopHelper;
import cn.kee.common.helper.UserHelper;
import cn.kee.common.jooq.CodeEnum.MessageType;
import cn.kee.common.jooq.CodeEnum.OrderState;
import cn.kee.common.jooq.CodeEnum.PushMesType;
import cn.kee.common.jooq.CodeEnum.SendType;


@WebService
@SOAPBinding(style = Style.RPC)
@SuppressWarnings("deprecation")
public class OnlineOdersService implements IOnlineOdersService{

	@WebMethod
	public List<ResultModel2> getAddressLibrary(String userid, Integer type){
		Msg rmsg = new Msg<List<ResultModel2>>();
		List<ResultModel2> r2s = new ArrayList<ResultModel2>();
		try{
			Msg<List<Addresslibrary>> msg =AddressLibraryHelper.getAddress(EngineHelper.getContext(), userid, type);
			if(msg.getResult()){
				List<Addresslibrary> al = msg.getValue();
				for(int i=0;i<al.size();i++){
					ResultModel2 r2= new ResultModel2();
					r2.setProvincial(al.get(i).getProvincial());
					r2.setCity(al.get(i).getCity());
					r2.setDistrict(al.get(i).getDistrict());
					r2.setAddress(al.get(i).getAddress());
					r2.setName(al.get(i).getName());
					r2.setTel(al.get(i).getTel());
					r2.setAddresslibraryid(al.get(i).getAddresslibraryid());
					r2.setIsdefault(al.get(i).getIsdefault());
					r2s.add(r2);
				}
			}
			rmsg.setResult(true);
			rmsg.setValue(r2s);
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);	
			return r2s;
		}
		return r2s;
	}

	@Override
	public ResultModel2 getDefultSender(String userid,Integer type) {
		ResultModel2 r2 = new ResultModel2();
		try{
			Msg<List<Addresslibrary>> msg =AddressLibraryHelper.getAddress(EngineHelper.getContext(), userid, type);
			if(msg.getResult()){
				List<Addresslibrary> al = msg.getValue();
				for(int i=0;i<al.size();i++){
					if(al.get(i).getIsdefault()==1){
						r2.setProvincial(al.get(i).getProvincial());
						r2.setCity(al.get(i).getCity());
						r2.setDistrict(al.get(i).getDistrict());
						r2.setAddress(al.get(i).getAddress());
						r2.setName(al.get(i).getName());
						r2.setTel(al.get(i).getTel());
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return r2;
		}
		return r2;
	}

	@Override
	public Msg<String> opAddressLibrary(String name, String provincial,
			String city, String district, String tel, String address,
			Integer type, String userid, Integer isdefault,
			String addresslibraryid, Integer optype) {
		Msg<String> rmsg = new Msg<String>();
		Context context = EngineHelper.getContext();
		Addresslibrary al = new Addresslibrary();
		al.setName(name);
		al.setProvincial(provincial);
		al.setCity(city);
		al.setDistrict(district);
		al.setTel(tel);
		al.setAddress(address);
		al.setType(type);
		al.setUserid(userid);
		al.setIsdefault(isdefault);
		try{
			switch(optype){
			case 0:
				rmsg=AddressLibraryHelper.addAdressLibrary(context, al);
				if(rmsg.getResult()){
					al.setAddresslibraryid(rmsg.getValue());
				}else{
					rmsg.setResult(false);
					return rmsg;
				}
				break;
			case 1:
				al.setAddresslibraryid(addresslibraryid);
				rmsg=AddressLibraryHelper.updateAdressLibrary(context, al);
				break;
			case 2:
				al.setAddresslibraryid(addresslibraryid);
				rmsg=AddressLibraryHelper.delAdressLibrary(context, al);
				break;
			}
			if(isdefault==1){
				AddressLibraryHelper.updateDeAdressLibrary(context, al.getAddresslibraryid());
			}
			rmsg.setResult(true);
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
		}			
		return rmsg;
	}

	@Override
	public Msg<String> saveOnlineOrder(String takeaddress,
			String senderaddress, String stel, String sender,
			String senderprovince, String senderdistrict, String sendercity,
			String address, String telphone, String consignee,
			String receiveprovince, String receivecity, String receivedistrict,
			Integer packagetype, String weightinfo, Integer isinsured,
			String userid, String lat, String lng) {
		Context context=EngineHelper.getContext();
		JClient client = context.getJc().getDefaultClient();
		DataSourceTransactionManager manager = client.getTransactionManager();
		TransactionStatus tx =  manager.getTransaction(new DefaultTransactionDefinition());
		Msg<String> rmsg = new Msg<String>();
		Boolean flag= true;
		Order o = new Order();
		o.setTakeaddress(takeaddress);
		o.setSenderaddress(senderaddress);
		o.setStel(stel);
		o.setSender(sender);
		o.setSenderprovince(senderprovince);
		o.setSenderdistrict(senderdistrict);
		o.setSendercity(sendercity);
		o.setAddress(address);
		o.setTelphone(telphone);
		o.setConsignee(consignee);
		o.setReceiveprovince(receiveprovince);
		o.setReceivecity(receivecity);
		o.setReceivedistrict(receivedistrict);
		o.setPackagetype(packagetype);
		o.setWeightinfo(weightinfo);
		o.setIsinsured(isinsured);
		o.setUserid(userid);		
		try{			
			o.setState(OrderState.DJD.key);
			Msg<String> omsg=OrderHelper.saveOrder(context, o);
			if(omsg.getResult()){
				o.setOrderid(omsg.getValue());
				//写入订单操作记录库
				Msg<String> blmsg=BusinessLibraryHelper.saveBL(context, o.getStel()+"在线下单", o.getStaffid(), 
						o.getUserid(), o.getOrderid(), o.getState());
				Msg<List<String>> idsmsg= MapApi.getAroundStaffid(lat, lng);
				if(idsmsg.getResult()){
					Msg<String> msg = MapApi.getOrderStaffid(context,Double.valueOf(lat),Double.valueOf(lng),idsmsg.getValue(),o.getOrderid());
					if(!msg.getResult()){
						flag = false;
						rmsg.setValue("附近没有可以接单的业务员");
						manager.rollback(tx);
					}
					//写入消息队列					
//						Msg<String> mmsg=MessageHelper.saveMes(context, "用户"+o.getStel()+"寄往"+o.getAddress()+"等待接单", o.getUserid(),
//								MessageType.BM.value, staff.getUserid(), o.getOrderid(), 0, 0, o.getStel(), o.getState(), 
//								SendType.UTE.value);
					//不主动发，等待客户端请求
//						MessageHelper.sendMesNow(context, mmsg.getValue());
				}else{
					flag = false;
					manager.rollback(tx);
				}				
			}else{
				flag=false;
				manager.rollback(tx);
			}
		}catch(Exception e){
			e.printStackTrace();
			manager.rollback(tx);
			rmsg.setResult(false);
			return rmsg;
		}finally {
			if (tx != null) {
				if (!tx.isCompleted())
					manager.commit(tx);
			}
		}		
		rmsg.setResult(flag);
		if(flag){
			rmsg.setValue(o.getOrderid());
		}
		return rmsg;
	}

	@Override
	public Msg<String> cancelOrder(String orderid) {
		Msg<String> rmsg = new Msg<String>();
		Context context = EngineHelper.getContext();
		JClient client = context.getJc().getDefaultClient();
		DataSourceTransactionManager manager = client.getTransactionManager();
		TransactionStatus tx =  manager.getTransaction(new DefaultTransactionDefinition());
		Boolean flag = true;
		try{
			Order o = OrderHelper.getOrderById(context, orderid);
			if(o.getState().equals(OrderState.DJD.key) || o.getState().equals(OrderState.DQJ.key) || o.getState().equals(OrderState.DZF.key)){
				if(OrderHelper.cancelOrder(context, orderid)){
					//写入订单操作记录库
					Msg<String> blmsg=BusinessLibraryHelper.saveBL(context, o.getStel()+"取消订单", o.getStaffid(), 
							o.getUserid(), o.getOrderid(), o.getState());
					//写入消息队列					
					Msg<String> mmsg=MessageHelper.saveMes(context, "用户"+o.getStel()+"寄往"+o.getAddress()+"取消订单", o.getUserid(),
							MessageType.BM.value, o.getStaffid(), o.getOrderid(), 0, 0, o.getStel(), o.getState(), 
							SendType.UTE.value,new Timestamp(new Date().getTime()),o.getStaffid(), PushMesType.NSCO.key);
					//主动发通知
					MessageHelper.PushMes(context, mmsg.getValue());
				}
			}else{
				rmsg.setResult(false);
				manager.rollback(tx);
				rmsg.setValue("订单状态已经改变，不能取消");
				return rmsg;
			}
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
			manager.rollback(tx);
			return rmsg;
		}finally {
			if (tx != null) {
				if (!tx.isCompleted())
					manager.commit(tx);
			}
		}	
		rmsg.setResult(flag);
		return rmsg;		
	}

}
