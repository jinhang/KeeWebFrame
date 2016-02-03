package com.kee.common.dhtmlx;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.kee.engine.wrapper.model.Msg;
import com.wfos.engine.transfer.Tasks;

import cn.kee.common.dhtmlx.DhtmlxDhxComplete;

public class DhtmlxGridCellXml extends Tasks{
	
	/**
	 * 
	 * @param _list
	 * @param _xmlpath
	 * @return
	 */
	public Msg generateGridCellXml(List _list, Map _mParam){
		Msg msg = new Msg();
		StringBuffer buf = new StringBuffer();
		new DhtmlxDhxComplete().PagingHeader(buf,_list.size(), _mParam);
		for (Iterator iterator = _list.iterator(); iterator.hasNext();) {
			buf.append("<row>");
			Object[] array = (Object[]) iterator.next();
			for (int i = 0; i < array.length; i++) {
				Object temp =  array[i];
				
				buf.append("<cell>"+new DhtmlxDhxComplete().processTemp(temp)+"</cell>");
			
			}
			buf.append("</row>");
		}
		buf.append("</rows>");
		new DhtmlxDhxComplete().log.debug(buf.toString());
		msg.setSvalue(buf.toString());
		return msg;
	}
	
	@Override
	public Msg execute(Map _mParam, Map<String, Msg> _mReturnParam, Msg _oMsg) throws Exception {
		// TODO Auto-generated method stub
		Msg msg = new Msg();
		
		List _list = _oMsg.getLvalue();
		msg = generateGridCellXml(_list, _mParam);
		
		return msg;
	}
	
}
