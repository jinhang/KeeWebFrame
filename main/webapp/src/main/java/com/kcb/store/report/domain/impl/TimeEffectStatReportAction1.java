package com.kcb.store.report.domain.impl;

import java.util.Map;

import com.wfos.engine.transfer.Tasks;
import com.wfos.engine.wrapper.model.Msg;

public class TimeEffectStatReportAction1 extends Tasks {

	@Override
	public Msg execute(Map _mParam, Map<String, Msg> _mReturnParam, Msg _oMsg)
			throws Exception {
		
		String byYear = (String) _mParam.get("byYear");
		String byMonth = (String) _mParam.get("byMonth");
		String stat_type = (String)_mParam.get("stat_type");
		
		
		System.out.println("stat_type:" + stat_type);
		
		Integer byYearInt = Integer.valueOf(byYear);
		Integer byMonthInt = Integer.valueOf(byMonth);
		
		_mParam.put("byYear", byYearInt);
		_mParam.put("byMonth", byMonthInt);
		
		if(stat_type.equals("0")) {
			// 按省统计
			Msg expresses = this.executeTask("t_get_province_time_effect_in_specific_date_1", _mParam);
		} else if (stat_type.equals("1")) {
			// 按市统计
		}

		
		// what you have to do is print them out...just that
		Msg expresses = this.executeTask("t_get_province_time_effect_in_specific_date", _mParam);
		return expresses;
	}


}
