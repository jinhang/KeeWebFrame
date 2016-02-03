package com.kee.zm.impl;

import com.kee.model.tables.pojos.Logisticsdata;

public class ChangeObjectToString {
	
	public static String ChangeDataToString(Logisticsdata d,Integer num){
		String msg = String.valueOf(num)+"|";
		msg+=d.getSendcode()==null?"":d.getSendcode()+"|";
		msg+=d.getScantype()==null?"":d.getScantype()+"|";
		msg+=d.getScanname()==null?"":d.getScanname()+"|";
		msg+=d.getStationcode()==null?"":d.getStationcode()+"|";
		msg+=d.getScantime()==null?"":d.getScantime()+"|";
		msg+=d.getStaffcode()==null?"":d.getStaffcode()+"|";
		msg+=d.getLnstation()==null?"":d.getLnstation()+"|";
		msg+=d.getWeight()==null?"":d.getWeight()+"|";
		msg+=d.getPacakgenum()==null?"":d.getPacakgenum()+"|";
		msg+=d.getBatchnum()==null?"":d.getBatchnum()+"|";
		msg+=d.getPronum()==null?"":d.getPronum()+"|";
		msg+=d.getMemo()==null?"":d.getMemo()+"|";
		msg+=d.getDsnum()==null?"":d.getDsnum()+"|";
		msg+=d.getImage()==null?"":d.getImage()+"|";
		return msg;
	}

}
