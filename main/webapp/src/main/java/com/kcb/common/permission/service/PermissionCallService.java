package com.kcb.common.permission.service;

import com.wfos.engine.wrapper.model.Msg;

/**
 *
 */
public class PermissionCallService {
	public Msg getPermission(String permissionid){
		Msg msg = new Msg();
		msg.setBvalue(true);
		return msg;
	}
}
