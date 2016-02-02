package com.kcb.client.system.domain.impl;

import java.util.Map;

import com.kcb.common.constant.Constants;
import com.kcb.common.session.DwrGetSession;
import com.wfos.engine.transfer.Tasks;
import com.wfos.engine.wrapper.model.Msg;



/**
 * @author 张海峰 zhanghaifeng@kucangbao.com
 * @version 创建时间：2012-12-10 下午4:24:35
 * 类说明
 */
public class ModifyPasswordAction extends Tasks {

	@Override
	public Msg execute(Map _mParam, Map<String, Msg> _mReturnParam, Msg _oMsg)
			throws Exception {
		String vercode = _mParam.get("vercode") == null ? null:_mParam.get("vercode").toString();
//		String oldPassword=_mParam.get("oldpassword").toString();
//		String newPassword=_mParam.get("newpassword").toString();
//		String id =_mParam.get("id").toString();
		//验证码判断
		Object o = DwrGetSession.getSession(Constants.SESSION_RANDOM);
		String random = o == null? null:o.toString();
		Msg msg = new Msg();
		if(vercode==null||random == null||random.compareToIgnoreCase(vercode) != 0){
			msg.setState(Constants.STATE_ERROR_VALIDATE_RANDOM_CODE);
			msg.setWillreturn(true);
			return msg;
		}
		//验证原始密码
		msg=this.executeTask("t_password_right", _mParam);
		if(Integer.valueOf(msg.ovalue.toString())==0){
			msg.setState(-100);//原始密码错误
			msg.setWillreturn(true);
			return msg;
		}
		msg=this.executeTask("t_update_password", _mParam);
		msg.setState(msg.ivalue>0?200:-200);
		return msg;
	}

}
