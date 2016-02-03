package com.kee.common.workflow;

import com.kee.common.session.DwrGetSession;
import com.kee.engine.wrapper.model.Msg;
import com.wfos.engine.transfer.Actions;

public class Parameter extends Actions{
	public Msg putObjectParameterFromSession(String session_key,String para_name){
		Msg msg = new Msg();
		msg.setOvalue(this.getInParam().put(para_name, DwrGetSession.getSession(session_key)));
		return msg;
	}
	
	public Msg putStringParameterFromSession(String session_key,String para_name){
		Msg msg = new Msg();
		Object o = DwrGetSession.getSession(session_key);
		String value = (o == null ? null:o.toString());
		this.getInParam().put(para_name, value);
		msg.setSvalue(value);
		return msg;
	}
	
	public Object overObjectParameterFromSession(String session_key,String para_name){
		if(this.getInParam().get(session_key) != null){
			this.getInParam().remove(session_key);
		}
		return this.getInParam().put(para_name, DwrGetSession.getSession(session_key));
	}
}
