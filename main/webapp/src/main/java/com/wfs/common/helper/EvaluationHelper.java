package com.wfs.common.helper;

import static com.wfs.model.tables.Evaluation.EVALUATION;
import static com.wfs.model.tables.Center.CENTER;
import static com.wfs.model.tables.User.USER;

import java.util.Formatter;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import com.kcb.common.util.UUIDTool;
import com.wfs.engine.Context;
import com.wfs.model.tables.pojos.Center;
import com.wfs.model.tables.pojos.Evaluation;
import com.wfs.model.tables.records.EvaluationRecord;
import com.wfs.task.Msg;

public class EvaluationHelper {
	
	private static final Logger logger = Logger.getLogger(EvaluationHelper.class);
	
	public static Msg<Evaluation> getEvaluationByOrderid(Context context,String orderid,String evatype){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<Evaluation> msg = new Msg<Evaluation>();
		List<Evaluation> es = create.select().from(EVALUATION)
				.where(EVALUATION.ORDERID.eq(orderid).and(EVALUATION.EVATYPE.eq(evatype))).fetchInto(Evaluation.class);
		if(es.size()==1){
			msg.setResult(true);
			msg.setValue(es.get(0));
		}else{
			msg.setResult(false);
		}
		return msg;
	}
	
	public static Msg<String> saveEvaluation(Context context,String staffid, String userid,
			String orderid, String memo, Integer etime, Integer eattitude,
			Integer isshow,String evatype){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		EvaluationRecord record = create.newRecord(EVALUATION);
		try{
			record.setUserid(userid);
			record.setStaffid(staffid);
			record.setOrderid(orderid);
			record.setEtime(etime);
			record.setEattitude(eattitude);
			record.setMemo(memo);
			record.setIsshow(isshow);
			record.setEvatype(evatype);
			record.setEvaluationid(UUIDTool.getUUID());
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
	
	public static Boolean StatisEva(Context context,String staffid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Boolean flag = true;
		try{
			Center center = new Center();			
			List<Map<String, Object>> evalue=create.select(DSL.countDistinct(EVALUATION.ORDERID).as("count"),
					DSL.countDistinct(EVALUATION.ORDERID).mul(EVALUATION.ETIME).as("p"),EVALUATION.ETIME).from(EVALUATION)
					.where(EVALUATION.STAFFID.eq(staffid).and(EVALUATION.ETIME.greaterThan(0))).groupBy(EVALUATION.ETIME)
					.orderBy(EVALUATION.ETIME.asc()).fetch().intoMaps();
			Integer total = create.select(DSL.countDistinct(EVALUATION.ORDERID)).from(EVALUATION)
					.where(EVALUATION.STAFFID.eq(staffid).and(EVALUATION.ETIME.greaterThan(0)))
					.fetchOneInto(Integer.class);
			Integer totalstaff = create.select(DSL.countDistinct(USER.USERID)).from(USER)
					.where(USER.TYPE.eq(1)).fetchOneInto(Integer.class);
			Double etime= new Double(0.0);
			for(int i =0;i<evalue.size();i++){
				switch(Integer.valueOf(String.valueOf(evalue.get(i).get("etime")))){
				case 1:
					center.setCountone(String.valueOf(evalue.get(i).get("count")));
					break;
				case 2:
					center.setCounttwo(String.valueOf(evalue.get(i).get("count")));
					break;
				case 3:
					center.setCountthree(String.valueOf(evalue.get(i).get("count")));
					break;
				case 4:
					center.setCountfour(String.valueOf(evalue.get(i).get("count")));
					break;					
				case 5:
					center.setCountfive(String.valueOf(evalue.get(i).get("count")));
					break;				
				}
				etime+=Double.valueOf((String.valueOf(evalue.get(i).get("p"))))/total;
			}
			center.setEtime(new Formatter().format("%.2f", etime).toString());
			Double ep=create.select(DSL.countDistinct(CENTER.USERID)).from(CENTER)
					.leftOuterJoin(USER).on(USER.USERID.eq(CENTER.USERID))
					.where(CENTER.ETIME.le(String.valueOf(etime)).and(CENTER.ETIME.isNotNull()).and(USER.TYPE.eq(1))).fetchOneInto(Double.class);
			center.setPercentage(new Formatter().format("%.0f", (ep/totalstaff*100)).toString());
			List<Map<String, Object>> avalue=create.select(DSL.countDistinct(EVALUATION.ORDERID),
					DSL.countDistinct(EVALUATION.ORDERID).mul(EVALUATION.EATTITUDE).as("p"),EVALUATION.EATTITUDE).from(EVALUATION)
					.where(EVALUATION.STAFFID.eq(staffid).and(EVALUATION.EATTITUDE.greaterThan(0))).groupBy(EVALUATION.EATTITUDE)
					.orderBy(EVALUATION.EATTITUDE.asc()).fetch().intoMaps();
			Integer atotal = create.select(DSL.countDistinct(EVALUATION.ORDERID)).from(EVALUATION)
					.where(EVALUATION.STAFFID.eq(staffid).and(EVALUATION.EATTITUDE.greaterThan(0)))
					.fetchOneInto(Integer.class);
			Double attitude= new Double(0.0);
			for(int i =0;i<avalue.size();i++){
				switch(Integer.valueOf(String.valueOf(avalue.get(i).get("eattitude")))){
				case 1:
					center.setAcountone(String.valueOf(avalue.get(i).get("count")));
					break;
				case 2:
					center.setAcounttwo(String.valueOf(avalue.get(i).get("count")));
					break;
				case 3:
					center.setAcountthree(String.valueOf(avalue.get(i).get("count")));
					break;
				case 4:
					center.setAcountfour(String.valueOf(avalue.get(i).get("count")));
					break;					
				case 5:
					center.setAcountfive(String.valueOf(avalue.get(i).get("count")));
					break;				
				}
				attitude+=Double.valueOf((String.valueOf(avalue.get(i).get("p"))))/atotal;
			}
			center.setEattitude(new Formatter().format("%.2f", attitude).toString());
			Double ap=create.select(DSL.countDistinct(CENTER.USERID).add(1)).from(CENTER)
					.leftOuterJoin(USER).on(USER.USERID.eq(CENTER.USERID))
					.where(CENTER.EATTITUDE.le(String.valueOf(attitude))
							.and(CENTER.EATTITUDE.isNotNull()).and(USER.TYPE.eq(1)))
					.fetchOneInto(Double.class);
			center.setApercentage(new Formatter().format("%.0f", (ap/totalstaff*100)).toString());
			create.update(CENTER).set(CENTER.ETIME,center.getEtime())
			.set(CENTER.PERCENTAGE,center.getPercentage())
			.set(CENTER.COUNTONE,center.getCountone())
			.set(CENTER.COUNTTWO,center.getCounttwo())
			.set(CENTER.COUNTTHREE,center.getCountthree())
			.set(CENTER.COUNTFOUR,center.getCountfour())
			.set(CENTER.COUNTFIVE,center.getCountfive())
			.set(CENTER.EATTITUDE,center.getEattitude())
			.set(CENTER.APERCENTAGE,center.getApercentage())
			.set(CENTER.ACOUNTONE,center.getAcountone())
			.set(CENTER.ACOUNTTWO,center.getAcounttwo())
			.set(CENTER.ACOUNTTHREE,center.getAcountthree())
			.set(CENTER.ACOUNTFOUR,center.getAcountfour())
			.set(CENTER.ACOUNTFIVE,center.getAcountfive())
			.where(CENTER.USERID.eq(staffid)).execute();			
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}

}
