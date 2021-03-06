package com.kee.task;

import com.kee.task.itasks.LoginTask;
import com.kee.task.itasks.UserTask;
import com.kee.task.itasks.system.ExpressTask;
import com.kee.task.itasks.system.SystemTask;
import com.wfs.engine.proxy.Proxy;


/**
 * 
 * @author qindebu
 * 该类用于封装所有task的入口
 *
 */
public class BaseTasks {
	
	/**
	 * 
	 */
	public static UserTask order = (UserTask)(new Proxy("order")).getInstance(new UserTask());
	
	public static LoginTask login = (LoginTask)(new Proxy("login")).getInstance(new LoginTask());
	
	public static SystemTask system = (SystemTask)(new Proxy("system")).getInstance(new SystemTask());
	
	public static ExpressTask express = (ExpressTask)(new Proxy("express")).getInstance(new ExpressTask());
	
	public void initTask(){
		
	}
	
}
