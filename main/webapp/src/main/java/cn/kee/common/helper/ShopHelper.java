package cn.kee.common.helper;

import static cn.kee.model.tables.Addresslibrary.ADDRESSLIBRARY;
import static cn.kee.model.tables.Center.CENTER;
import static cn.kee.model.tables.Shop.SHOP;

import java.util.List;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.kee.app.model.Points;
import com.kee.common.util.UUIDTool;
import com.kee.model.tables.pojos.Addresslibrary;
import com.kee.model.tables.pojos.Shop;
import com.kee.model.tables.records.ShopRecord;
import com.kee.task.Msg;
import com.wfs.engine.Context;

public class ShopHelper {
	
	private static final Logger logger = Logger.getLogger(ShopHelper.class);
	
	public static Msg<List<String>> getStaffids(Context context){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<List<String>> msg = new Msg<List<String>>();
		try{
			List<String> ps=create.select(SHOP.USERID).from(SHOP).fetch("userid",String.class);
			if(ps.size()>0){
				msg.setResult(true);
				msg.setValue(ps);
			}else{
				msg.setResult(false);
				return msg;
			}
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}		
		return msg;
	}
	
	public static Msg<Shop> getShop(Context context,String staffid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<Shop> msg = new Msg<Shop>();
		try{
			List<Shop> shop=create.select().from(SHOP).where(SHOP.USERID.eq(staffid)).fetchInto(Shop.class);
			if(shop.size()>0){
				msg.setResult(true);
				msg.setValue(shop.get(0));
			}else{
				msg.setResult(false);
				return msg;
			}
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}		
		return msg;
	}
	
	public static Msg<String> addShop(Context context,Shop s){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		ShopRecord record = create.newRecord(SHOP);
		try{
			record.from(s);
			record.setShopid(UUIDTool.getUUID());			
			record.store();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setValue(record.getShopid());
		msg.setResult(true);
		return msg;
	}
	
	public static Msg<String> updateShop(Context context,Shop s){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		try{
			create.update(SHOP).set(SHOP.QUOTE,s.getQuote())
			.set(SHOP.FEATURES,s.getFeatures())
			.where(SHOP.SHOPID.eq(s.getShopid())).execute();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setResult(true);
		return msg;
	}
	
	public static Boolean CheckUnique(Context context,String userid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Boolean flag = false;		
		try{
			Integer num = create.selectCount().from(SHOP)
					.where(SHOP.USERID.eq(userid)).fetchOneInto(Integer.class);
			if(num==0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		}		
		return flag;
	}

}
