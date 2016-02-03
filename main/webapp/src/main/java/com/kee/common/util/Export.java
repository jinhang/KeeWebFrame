package com.kee.common.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author 孙东辉 2012-7-26
 */
@Target(value={java.lang.annotation.ElementType.METHOD,java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Export{

	/**
	 * @return 表头名称
	 */
	String header() default " ";

	/**
	 * @return 排序
	 */
	int sort();
	
	/**
	 * 设定列宽
	 * @return
	 */
	int columnWidth() default 100;

}
