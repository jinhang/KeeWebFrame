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
public class WeightSearchReportPageAction extends Tasks {

	@Override
	public Msg execute(Map arg0, Map<String, Msg> arg1, Msg arg2)
			throws Exception {
		// TODO Auto-generated method stub
		Integer first = (Integer) arg0.get("first");
		Integer limit = (Integer) arg0.get("limit");
		if(arg0.get("staffname")==""||arg0.get("staffname")==null){
			arg0.remove("staffname");
		}
		if(arg0.get("sendcode")==""||arg0.get("sendcode")==null){
			arg0.remove("sendcode");
		}
		Msg msg = this.executeTask("t_express_weighted_search_count", arg0);
		Object total = ((Object[])msg.getLvalue().get(0))[0];
		Page page = new Page(limit,((Long)total).intValue(),(first+limit-2)/limit+1);
		String url = (String) arg0.get("url");
		String pagination = PaginationUtil.getPagination3(page,url,null);
		msg.setSvalue(pagination);
		return msg;
	}

}
