package com.kee.store.report.domain.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kee.engine.wrapper.model.Msg;
import com.wfos.engine.transfer.Tasks;

/**
 * @author 张海峰 zhanghaifeng@kucangbao.com
 * @version 创建时间：2013-9-9 上午10:29:05
 * 类说明
 */
public class CheckOrderReportAction extends Tasks{

	@Override
	public Msg execute(Map _mParam, Map<String, Msg> _mReturnParam, Msg _oMsg) throws Exception {

		Msg msg = new Msg();
		
		Map<String, Object[]> res= new HashMap<String, Object[]>();//结果集
		List<Object> orders=_oMsg.getLvalue();
		for(int i=0;i<orders.size();i++){
			Object[] order= (Object[])orders.get(i);
			String ordercode=order[3].toString();
			if(res.containsKey(ordercode)){//如果该天已经存在，则金额累加
				Object[] tmp= res.get(ordercode);
				tmp[13]=tmp[13]+" "+order[13];
				tmp[14]=(Integer)tmp[14]+(Integer)order[14];
			}else {//创建该集合
				res.put(ordercode,order );
			}
		}
		List<Object> list = msg.getLvalue();//對msg中的list赋值
		Set<String> keys=res.keySet();
		for(String ordercode:keys){
			list.add(res.get(ordercode));
		}
		msg.setCvalue(_oMsg.getCvalue());
		return msg;
	}

}
