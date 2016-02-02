package com.wfs.common.helper;

import static com.wfs.model.tables.Logisticsdata.LOGISTICSDATA;
import static com.wfs.model.tables.Message.MESSAGE;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.kcb.common.util.UUIDTool;
import com.kcb.zm.interfaces.UploadOdData;
import com.wfs.common.jooq.JooqHelper;
import com.wfs.common.jooq.CodeEnum.ScanType;
import com.wfs.common.jooq.CodeEnum.SignedWay;
import com.wfs.engine.Context;
import com.wfs.model.tables.pojos.Logisticsdata;
import com.wfs.model.tables.pojos.Message;
import com.wfs.model.tables.pojos.Order;
import com.wfs.model.tables.pojos.User;
import com.wfs.model.tables.records.LogisticsdataRecord;
import com.wfs.task.Msg;

public class LogisticsDataHelper {
	
	private static final Logger logger = Logger.getLogger(LogisticsDataHelper.class);
	
	public static Msg<String> saveLogisData(Context context,Logisticsdata ld){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		LogisticsdataRecord record = create.newRecord(LOGISTICSDATA);
		try{
			record.setLogisticsdataid(ld.getLogisticsdataid());
			record.setCreatetime(new Timestamp((new Date()).getTime()));
			record.setDsnum(ld.getDsnum());
			record.setImage(ld.getImage());
			record.setLnstation(ld.getLnstation());
			record.setMemo(ld.getMemo());
			record.setNum(ld.getNum());
			record.setPacakgenum(ld.getPacakgenum());
			record.setPronum(ld.getPronum());
			record.setScanname(ld.getScanname());
			record.setScantime(ld.getScantime());
			record.setScantype(ld.getScantype());
			record.setSendcode(ld.getSendcode());
			record.setStaffcode(ld.getStaffcode());
			record.setState(ld.getState());
			record.setStationcode(ld.getStationcode());
			record.store();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setResult(true);
		msg.setValue(record.getLogisticsdataid());
		return msg;
	}
	
	public static Msg<List<Logisticsdata>> getWaitUploadLogsticData(Context context,String scantype){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<List<Logisticsdata>> rmess = new Msg<List<Logisticsdata>>();
		JooqHelper helper = new JooqHelper().where(LOGISTICSDATA.STATE.le(0).or(LOGISTICSDATA.STATE.isNull())
				.and(LOGISTICSDATA.SCANTYPE.eq(scantype)));
		List<Logisticsdata> mess = create.select().from(LOGISTICSDATA)
				.where(helper.getCondition()).orderBy(LOGISTICSDATA.CREATETIME.asc()).fetchInto(Logisticsdata.class);
		rmess.setValue(mess);
		if(mess.size()>0){
			rmess.setResult(true);
		}else{
			rmess.setResult(false);
		}		
		return rmess;
	}
	
	
	public static Msg<String> updateLogisState(Context context,List<String> ids,List<String> nids){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		try{
			create.update(LOGISTICSDATA).set(LOGISTICSDATA.STATE,1)
			.where(LOGISTICSDATA.LOGISTICSDATAID.in(ids).and(LOGISTICSDATA.LOGISTICSDATAID.notIn(nids))).execute();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setResult(true);
		return msg;
	}
	
	public static Msg<String> uploadODLogiscData(Context context,List<String> sendcodes,String staffid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> rmsg = new Msg<String>();
		try{
			User staff = UserHelper.getUserById(context, staffid);
			List<Logisticsdata> lds = new ArrayList();
			for(int i=0;i<sendcodes.size();i++){
				Logisticsdata ld = new Logisticsdata();
				ld.setNum(String.valueOf(i+1));
				ld.setSendcode(sendcodes.get(i));
				ld.setScantype("OD");
				ld.setStationcode(staff.getStationcode());
				ld.setScantime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format((new Date())));
				ld.setStaffcode(staff.getStaffcode());
				ld.setLnstation("");
				ld.setWeight("");
				ld.setPacakgenum("1");
				ld.setBatchnum("");
				ld.setPronum("");
				ld.setMemo("");
				ld.setDsnum("");
				ld.setImage("");
				ld.setScanname(staff.getName());
				ld.setState(1);
				ld.setLogisticsdataid(UUIDTool.getUUID());
				lds.add(ld);
			}		
			Msg<List<String>> msg = UploadOdData.OdData(lds, ScanType.OD.key);
			if(!msg.getResult()){
				List<String> nums = msg.getValue();
				for(int i=0;i<nums.size();i++){
					lds.get(Integer.valueOf(nums.get(i))).setState(0);
				}
			}
			for(int i=0;i<lds.size();i++){
				saveLogisData(context,lds.get(i));
			}
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
			return rmsg;
		}
		rmsg.setResult(true);
		return rmsg;
	}
	
	
	public static Msg<String> uploadOKLogiscData(Context context,String orderid,String staffid){
		Msg<String> rmsg = new Msg<String>();
		try{
			User staff = UserHelper.getUserById(context, staffid);
			Order o = OrderHelper.getOrderById(context, orderid);
			List<Logisticsdata> lds = new ArrayList();
			Logisticsdata ld = new Logisticsdata();
			ld.setNum("1");
			ld.setSendcode(o.getSendcode());
			ld.setScantype("OK");
			ld.setStationcode(staff.getStationcode());
			ld.setScantime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format((new Date())));
			ld.setStaffcode(staff.getStaffcode());
			ld.setLnstation("");
			ld.setWeight("");
			ld.setPacakgenum("1");
			ld.setBatchnum("");
			ld.setPronum("");
			ld.setScanname(staff.getName());
			if(o.getSignedway().equals("INSTEAD_SIGNED")){
				ld.setMemo("他人代收");				
			}
			if(o.getSignedway().equals("OWN_SIGNED")){
				ld.setMemo("本人,");
				if(o.getSigedname()!=null && !o.getSigedname().equals("")){
					ld.setMemo(ld.getMemo()+o.getSigedname());
				}
			}					
			ld.setDsnum("");
			ld.setImage("");
			ld.setState(1);
			ld.setLogisticsdataid(UUIDTool.getUUID());
			lds.add(ld);
			Msg<List<String>> msg = UploadOdData.OdData(lds, ScanType.OK.key);
			if(!msg.getResult()){
				List<String> nums = msg.getValue();
				for(int i=0;i<nums.size();i++){
					lds.get(Integer.valueOf(nums.get(i))).setState(0);
				}
			}
			for(int i=0;i<lds.size();i++){
				saveLogisData(context,lds.get(i));
			}
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
			return rmsg;
		}
		rmsg.setResult(true);
		return rmsg;
	}
	
