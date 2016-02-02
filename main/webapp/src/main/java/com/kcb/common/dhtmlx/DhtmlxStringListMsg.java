package com.kcb.common.dhtmlx;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wfos.engine.transfer.Tasks;
import com.wfos.engine.wrapper.model.Msg;
import com.wfs.common.dhtmlx.DhtmlxDhxComplete;

public class DhtmlxStringListMsg extends Tasks{
	
	/**
	 * 
	 * @param _list
	 * @return
	 */
	public Msg generateStringListMsg(List _list){
		Msg msg = new Msg();
		List<List<String>> list = new ArrayList<List<String>>();
		for (Iterator iterator = _list.iterator(); iterator.hasNext();) {
			Object[] array = (Object[]) iterator.next();
			List<String> tmplist = new ArrayList<String>();
			for (int i = 0; i < array.length; i++) {
				Object temp =  array[i];
				tmplist.add(new DhtmlxDhxComplete().processTemp(temp));
			}
			list.add(tmplist);
		}
		msg.setLvalue(list);
		return msg;
	}
	
	@Override
	public Msg execute(Map _mParam, Map<String, Msg> _mReturnParam, Msg _oMsg) throws Exception {
		// TODO Auto-generated method stub
		Msg msg = new Msg();
		
		List _list = _oMsg.getLvalue();
		msg = generateStringListMsg(_list);
		
		return msg;
	}
}
