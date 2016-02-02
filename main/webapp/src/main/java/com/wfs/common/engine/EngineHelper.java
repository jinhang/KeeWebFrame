package com.wfs.common.engine;

import java.util.HashMap;

import org.springframework.web.context.request.async.DeferredResult;

import com.wfs.dialect.jooq.cluster.JClient;
import com.wfs.dialect.mongo.MClient;
import com.wfs.engine.Context;
import com.wfs.engine.EngineController;
import com.wfs.engine.Param;
import com.wfs.engine.TaskEngine;
import com.wfs.task.Msg;
import com.wfs.task.MySession;

public class EngineHelper {

	public static Context getContext(){
		Context context = new Context();
		context.setAsyn(false);
		TaskEngine engine = EngineController.engineMap.get("engineCluster");
		context.setJc(engine.getJc());
		context.setMc(engine.getMc());
		context.setRc(engine.getRc());
		context.setEngine(engine);
		return context;
		
	}
	
	
	public static TaskEngine getEngine(){
		return EngineController.engineMap.get("engineCluster");
	}
	
	public static Param generateParam(String taskid){
		MySession session = new MySession();
		Param param = new Param();
		param.setSession(session);
		String[] task = taskid.split("\\.");
		param.setTarget(task[0]);
		param.setMethodName(task[1]);
		param.setTaskID(taskid);
		return param;
	}
	
	public static DeferredResult excuteTask(String taskid,HashMap sMap) throws Exception{
		Param param = generateParam(taskid);
		param.setStringParamMap(sMap);
		DeferredResult<String> result = new DeferredResult<String>();
		EngineHelper.getEngine().excuteTask(param, result);
		return result;
	}
	
	
	public static Msg excuteSynTask(String taskid,HashMap oMap) throws Exception{
		Param param = generateParam(taskid);
		param.setObjParamMap(oMap);;
		return EngineHelper.getEngine().runTask(param);
	}
	
	public static JClient getJclient(Context context){
		return context.getJc().getDefaultClient();
	}
	
	public static MClient getMclient(Context context){
		return context.getMc().getDefaultClient();
	}
	
}
