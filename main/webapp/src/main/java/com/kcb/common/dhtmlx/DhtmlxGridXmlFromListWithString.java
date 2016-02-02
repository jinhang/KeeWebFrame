package com.kcb.common.dhtmlx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wfos.engine.transfer.Tasks;
import com.wfos.engine.wrapper.model.Msg;
import com.wfs.common.dhtmlx.DhtmlxDhxComplete;

public class DhtmlxGridXmlFromListWithString extends Tasks{
	
	/**
	 * 指定可以隐藏的列。
	 * @param list
	 * @return
	 */
	public Msg generateGridXml(List list,String colmuns, Map _mParam){
		if(list.size() > 0){
			Object obj = list.get(0);
			if (HashMap.class.isInstance(obj)){
				if (colmuns != null && colmuns.length() > 0)
					return new DhtmlxDhxComplete().generateGridMapXml(list,colmuns.split(","), _mParam);
				return new DhtmlxDhxComplete().generateGridMapXml(list,null);
			}
		}
		return new DhtmlxDhxComplete().generateGridToXml(list,null);
	}
	
	@Override
	public Msg execute(Map _mParam, Map<String, Msg> _mReturnParam, Msg _oMsg) throws Exception {
		// TODO Auto-generated method stub
		Msg msg = new Msg();
		
		String colmuns = (String) _mParam.get("");
		List list = _oMsg.getLvalue();
		msg = generateGridXml(list, colmuns, _mParam);
		
		return msg;
	}
}
