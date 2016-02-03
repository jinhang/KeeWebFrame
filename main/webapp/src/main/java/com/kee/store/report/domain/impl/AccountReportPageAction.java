package com.kee.store.report.domain.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kee.common.page.Page;
import com.kee.common.page.PaginationUtil;
import com.kee.engine.wrapper.model.Msg;
import com.wfos.engine.transfer.Tasks;

/**
 * @author jyl
 * @version 创建时间：2013-7-9 上午10:38:13
 * 类说明
 */
public class AccountReportPageAction extends Tasks {

	@Override
	public Msg execute(Map arg0, Map<String, Msg> arg1, Msg arg2)
			throws Exception {
		// TODO Auto-generated method stub
		Integer first = (Integer) arg0.get("first");
		Integer limit = (Integer) arg0.get("limit");
		String[] sendCode = arg0.get("sendCode")==null?null:((String)arg0.get("sendCode")).split(",");
		List list = new ArrayList();
		if(sendCode!=null&&sendCode.length>0){
			for(int i=0;i<sendCode.length;i++){
				list.add(sendCode[i]);
			}
			arg0.remove("begin");
			arg0.put("sendCode", list);
		}else{
			arg0.remove("sendCode");
		}
		Msg msg = this.executeTask("t_common_express_account_report_count", arg0);
		Page page = new Page(limit,((Long)msg.getOvalue()).intValue(),(first+limit-2)/limit+1);
		String url = (String) arg0.get("url");
		String pagination = PaginationUtil.getPagination3(page,url,null);
		msg.setSvalue(pagination);
		msg.setIvalue(((Long)msg.getOvalue()).intValue());
		return msg;
	}

}
