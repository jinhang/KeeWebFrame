package com.kcb.common.socket.server;

import java.nio.charset.Charset;

import org.apache.mina.filter.codec.textline.TextLineCodecFactory;

/**
 * <style type="text/css">body{background:#C7EDCC;}</style>
 * 这个类集成自TextLineCodecFactory, 表示对mina的数据的解析方式. 本来可以直接用TextLineCodecFactory的,
 * 但这个类的目的是表示我们可以有自己的方式来解析. MyCodecFactory.java
 * 
 * @author zhu
 */
public class MyCodecFactory extends TextLineCodecFactory {
	public MyCodecFactory() {
		super(Charset.forName("UTF-8"));
	}
}
