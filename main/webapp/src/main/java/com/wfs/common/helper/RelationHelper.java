package com.wfs.common.helper;

import static com.wfs.model.tables.Relation.RELATION;
import static com.wfs.model.tables.User.USER;

import java.util.List;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.kcb.app.model.ClientInfo;
import com.kcb.app.model.StaffResult1;
import com.kcb.common.util.UUIDTool;
import com.wfs.common.jooq.CodeEnum.FollowType;
import com.wfs.engine.Context;
import com.wfs.model.tables.pojos.Relation;
import com.wfs.model.tables.pojos.User;
import com.wfs.model.tables.records.RelationRecord;
import com.wfs.task.Msg;

public class RelationHelper {
	
	private static final Logger logger = Logger.getLogger(RelationHelper.class);
	
	public static Msg<String> saveRelation(Context context,String userid,String staffid,String followtype){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		RelationRecord record = create.newRecord(RELATION);
		Integer num = create.selectCount().from(RELATION)
				.where(RELATION.USERID.eq(userid)
				.and(RELATION.STAFFID.eq(staffid)
				.and(RELATION.FOLLOWTYPE.eq(followtype)))).fetchOneInto(Integer.class);
		if(num>0){
			msg.setResult(true);
			return msg;
		}	
		try{
			record.setUserid(userid);
			record.setStaffid(staffid);
			record.setFollowtype(followtype);
			record.setRelationid(UUIDTool.getUUID());
			record.store();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setValue(record.getUserid());
		msg.setResult(true);
		return msg;
	}
	
	public static Msg<List<StaffResult1>> getFollowStaffs(Context context,String userid,String followtype){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<List<StaffResult1>> msg = new Msg<List<StaffResult1>>();
		try{
			List<StaffResult1> sr1s = create.selectDistinct(USER.NAME,USER.TELPHONE,USER.USERID.as("staffid")).from(RELATION)
					.leftOuterJoin(USER).on(USER.USERID.eq(RELATION.STAFFID))
					.where(RELATION.FOLLOWTYPE.eq(followtype).and(RELATION.USERID.eq(userid)))
					.orderBy(USER.TELPHONE.desc()).fetchInto(StaffResult1.class);
			msg.setResult(true);
			msg.setValue(sr1s);
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
		}
		return msg;
	}
	
	public static Msg<String> addClient(Context context,Relation ri){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		RelationRecord record = create.newRecord(RELATION);
		try{
			record.from(ri);
			record.setRelationid(UUIDTool.getUUID());			
			record.store();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setValue(record.getRelationid());
		msg.setResult(true);
		return msg;
	}
	
	public static Msg<String> updateClient(Context context,Relation ri){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		try{
			create.update(RELATION).set(RELATION.UNAME,ri.getUname())
			.set(RELATION.TELPHONE,ri.getTelphone())
			.set(RELATION.ADDRESS,ri.getAddress())
			.set(RELATION.LANDLINE,ri.getLandline())
			.where(RELATION.RELATIONID.eq(ri.getRelationid())).execute();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setResult(true);
		return msg;
	}
	
	public static Msg<List<ClientInfo>> getClient(Context context,String staffid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<List<ClientInfo>> msg = new Msg<List<ClientInfo>>();
		try{
			List<ClientInfo> cis = create.select(RELATION.UNAME.as("name"),RELATION.TELPHONE,RELATION.ADDRESS,
					RELATION.LANDLINE,RELATION.RELATIONID).from(RELATION)
					.where(RELATION.STAFFID.eq(staffid).and(RELATION.FOLLOWTYPE.eq(FollowType.SFU.value)))
					.orderBy(RELATION.UNAME.asc().nullsLast())
					.fetchInto(ClientInfo.class);
			msg.setResult(true);
			msg.setValue(cis);
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}		
		return msg;
	}

}
