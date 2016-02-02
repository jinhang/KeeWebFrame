package com.wfs.common.helper;

import static com.wfs.model.tables.Imomessage.IMOMESSAGE;
import static com.wfs.model.tables.User.USER;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.kcb.common.util.UUIDTool;
import com.wfs.common.jooq.JooqHelper;
import com.wfs.engine.Context;
import com.wfs.model.tables.pojos.Imomessage;
import com.wfs.model.tables.pojos.User;
import com.wfs.model.tables.records.ImomessageRecord;
import com.wfs.task.Msg;

public class ImoMessageHelper {
	
	private static final Logger logger = Logger.getLogger(ImoMessageHelper.class);
	
	public static Msg<String> savImoMes(Context context,String text,String userid,String evatype,String orderid,Integer type){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		ImomessageRecord mes = create.newRecord(IMOMESSAGE);
		
		try{
			User user = UserHelper.getUserById(context, userid);	
			mes.setName(user.getNick());
			mes.setImage(user.getImage());
			mes.setOrderid(orderid);
			mes.setEvatype(evatype);
			mes.setUserid(userid);
			mes.setText(text);
			mes.setType(type);
			mes.setCreatetime(new Timestamp((new Date()).getTime()));
			mes.setId(UUIDTool.getUUID());
			mes.store();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setResult(true);
		msg.setValue(mes.getId());
		return msg;
	}
	
	
	public static Msg<List<Imomessage>> getImoMesList(Context context,String orderid,Integer first,Integer limit,String evatype){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<List<Imomessage>> msg = new Msg<List<Imomessage>>();
		List<Imomessage> imos = create.select().from(IMOMESSAGE)
				.where(IMOMESSAGE.ORDERID.eq(orderid).and(IMOMESSAGE.EVATYPE.eq(evatype)))
				.orderBy(IMOMESSAGE.CREATETIME.desc())
				.limit(limit).offset(first).fetchInto(Imomessage.class);
		if(imos.size()>0){
			msg.setResult(true);
			msg.setValue(imos);
		}else{
			msg.setResult(false);
		}
		return msg;
	}

}
