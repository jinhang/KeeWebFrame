package com.kcb.common.log4j;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.kee.common.log4j.UserLog;

/**
 * @author jyl
 * @version 创建时间：2012-10-17 上午11:35:27
 * 类说明
 */
public class UserLogTest {

	private static Logger logger = Logger.getLogger(UserLogTest.class);
	@Test
	public void test(){
		UserLog.getLog(UserLogTest.class).userLog("这是一条测试信息~");
		logger.info("测试info");
	}
}
