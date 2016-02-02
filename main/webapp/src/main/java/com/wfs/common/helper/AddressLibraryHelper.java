package com.wfs.common.helper;

import static com.wfs.model.tables.Addresslibrary.ADDRESSLIBRARY;
import static com.wfs.model.tables.User.USER;

import java.util.List;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.kcb.common.util.UUIDTool;
import com.wfs.engine.Context;
import com.wfs.model.tables.pojos.Addresslibrary;
import com.wfs.model.tables.pojos.User;
import com.wfs.model.tables.records.AddresslibraryRecord;
import com.wfs.task.Msg;

public class AddressLibraryHelper {
	
	private static final Logger logger = Logger.getLogger(AddressLibraryHelper.class);
	
	public static Msg<List<Addresslibrary>> getAddress(Context context,String userid,Integer type){
		DSLContext dSLContext = context.getJc().getDefaultClient().getContext();
		Msg<List<Addresslibrary>> msg = new Msg<List<Addresslibrary>>();
		List<Addresslibrary> al=dSLContext.select().from(ADDRESSLIBRARY)
				.where(ADDRESSLIBRARY.USERID.eq(userid).and(ADDRESSLIBRARY.TYPE.eq(type))
						.and(ADDRESSLIBRARY.ISDEL.eq(0).or(ADDRESSLIBRARY.ISDEL.isNull())))
				.orderBy(ADDRESSLIBRARY.TEL.asc())
				.fetchInto(Addresslibrary.class);
		if(al.size()>=1){
			msg.setResult(true);
			msg.setValue(al);
		}else{
			msg.setResult(false);
		}
		return msg;
	}
	
	
	public static Msg<Addresslibrary> getDefaultSender(Context context,String userid,Integer isdefault){
		DSLContext dSLContext = context.getJc().getDefaultClient().getContext();
		Msg<Addresslibrary> msg = new Msg<Addresslibrary>();
		List<Addresslibrary> al=dSLContext.select().from(ADDRESSLIBRARY)
				.where(ADDRESSLIBRARY.USERID.eq(userid).and(ADDRESSLIBRARY.TYPE.eq(1))
						.and(ADDRESSLIBRARY.ISDEFAULT.eq(1))
						.and(ADDRESSLIBRARY.ISDEL.eq(0))).fetchInto(Addresslibrary.class);
		if(al.size()>=1){
			msg.setResult(true);
			msg.setValue(al.get(0));
		}else{
			msg.setResult(false);
		}
		return msg;
	}
	
	
	public static Msg<String> addAdressLibrary(Context context,Addresslibrary al){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		AddresslibraryRecord record = create.newRecord(ADDRESSLIBRARY);
		try{
			record.from(al);
			record.setAddresslibraryid(UUIDTool.getUUID());			
			record.store();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setValue(record.getAddresslibraryid());
		msg.setResult(true);
		return msg;
	}
	
	public static Msg<String> updateAdressLibrary(Context context,Addresslibrary al){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		try{
			create.update(ADDRESSLIBRARY).set(ADDRESSLIBRARY.NAME,al.getName())
			.set(ADDRESSLIBRARY.PROVINCIAL,al.getProvincial())
			.set(ADDRESSLIBRARY.CITY,al.getCity())
			.set(ADDRESSLIBRARY.DISTRICT,al.getDistrict())
			.set(ADDRESSLIBRARY.TEL,al.getTel())
			.set(ADDRESSLIBRARY.ADDRESS,al.getAddress())
			.set(ADDRESSLIBRARY.TYPE,al.getType())
			.set(ADDRESSLIBRARY.USERID,al.getUserid())
			.set(ADDRESSLIBRARY.ISDEFAULT,al.getIsdefault())
			.where(ADDRESSLIBRARY.ADDRESSLIBRARYID.eq(al.getAddresslibraryid())).execute();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setResult(true);
		return msg;
	}
	
	
	public static Msg<String> delAdressLibrary(Context context,Addresslibrary al){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		try{
			create.update(ADDRESSLIBRARY).set(ADDRESSLIBRARY.ISDEL,1)
			.where(ADDRESSLIBRARY.ADDRESSLIBRARYID.eq(al.getAddresslibraryid())).execute();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setResult(true);
		return msg;
	}
	
	public static Msg<String> updateDeAdressLibrary(Context context,String alid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		try{
			create.update(ADDRESSLIBRARY).set(ADDRESSLIBRARY.ISDEFAULT,0)
			.where(ADDRESSLIBRARY.ADDRESSLIBRARYID.ne(alid)).execute();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setResult(true);
		return msg;
	}

}
