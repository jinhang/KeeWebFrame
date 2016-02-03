package com.kee.app.interfaces.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.kee.app.interfaces.service.IMessageService;
import com.kee.common.util.DateUtil;
import com.kee.model.tables.pojos.Note;
import com.kee.model.tables.pojos.Order;
import com.kee.model.tables.pojos.User;
import com.kee.task.Msg;
import com.wfs.dialect.jooq.cluster.JClient;
import com.wfs.engine.Context;
import com.wfs.engine.annotation.TA;
import com.wfs.engine.annotation.TransactionManager;
import com.wfs.engine.common.EngineHelper;

import cn.kee.common.helper.BusinessLibraryHelper;
import cn.kee.common.helper.LogisticsDataHelper;
import cn.kee.common.helper.MessageHelper;
import cn.kee.common.helper.NoteHelper;
import cn.kee.common.helper.OrderHelper;
import cn.kee.common.helper.UserHelper;
import cn.kee.common.jooq.CodeEnum.MessageType;
import cn.kee.common.jooq.CodeEnum.OrderState;
import cn.kee.common.jooq.CodeEnum.PushMesType;

public class MessageService implements IMessageService{

	@Override
	public Msg<String> saveMessage(String staffid, String telphone, String num,
			String sendcode, String time, String type, String sendtype,
			String reserveinfo,Integer istiming) {
		Date mtime = DateUtil.getDateByFormatString(time,com.kee.common.date.DateUtil.formart9);
		Context context=EngineHelper.getContext();
		JClient client = context.getJc().getDefaultClient();
		DataSourceTransactionManager manager = client.getTransactionManager();
		TransactionStatus tx =  manager.getTransaction(new DefaultTransactionDefinition());
		Msg<String> rmsg = new Msg<String>();		
		String rs = "";
		Boolean flag= true;
		String telphones [];
		if(!telphone.equals("")){
			telphones =telphone.split(",");
		}else{
			telphones = null;
			flag= false; 
		}
		String nums [];
		if(!num.equals("")){
			nums =num.split(",");
		}else{
			nums = null;
			flag= false; 
		}
		String sendcodes [];
		if(!sendcode.equals("")){
			sendcodes =sendcode.split(",");
		}else{
			sendcodes = null;
			flag= false; 
		}
		for(int i=0;i<sendcodes.length;i++){
			User u = new User();
			User staff = new User();
			Order o = new Order();				
			try{
				staff = UserHelper.getUserById(context, staffid);
				Msg<Order> msg= OrderHelper.getOrderByTelAndSendcode(context, telphones[i], sendcodes[i]);
				int k=0;
				if(msg.getResult()){
					o=msg.getValue();
					k=o.getDcount();
					o.setRdtime(new Timestamp((new Date()).getTime()));
				}else{
					if(msg.getNode()!=null && msg.getNode().equals(OrderState.YQS.key)){
						rs += "快递单号："+sendcodes[i]+"已签收，不能派送";
						continue;
					}			
				}
				Msg<User> umsg= UserHelper.getUserByTel(context, telphones[i],0);					
				if(umsg.getResult()){
					u = umsg.getValue();
					o.setSigedtel(u.getUsername());
				}else{
//					flag=false;
//					rmsg.setResult(false);
//					manager.rollback(tx);
//					return rmsg;
					o.setSigedtel(telphones[i]);
				}
				o.setSigedname(u.getNick());
//				o.setSigedtel(u.getUsername());
				o.setSuserid(u.getUserid());
				o.setSendstaffname(staff.getNick());
				o.setSendstafftel(staff.getUsername());
				o.setDstaffid(staff.getUserid());
				o.setSendcode(sendcodes[i]);
				o.setReserveinfo(reserveinfo);
				o.setState(OrderState.DQS.key);
				o.setDcount(k++);				
				Msg<String> omsg=OrderHelper.saveOrUpdateOrder(context, o);
				if(omsg.getResult()){
					if(!msg.getResult()){
						o.setOrderid(omsg.getValue());
					}				
					//写入订单操作记录库
					Msg<String> blmsg=BusinessLibraryHelper.saveBL(context, o.getSendstafftel()+"发送派件通知,快递单号："+sendcodes[i], o.getDstaffid(), 
							o.getSuserid(), o.getOrderid(), o.getState());
					//写入消息队列					
					Msg<String> mmsg=MessageHelper.saveMes(context, o.getReserveinfo(), o.getSuserid(),
							MessageType.BM.value, staff.getUserid(), o.getOrderid(),1, 0, o.getSigedtel(), o.getState(), 
							sendtype,new Timestamp(mtime.getTime()),o.getSuserid(),PushMesType.NUSO.key);
					//是否立即发送通知短信
					if(istiming==0){
						MessageHelper.sendSMSMes(context, mmsg.getValue());
					}
					List<String> sc = new ArrayList();
					sc.add(sendcodes[i]);
					LogisticsDataHelper.uploadODLogiscData(context,sc,staffid);
					//主动发通知
//					MessageHelper.PushMes(context, mmsg.getValue(), PushMesType.NSPU.key);
				}else{
					flag=false;
					manager.rollback(tx);
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
		}		
		rmsg.setResult(flag);
		return rmsg;
	}

	@Override
	public Msg<String> opNotes(String noteid, String name, String context,Integer optype,String staffid) {
		Msg<String> rmsg = new Msg<String>();
		Note note = new Note();
		note.setName(name);
		note.setContext(context);
		note.setUserid(staffid);
		try{
			switch(optype){
			case 0:
				rmsg=NoteHelper.addNote(EngineHelper.getContext(), note);
				break;
			case 1:
				note.setNoteid(noteid);
				rmsg=NoteHelper.updateNote(EngineHelper.getContext(), note);
				break;
			case 2:
				note.setNoteid(noteid);
				rmsg=NoteHelper.delNote(EngineHelper.getContext(), note);
				break;
			}
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
		}	
		rmsg.setResult(true);
		return rmsg;
	}

	@Override
	public List<Note> getNotes(String staffid,Integer first,Integer limit) {		
		List<Note> notes = new ArrayList<Note>();
		Msg<List<Note>> rmsg = NoteHelper.getNotes(EngineHelper.getContext(), staffid,first,limit);
		if(rmsg.getResult()){
			notes = rmsg.getValue();
		}
		return notes;
	}

}
