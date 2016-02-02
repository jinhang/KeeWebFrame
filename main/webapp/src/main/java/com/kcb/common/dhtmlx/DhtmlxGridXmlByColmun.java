package com.kcb.common.dhtmlx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wfos.engine.transfer.Tasks;
import com.wfos.engine.wrapper.model.Msg;
import com.wfs.common.dhtmlx.DhtmlxDhxComplete;

public class DhtmlxGridXmlByColmun extends Tasks{
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public Msg generateGridXmlByColmun(List list,Object[] colmun, Map _mParam){
		//for();
		if(list.size() > 0){
			Object obj = list.get(0);
			if (HashMap.class.isInstance(obj)){
				return new DhtmlxDhxComplete().generateGridMapXml(list,colmun, _mParam);
			}
		}
		return new DhtmlxDhxComplete().generateGridToXml(list,null);
	}
	
	@Override
	public Msg execute(Map _mParam, Map<String, Msg> _mReturnParam, Msg _oMsg) throws Exception {
		// TODO Auto-generated method stub
		Msg msg = new Msg();
		
		List list = _oMsg.getLvalue();
		Object[] colmun = _oMsg.getCvalue();
		msg = generateGridXmlByColmun(list, colmun, _mParam);
		
		return msg;
	}
	
}
