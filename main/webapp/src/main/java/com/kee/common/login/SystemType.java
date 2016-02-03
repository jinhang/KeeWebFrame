package com.kee.common.login;

import java.util.Map;

import com.kee.engine.wrapper.model.Msg;
import com.wfos.engine.transfer.Tasks;

public class SystemType  extends Tasks {

	@Override
	public Msg execute(Map _mParam, Map<String, Msg> _mReturnParam, Msg _oMsg)
			throws Exception {
		String clientid = (String) _mParam.get("clientid");
		_mParam.put("clientid", clientid);
		Msg msg=this.executeTask("task_get_masterroleid_by_clientid",_mParam);
		String roleId="";
		if(msg.getLvalue()!=null && msg.getLvalue().size()>0){
			roleId=(String)msg.getLvalue().get(0);
		}
		String flag= getSystemTypeByRoleId(roleId);
		msg.svalue=flag;
		return msg;
	}
	/**
	 * 
	 *  标准版  c5809a569e704f14a48289698db1b85b
	 * 豪华版  42c2e4bb9cb64bf887bc999558dcaa7f
	 *  企业版 4c3175154ce34a25b3c79c1f6246030d
	 * 仓储版    "仓储公司管理员" "8c93cfb310854a3a8854420c2375dd55" "仓储客户管理员"   "b4af67ac40b348069cf93b202933e59a"
	 *  酷仓宝 "fc1afc53027049fca97fd241daa130a5"
	 * @param roleId
	 * @return  B  标准版   H  豪华版   Q  企业版   C  仓储版   K   酷仓宝 T 快递通版    S 商家端(仓储) BT 宝通
	 */

	public String getSystemTypeByRoleId(String roleId){
		String flag="";
		if(roleId.equals("c5809a569e704f14a48289698db1b85b")){
			flag= "B";
		}else if (roleId.equals("42c2e4bb9cb64bf887bc999558dcaa7f")){
			flag= "H";
		}else if (roleId.equals("4c3175154ce34a25b3c79c1f6246030d")){
			flag= "Q";
		}else if (roleId.equals("8c93cfb310854a3a8854420c2375dd55") || roleId.equals("b4af67ac40b348069cf93b202933e59a") || roleId.equals("dc0010dcd7ad44dfa8c2db4ef89267ca")){
			flag= "C";
		}else if (roleId.equals("fc1afc53027049fca97fd241daa130a5")){
			flag= "K";
		}else if (roleId.equals("1234e0f0017849bf88a4638d5ae2a4c6") ||roleId.equals("fc1afc53027049fca97fd241daa13001") || roleId.equals("c6bc0c25723046c8801de305031f06ee")||roleId.equals("7855661849bf88a46fffes33689")){
			flag= "T";
		}else if (roleId.equals("d52ede64c33348deaf608970b09547fc")){
			flag= "S";
		}else if (roleId.equals("98cdf23ffd1c42da837620cd89a76e78")){
			flag= "BT";
		}
		return flag;
	}

}
