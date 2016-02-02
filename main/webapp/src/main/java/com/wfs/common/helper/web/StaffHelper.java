package com.wfs.common.helper.web;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.wfs.engine.Context;
import com.wfs.model.tables.pojos.Staff;

import static com.wfs.model.tables.Staff.STAFF;

import com.wfs.task.Msg;

public class StaffHelper {
	
	private static final Logger logger = Logger.getLogger(StaffHelper.class);
	
	public static Msg<Staff> getStaffByUsername(Context context,String username,String password){
		Msg<Staff> rmsg = new Msg<Staff>();
		DSLContext create = context.getJc().getDefaultClient().getContext();
		List<Staff> staffs = create.select().from(STAFF).where(STAFF.USERNAME.eq(username)
				.and(STAFF.PASSWORD.eq(password))).fetchInto(Staff.class);
		if(staffs.size()==1){
			create.update(STAFF).set(STAFF.LASTLOGINTIME,new Timestamp((new Date()).getTime()))
			.where(STAFF.STAFFID.eq(staffs.get(0).getStaffid())).execute();
			rmsg.setResult(true);
			rmsg.setValue(staffs.get(0));
		}else{
			rmsg.setResult(false);
		}
		return rmsg;		
	}
}
