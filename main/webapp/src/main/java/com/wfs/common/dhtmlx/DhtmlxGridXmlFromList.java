package com.wfs.common.dhtmlx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wfos.engine.transfer.Tasks;
import com.wfos.engine.wrapper.model.Msg;
import com.wfs.common.dhtmlx.DhtmlxDhxComplete;

public class DhtmlxGridXmlFromList extends Tasks{
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public Msg generateGridXml(List list,Map _mParam){
		if(list!=null&&list.size() > 0){
			Object obj = list.get(0);
			if (HashMap.class.isInstance(obj)){
				return new DhtmlxDhxComplete().generateGridMapXml(list, _mParam);
			}
		}
		return new DhtmlxDhxComplete().generateGridToXml(list,null);
	}
	
	@Override
	public Msg execute(Map _mParam, Map<String, Msg> _mReturnParam, Msg _oMsg) throws Exception {
		
		Msg msg = new Msg();
		
		List list = _oMsg.getLvalue();
		msg = generateGridXml(list,_mParam);
		msg.setState(_oMsg.getState());
		msg.setOvalue(_oMsg.getOvalue());
		msg.setIvalue(_oMsg.getIvalue());
		msg.setLvalue(list);
		return msg;
	}
}
