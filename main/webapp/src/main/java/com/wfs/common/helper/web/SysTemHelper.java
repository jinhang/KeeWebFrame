package com.wfs.common.helper.web;

import static com.wfs.model.tables.Sysexpresstemplate.SYSEXPRESSTEMPLATE;

import java.util.List;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.wfs.common.jooq.JooqHelper;
import com.wfs.engine.Context;
import com.wfs.model.tables.pojos.Sysexpresstemplate;
import com.wfs.task.Msg;
import com.wfs.common.jooq.Expression;

public class SysTemHelper {
	
	private static final Logger logger = Logger.getLogger(SysTemHelper.class);
	
	public static Msg<List<Sysexpresstemplate>> getSysTemp(Context context,String tId,String transportId){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<List<Sysexpresstemplate>> rmsg = new Msg<List<Sysexpresstemplate>>();
		JooqHelper helper = new JooqHelper().where(SYSEXPRESSTEMPLATE.ID.ne("1")).and(Expression.NotNull(tId,SYSEXPRESSTEMPLATE.ID.eq(tId)))
				.and(Expression.NotNull(transportId,SYSEXPRESSTEMPLATE.FORWARDCOMPANYID.eq(transportId)));
		try{
			List<Sysexpresstemplate> systemp =create.select().from(SYSEXPRESSTEMPLATE).where(helper.getCondition()).fetchInto(Sysexpresstemplate.class);
			rmsg.setResult(true);
			rmsg.setValue(systemp);
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
		}		
		return rmsg;
	}
	
	public static Boolean checkSysTempName(Context context,Sysexpresstemplate systemp){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Boolean flag = true;
		try{
			Integer num =create.selectCount().from(SYSEXPRESSTEMPLATE)
					.where(SYSEXPRESSTEMPLATE.NAME.eq(systemp.getName())
							.and(SYSEXPRESSTEMPLATE.ID.ne(systemp.getId())))
							.fetchOneInto(Integer.class);
			if(num>=1){
				flag=false;
			}
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		}		
		return flag;
	}
	
	public static Boolean updateSysTemp(Context context,Sysexpresstemplate systemp){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Boolean flag = true;
		try{
			create.update(SYSEXPRESSTEMPLATE).set(SYSEXPRESSTEMPLATE.NAME,systemp.getName())
			.set(SYSEXPRESSTEMPLATE.NAME,systemp.getName())
			.set(SYSEXPRESSTEMPLATE.VALUE,systemp.getValue())
			.set(SYSEXPRESSTEMPLATE.WIDTH,systemp.getWidth())
			.set(SYSEXPRESSTEMPLATE.HEIGHT,systemp.getHeight())
			.set(SYSEXPRESSTEMPLATE.IMAGE,systemp.getImage())
			.set(SYSEXPRESSTEMPLATE.FORWARDCOMPANYID,systemp.getForwardcompanyid())
			.set(SYSEXPRESSTEMPLATE.ISDEFAULT,systemp.getIsdefault())
			.set(SYSEXPRESSTEMPLATE.NEEDSENDCODE,systemp.getNeedsendcode())
			.where(SYSEXPRESSTEMPLATE.ID.eq(systemp.getId())).execute();
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		}		
		return flag;
	}

}
