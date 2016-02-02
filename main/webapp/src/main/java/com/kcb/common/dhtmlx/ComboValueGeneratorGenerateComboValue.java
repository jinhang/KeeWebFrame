package com.kcb.common.dhtmlx;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wfos.engine.transfer.Tasks;
import com.wfos.engine.wrapper.model.Msg;

public class ComboValueGeneratorGenerateComboValue extends Tasks{
	private Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 根据返回的map生成combo对象
	 * @return
	 */
	public Msg generateComboValue(List<Object[]> pLst){
		Msg msg = new Msg();
		StringBuilder sb = new StringBuilder("[");
		sb.append("[")
		.append("'',")
		.append("'全部'")
		.append("],");
		for(Object[] objArr : pLst){
			//combo仅有value和text组成
			if(objArr.length != 2){
				break;
			}
				sb.append("[")
				  .append("'"+objArr[0]+"',")
				  .append("'"+objArr[1]+"'")
				  .append("],");
		}
		sb.append("]");
		msg.setSvalue(sb.toString());
		logger.debug("combo value is :"+msg.svalue);
		return msg;
	}
	
	@Override
	public Msg execute(Map _mParam, Map<String, Msg> _mReturnParam, Msg _oMsg) throws Exception {
		// TODO Auto-generated method stub
		Msg msg = new Msg();
		
		List<Object[]> pLst = _oMsg.getLvalue();
		msg = generateComboValue(pLst);
		
		return msg;
	}
	
}
