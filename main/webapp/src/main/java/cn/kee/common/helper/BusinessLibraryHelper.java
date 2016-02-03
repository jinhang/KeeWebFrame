package cn.kee.common.helper;

import static cn.kee.model.tables.Businesslibrary.BUSINESSLIBRARY;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.kee.common.util.UUIDTool;
import com.kee.model.tables.pojos.Businesslibrary;
import com.kee.model.tables.records.BusinesslibraryRecord;
import com.kee.task.Msg;
import com.wfs.engine.Context;

public class BusinessLibraryHelper {
	
	private static final Logger logger = Logger.getLogger(BusinessLibraryHelper.class);
	
	public static Msg<String> saveBL(Context context,Businesslibrary bl){
		Msg<String> msg = new Msg<String>();
		DSLContext create = context.getJc().getDefaultClient().getContext();
		BusinesslibraryRecord record = create.newRecord(BUSINESSLIBRARY);
		try{
			record.setAction(bl.getAction());
			record.setStaffid(bl.getStaffid());
			record.setUserid(bl.getUserid());
			record.setCreatetime(new Timestamp((new Date()).getTime()));
			record.setOrderid(bl.getOrderid());
			record.setCode(bl.getCode());
			record.setBusinesslibraryid(UUIDTool.getUUID());
			record.store();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setValue(record.getBusinesslibraryid());
		msg.setResult(true);
		return msg;
	}
	
	public static Msg<String> saveBL(Context context,String action,String staffid,String userid,String orderid,String code){
		Msg<String> msg = new Msg<String>();
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Businesslibrary bl = new Businesslibrary();
		try{
			bl.setAction(action);
			bl.setCode(code);
			bl.setOrderid(orderid);
			bl.setStaffid(staffid);
			bl.setUserid(userid);
			msg=BusinessLibraryHelper.saveBL(context, bl);
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		return msg;
	}

}
