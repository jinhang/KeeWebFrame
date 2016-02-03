package com.kee.task.itasks.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jooq.DSLContext;

import com.kee.model.tables.pojos.Expresstemplate;
import com.kee.model.tables.pojos.Forwardcompany;
import com.kee.model.tables.pojos.Sysexpresstemplate;
import com.kee.task.Msg;
import com.wfs.engine.Context;
import com.wfs.engine.annotation.Description;

import cn.kee.common.UIMessage;
import cn.kee.common.helper.web.ExpressTempHelper;
import cn.kee.common.helper.web.ForwardCompanyHelper;
import cn.kee.common.helper.web.SysTemHelper;


public class ExpressTask {
	
	@Description(value = "context,tId,transportId")
	public Msg<UIMessage> getSysTemplate(Context context,String tId,String transportId){
		Msg<UIMessage> rmsg = new Msg<UIMessage>();
		UIMessage ui = new UIMessage(1, "", "green");
		Msg<List<Sysexpresstemplate>> msg = SysTemHelper.getSysTemp(context, tId, transportId);
		if(msg.getResult()){
			ui.setContext(msg.getValue());
		}else{
			ui.setState(-1);
			ui.setColor("red");
		}
		rmsg.setValue(ui);
		return rmsg;
	}
	
	@Description(value = "context,clientid")
	public Msg<UIMessage> getExpress(Context context,String clientid){
		Msg<UIMessage> rmsg = new Msg<UIMessage>();
		UIMessage ui = new UIMessage(1, "", "green");
		Msg<List<Forwardcompany>> msg = ForwardCompanyHelper.getFor(context);
		if(msg.getResult()){
			ui.setContext(msg.getValue());
		}else{
			ui.setState(-1);
			ui.setColor("red");
		}
		rmsg.setValue(ui);
		return rmsg;
	}
	
	@Description(value = "context,id")
	public Msg<UIMessage> getTemplateById(Context context,String id){
		Msg<UIMessage> rmsg = new Msg<UIMessage>();
		UIMessage ui = new UIMessage(1, "", "green");
		Expresstemplate msg = ExpressTempHelper.getExpressTempById(context, id);
		List<Expresstemplate> rvalue = new ArrayList();
		rvalue.add(msg);
		ui.setContext(rvalue);
		rmsg.setValue(ui);
		return rmsg;
	}
	
	@Description(value = "context,systemp")
	public Msg<UIMessage> updateSysTemp(Context context,Sysexpresstemplate systemp){
		Msg<UIMessage> rmsg = new Msg<UIMessage>();
		UIMessage ui = new UIMessage(1, "设置成功", "green");
		if(SysTemHelper.checkSysTempName(context, systemp)){
			if(!SysTemHelper.updateSysTemp(context, systemp)){
				ui.setState(-1);
				ui.setSvalue("保存失败");
				ui.setColor("red");
			}
		}else{
			ui.setState(-1);
			ui.setSvalue("存在相同名称的快递单模板，请修改模板名称！");
			ui.setColor("red");
		}
		rmsg.setValue(ui);
		return rmsg;
	}
	
	@Description(value = "context,expresstemp")
	public Msg<UIMessage> saveExpress(Context context,Expresstemplate expresstemp){
		Msg<UIMessage> rmsg = new Msg<UIMessage>();
		UIMessage ui = new UIMessage(1, "设置成功", "green");
		if(ExpressTempHelper.checkExpressTempName(context, expresstemp)){
			if(!ExpressTempHelper.saveExpressTemp(context, expresstemp)){
				ui.setState(-1);
				ui.setSvalue("保存失败");
				ui.setColor("red");
			}
		}else{
			ui.setState(-1);
			ui.setSvalue("存在相同名称的快递单模板，请修改模板名称！");
			ui.setColor("red");
		}
		rmsg.setValue(ui);
		return rmsg;
	}
	
	@Description(value = "context,expresstemp")
	public Msg<UIMessage> updateExpressTemp(Context context,Expresstemplate expresstemp){
		Msg<UIMessage> rmsg = new Msg<UIMessage>();
		UIMessage ui = new UIMessage(1, "设置成功", "green");
		if(ExpressTempHelper.checkExpressTempName(context, expresstemp)){
			if(!ExpressTempHelper.saveExpressTemp(context, expresstemp)){
				ui.setState(-1);
				ui.setSvalue("保存失败");
				ui.setColor("red");
			}
		}else{
			ui.setState(-1);
			ui.setSvalue("存在相同名称的快递单模板，请修改模板名称！");
			ui.setColor("red");
		}
		rmsg.setValue(ui);
		return rmsg;
	}

}
