package com.kee.common.socket.action;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent sce) {
		new ClassPathXmlApplicationContext("spring/applicationContext-minaServer.xml");
		// 在tomcat的启动过程中,会看到控制台打印此语句.
		System.out.println("********mina server 启动完毕*********");
	}
}
