package com.kee.common.log4j;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.net.SyslogAppender;

/**
 * @author jyl
 * @version 创建时间：2012-10-17 上午11:07:00
 * 类说明
 */
public class UserLog {
	
	 private Logger logger;

	 public UserLog(Logger logger) {
		super();
		this.logger = logger;
	 }

	 public static UserLog getLog(Class clazz){
		 Logger logger = Logger.getLogger(clazz);
		 return new UserLog(logger);
	 }
	 
	 private static class UserInfoLevel extends Level {
		 private static final long serialVersionUID = -7976298046021545731L;
			
		 protected UserInfoLevel(int level, String levelStr, int syslogEquivalent) {
			super(level, levelStr, syslogEquivalent);
				// TODO Auto-generated constructor stub
		 }
	 }

	 private static final Level User_LOG_LEVEL = new UserInfoLevel(20050, "USERINFO", SyslogAppender.LOG_LOCAL0);

	 public void userLog(Object pm_objLogInfo) {
		 logger.log(User_LOG_LEVEL, pm_objLogInfo);
	 }
	 
	

}
