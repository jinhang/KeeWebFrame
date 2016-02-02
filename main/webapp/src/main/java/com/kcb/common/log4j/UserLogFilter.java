package com.kcb.common.log4j;

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

/**
 * @author jyl
 * @version 创建时间：2012-10-17 上午11:11:17
 * 类说明
 */
public class UserLogFilter extends Filter {

	boolean acceptOnMatch = false;
	 int levelMin;
	 int levelMax;

	 public int getLevelMin() {
	  return levelMin;
	 }

	 public void setLevelMin(int levelMin) {
	  this.levelMin = levelMin;
	 }

	 public int getLevelMax() {
	  return levelMax;
	 }

	 public void setLevelMax(int levelMax) {
	  this.levelMax = levelMax;
	 }

	 @Override
	 public int decide(LoggingEvent lgEvent) {

	  int inputLevel = lgEvent.getLevel().toInt();
	  if (inputLevel >= levelMin && inputLevel <= levelMax) {
	   return 0;
	  }
	  return -1;
	 }

}
