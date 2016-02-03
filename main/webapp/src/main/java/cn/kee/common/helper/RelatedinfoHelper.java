package cn.kee.common.helper;

import static cn.kee.model.tables.Relatedinfo.RELATEDINFO;
import static cn.kee.model.tables.Shop.SHOP;

import java.util.List;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.kee.app.assign.ImageApi;
import com.kee.common.util.UUIDTool;
import com.kee.model.tables.pojos.Relatedinfo;
import com.kee.model.tables.pojos.Shop;
import com.kee.model.tables.records.RelatedinfoRecord;
import com.kee.model.tables.records.ShopRecord;
import com.kee.task.Msg;
import com.wfs.engine.Context;

public class RelatedinfoHelper {
	
	private static final Logger logger = Logger.getLogger(RelatedinfoHelper.class);
	
	public static Msg<String> saveRelatedInfo(Context context,String userid,String staffid,String orderid,String imagename,Integer type){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		RelatedinfoRecord record = create.newRecord(RELATEDINFO);
		try{
			record.setUserid(userid);
			record.setStaffid(staffid);
			record.setPicture(imagename);
			record.setType(type);
			record.setOrderid(orderid);
			record.setRelatedinfoid(UUIDTool.getUUID());
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
	
	public static Msg<String> uploadImage(Context context,String userid,String staffid,String orderid,String image,Integer type){
		Msg<String> msg = new Msg<String>();
		String filename = UUIDTool.getUUID()+".jpg";
		try{			
			Msg<String> imsg= ImageApi.saveImage(filename, image,"order");
			if(imsg.getResult()){
				RelatedinfoHelper.saveRelatedInfo(context, userid, staffid,orderid,imsg.getValue(),type);
			}else{
				msg.setResult(false);
				return msg;
			}			
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setValue(filename);
		msg.setResult(true);
		return msg;
	}
	
	
	public static Msg<String> getImages(Context context,String orderid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		String image="";
		try{			
			List<String> images = create.select(RELATEDINFO.PICTURE).from(RELATEDINFO)
					.where(RELATEDINFO.ORDERID.eq(orderid)).fetch("picture",String.class);
			for(int i=0;i<images.size();i++){
				if(i==images.size()-1){
					image+=images.get(i);
				}else{
					image+=images.get(i)+",";
				}				
			}	
			if(images.size()>0){				
				msg.setValue(image);
			}else{
				msg.setValue(null);
			}
			msg.setResult(true);
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		return msg;
	}
}
