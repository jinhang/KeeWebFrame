package com.kee.client.system.domain.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kee.common.date.IsOverTimeUtil;
import com.kee.engine.wrapper.model.Msg;
import com.wfos.engine.transfer.Tasks;

/**
 * @author 张海峰 zhanghaifeng@kucangbao.com
 * @version 创建时间：2013-11-20 下午2:41:44
 * 类说明
 */
public class CheckLoginStatusByShopAction extends Tasks{
	@Override
	public Msg execute(Map _mParam, Map<String, Msg> _mReturnParam, Msg _oMsg) throws Exception {
		Msg msg = new Msg();
		List list = _oMsg.getLvalue();
		this.checkLoginStaus(list);
		msg.setLvalue(list);
		//开始时间 和频率
		return msg;
	}
	
	/**
	 * @param list 检查信息列表
	 * @return
	 */
	private void checkLoginStaus(List list){
		for (Object object : list) {
			Map<String, Object> map = (Map) object;
			Object top_session = map.get("top_session");
			if(top_session != null){
				Date login_time=(Date)map.get("login_time");
				Long expires_in=(String) map.get("r2_expires_in")!=null?Long.valueOf((String) map.get("r2_expires_in")):null;
				Integer openrds=(Integer) map.get("openrds");
				if(openrds==0){
					if(!IsOverTimeUtil.isOverTime(login_time,expires_in)){
						map.put("loginstatus", "未登录");
						map.put("login", "登录^javascript:login(\""+map.get("appkey")+"\","+map.get("shoptype")+",\""+map.get("appsecret")+"\",\""+map.get("nick")+"\")^_self");
					}else{
						map.put("loginstatus", "已登录");
						map.put("login", "重新登录^javascript:login(\""+map.get("appkey")+"\","+map.get("shoptype")+",\""+map.get("appsecret")+"\",\""+map.get("nick")+"\")^_self");
					}
				}else{
					map.put("loginstatus", "已登录(聚石塔)");
					map.put("login", "重新登录^javascript:login(\""+map.get("appkey")+"\","+map.get("shoptype")+",\""+map.get("appsecret")+"\",\""+map.get("nick")+"\")^_self");

				}
				
			}else{
				map.put("loginstatus", "未登录");
				map.put("login", "登录^javascript:login(\""+map.get("appkey")+"\","+map.get("shoptype")+",\""+map.get("appsecret")+"\",\""+map.get("nick")+"\")^_self");
			}
		}
	}
}
