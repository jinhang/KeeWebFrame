package cn.kee.common.helper;

import static cn.kee.model.tables.Center.CENTER;
import static cn.kee.model.tables.Order.ORDER;
import static cn.kee.model.tables.Shop.SHOP;
import static cn.kee.model.tables.User.USER;

import java.util.List;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.kee.app.model.EvationInfo;
import com.kee.app.model.UserInfoResult1;
import com.kee.common.util.UUIDTool;
import com.kee.model.tables.pojos.Center;
import com.kee.model.tables.pojos.Shop;
import com.kee.model.tables.records.CenterRecord;
import com.kee.task.Msg;
import com.wfs.engine.Context;

public class CenterHelper {
	
	private static final Logger logger = Logger.getLogger(CenterHelper.class);
	
	public static Msg<UserInfoResult1> getUserInfo(Context context,String userid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<UserInfoResult1> rmsg = new Msg<UserInfoResult1>();
		List<UserInfoResult1> uir= create.select(USER.IMAGE,CENTER.LEVEL,CENTER.ACCOUNT,CENTER.COINS,
				CENTER.INTEGRAL.as("num"),USER.TELPHONE,USER.AGE,USER.SEX,USER.NICK,USER.NAME,
				USER.STATIONCODE,USER.STAFFCODE).from(CENTER)
				.leftOuterJoin(USER).on(USER.USERID.eq(CENTER.USERID))
				.where(CENTER.USERID.eq(userid)).fetchInto(UserInfoResult1.class);
		if(uir.size()==1){
			rmsg.setResult(true);
			rmsg.setValue(uir.get(0));
		}else{
			UserInfoResult1 uir1 = new UserInfoResult1();
			uir1.setAccount(Double.valueOf(0));
			uir1.setAge(0);
			uir1.setCoins(Double.valueOf(0));
			uir1.setLevel("LV1");
			uir1.setNum(0);
			rmsg.setResult(false);
			rmsg.setValue(uir1);
		}
		return rmsg;		
	}
	
	public static Msg<String> addCenter(Context context,Center c){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		CenterRecord record = create.newRecord(CENTER);
		try{
			record.from(c);
			record.setCenterid(UUIDTool.getUUID());			
			record.store();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setValue(record.getCenterid());
		msg.setResult(true);
		return msg;
	}
	
	public static Boolean CheckCenterUnique(Context context,String userid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Boolean flag = false;		
		try{
			Integer num = create.selectCount().from(CENTER)
					.where(CENTER.USERID.eq(userid)).fetchOneInto(Integer.class);
			if(num==0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		}		
		return flag;
	}
	
	public static Msg<EvationInfo> getEvationInfo(Context context,String staffid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<EvationInfo> rmsg = new Msg<EvationInfo>();
		List<EvationInfo> uir= create.select(USER.NICK.as("name"),CENTER.LEVEL,USER.IMAGE,USER.USERNAME.as("tel"),
				USER.SEX,CENTER.ETIME,CENTER.PERCENTAGE,CENTER.COUNTONE,CENTER.COUNTTWO,CENTER.COUNTTHREE,CENTER.COUNTFOUR,
				CENTER.COUNTFIVE,CENTER.EATTITUDE,CENTER.APERCENTAGE,CENTER.ACOUNTONE,CENTER.ACOUNTTWO,CENTER.ACOUNTTHREE,
				CENTER.ACOUNTFOUR,CENTER.ACOUNTFIVE).from(CENTER)
				.leftOuterJoin(USER).on(USER.USERID.eq(CENTER.USERID))
				.where(CENTER.USERID.eq(staffid)).fetchInto(EvationInfo.class);
		if(uir.size()==1){
			rmsg.setResult(true);
			rmsg.setValue(uir.get(0));
		}else{
			rmsg.setResult(false);
		}
		return rmsg;		
	}
}
