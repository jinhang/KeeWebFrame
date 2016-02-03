package com.kee.client.system.domain.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kee.engine.wrapper.model.Msg;
import com.wfos.engine.transfer.Tasks;



public class GetStaffInfoAction extends Tasks{
	
	    //查询客户的员工
		@Override
		public Msg execute(Map _mParam, Map<String, Msg> _mReturnParam, Msg _oMsg)
				throws Exception {
			Map map = _mParam;
			Msg meg  = null;
			Msg meg_role = null,meg_shop=null;
			int k;
			String clientid = (String)map.get("clientid");
			String staffname = (String)map.get("staffname");
			
			map.put("staffname",staffname);
			map.put("clientid", clientid);
			
			meg = this.executeTask("get_staffinfo", map);
			System.out.println(meg.lvalue);
			for(int i=0;i<meg.lvalue.size();i++){
				Map staffinfo = (Map) meg.lvalue.get(i);
				meg_role=this.executeTask("get_role", staffinfo);
				k=0;
				for(int j=0;j<meg_role.lvalue.size();j++){
					Map role = (Map) meg_role.lvalue.get(j);
					if(k==0){
						staffinfo.put("role",role.get("role"));
					}else{
						if(role.get("role")!=null){
							String rolename = staffinfo.get("role")+","+role.get("role");
							staffinfo.put("role",rolename);
						}
					}
					k++;
				}
				meg_shop=this.executeTask("get_shop", staffinfo);
				k=0;
				for(int j=0;j<meg_shop.lvalue.size();j++){
					Map shop = (Map) meg_shop.lvalue.get(j);
					if(k==0){
						staffinfo.put("shop",shop.get("shop"));
					}else{
						if(shop.get("shop")!=null){
							String shopname = staffinfo.get("shop")+","+shop.get("shop");
							staffinfo.put("shop",shopname);
						}
					}
					k++;
				}
				System.out.println(meg.lvalue);
			}
			return meg;
		}
}
