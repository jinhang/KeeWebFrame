package com.kcb.store.report.domain.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wfos.engine.transfer.Tasks;
import com.wfos.engine.wrapper.model.Msg;

/**
 * @author jyl
 * @version 创建时间：2013-5-30 下午2:07:42
 * 类说明
 */
public class ExceptionOutstockAction extends Tasks {

	@Override
	public Msg execute(Map arg0, Map<String, Msg> arg1, Msg arg2)
			throws Exception {
		// TODO Auto-generated method stub
		List<Object[]> list = arg2.getLvalue();
		List<Object[]> list2 = new ArrayList();
		for(int i=0;i<list.size();i++){
			if(i==0){
				list2.add(list.get(i));
			}else{
				String sendcode = (String)list.get(i)[0];
				String sendcode2 = (String)list.get(i-1)[0];
				if(sendcode2.compareToIgnoreCase(sendcode)!=0){
					list2.add(list.get(i));
				}
			}
		}
		arg2.setLvalue(list2);
		return arg2;
	}

}
