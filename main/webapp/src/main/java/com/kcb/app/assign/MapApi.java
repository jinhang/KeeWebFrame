package com.kcb.app.assign;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amap.api.maps2d.model.Polygon;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.SpatialRelationUtil;
import com.kcb.app.json.model.Datas;
import com.kcb.app.json.model.MapResultJson;
import com.kcb.app.model.Points;
import com.wfs.common.helper.MessageHelper;
import com.wfs.common.helper.OrderHelper;
import com.wfs.common.jooq.CodeEnum.MessageType;
import com.wfs.common.jooq.CodeEnum.PushMesType;
import com.wfs.common.jooq.CodeEnum.SendType;
import com.wfs.engine.Context;
import com.wfs.model.tables.pojos.Order;
import com.wfs.task.Msg;

public class MapApi {
	
	public static Msg<String> getOrderStaffid(Context context,Double lat,Double lng,List<String> ps,String orderid){
		Msg<String> msg = new Msg<String>();
		try{
			if(ps.size()==0){
				msg.setResult(false);
				msg.setValue("无法指定业务接单");
				return msg;
			}
			Order o = OrderHelper.getOrderById(context, orderid);
			int k=0;
			for(int i=0;i<ps.size();i++){
				if(matchStaff(lat,lng,ps.get(i))){
					k++;
					//写入消息队列					
					Msg<String> mmsg=MessageHelper.saveMes(context, "用户"+o.getStel()+"寄往"+o.getAddress()+"等待接单", o.getUserid(),
							MessageType.BM.value,ps.get(i), o.getOrderid(), 0, 0, o.getStel(), o.getState(), 
							SendType.UTE.value,new Timestamp(new Date().getTime()),ps.get(i),PushMesType.NSPU.key);
					//主动发通知
					MessageHelper.PushMes(context, mmsg.getValue());
				}
			}
			if(k==0){
				msg.setResult(false);
			}else{
				msg.setResult(true);
			}
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
		}		
		return msg;
	}
	
	public static Boolean matchStaff(Double lat,Double lng,String staffid){
		LatLng point=new LatLng(lat,lng);
		try{
			Msg<List<LatLng>> msg = CallUrl.call(staffid);
			if(msg.getResult()){
				SpatialRelationUtil sru = new SpatialRelationUtil();
				if(!(sru.isPolygonContainsPoint(msg.getValue(), point))){
					return false;
				}
			}else{
				return false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public static Msg<List<String>> getAroundStaffid(String lat,String lng){
		Msg<List<String>> rmsg= new Msg<List<String>>();	
		List<String> staffids = new ArrayList();
		try{
			Msg<List<Datas>> msg = CallUrl.callAround(String.valueOf(lat),String.valueOf(lng));
			if(msg.getResult()){
				List<Datas> datas = msg.getValue();
				for(int i=0;i<datas.size();i++){
					Boolean flag= true;
					for(int j=0;j<staffids.size();j++){
						if(staffids.get(j).equals(datas.get(i).getUsermapid())){
							flag=false;
							break;
						}
					}
					if(flag){
						staffids.add(datas.get(i).getUsermapid());
					}					
				}
				rmsg.setResult(true);
				rmsg.setValue(staffids);
			}else{
				rmsg.setResult(false);
			}			
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
		}
		return rmsg;
	}
	
}