	public static Msg<String> uploadYNLogiscData(Context context,String sendcode,
			String pronum,String memo,String staffid){
		Msg<String> rmsg = new Msg<String>();
		try{
			User staff = UserHelper.getUserById(context, staffid);
			List<Logisticsdata> lds = new ArrayList();
			Logisticsdata ld = new Logisticsdata();
			ld.setNum("1");
			ld.setSendcode(sendcode);
			ld.setScantype("YN");
			ld.setStationcode(staff.getStationcode());
			ld.setScanname(staff.getName());
			ld.setScantime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format((new Date())));
			ld.setStaffcode(staff.getStaffcode());
			ld.setLnstation("");
			ld.setWeight("");
			ld.setPacakgenum("1");
			ld.setBatchnum("");
			ld.setPronum(pronum);
			ld.setMemo(memo);
			ld.setDsnum("");
			ld.setImage("");
			ld.setState(1);
			ld.setLogisticsdataid(UUIDTool.getUUID());
			lds.add(ld);
			Msg<List<String>> msg = UploadOdData.OdData(lds, ScanType.YN.key);
			if(!msg.getResult()){
				List<String> nums = msg.getValue();
				for(int i=0;i<nums.size();i++){
					lds.get(Integer.valueOf(nums.get(i))).setState(0);
				}
			}
			for(int i=0;i<lds.size();i++){
				saveLogisData(context,lds.get(i));
			}
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
			return rmsg;
		}
		rmsg.setResult(true);
		return rmsg;
	}

}
