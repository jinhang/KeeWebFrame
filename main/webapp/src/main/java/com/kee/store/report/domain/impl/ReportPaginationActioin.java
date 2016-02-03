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
 * @version 创建时间：2013-7-17 下午4:00:32
 * 类说明
 */
public class ReportPaginationActioin extends Tasks {

	@Override
	public Msg execute(Map arg0, Map<String, Msg> arg1, Msg arg2)
			throws Exception {
		// TODO Auto-generated method stub
		Map map = (Map) arg0.get("map");
		Msg msg = new Msg();
		Integer total = 0;
		Integer first = map.get("first")==null?0:(Integer) map.get("first");
		Integer limit = map.get("limit")==null?20:(Integer) map.get("limit");
		if(arg0.get("total")!=null){
			total = (Integer) map.get("total");
		}else{
			String total_taskid = (String) map.get("total_taskid");
			//多快递单号查询处理
			if(total_taskid.contains("t_common_express_account_report_count")){
				String[] sendCode = map.get("sendCode")==null?null:((String)map.get("sendCode")).split(",");
				List list = new ArrayList();
				if(sendCode!=null&&sendCode.length>0){
					for(int i=0;i<sendCode.length;i++){
						list.add(sendCode[i]);
					}
					map.remove("begin");
					map.put("sendCode", list);
				}else{
					map.remove("sendCode");
				}
			}
			if(total_taskid.contains("t_report_hf_wait_upload")){
				if(map.get("isexport")!=null){
					if(map.get("isexport").equals("1")){
						map.put("isexport", 1);
					}else{
						map.put("isexport", 0);
					}
				}else{
					if(!(map.get("type").equals("1"))){
						map.put("isexport", 0);
					}
				}
				
			}
			msg = this.executeTask(total_taskid, map);
			try{
				total = ((Long)msg.getOvalue()).intValue();
			}catch (Exception e) {
				// TODO: handle exception
				Object tmp_total = ((Object[])msg.getLvalue().get(0))[0];
				total = ((Long)tmp_total).intValue();
			}
		}
		
		Page page = new Page(limit,total,(first+limit-2)/limit+1);
		String url = (String) map.get("url");
		String pagination = PaginationUtil.getPagination3(page,url,null);
		msg.setSvalue(pagination.replace(";", "&")+"</br></br>");
		msg.setIvalue(total);
		return msg;
	}

}
