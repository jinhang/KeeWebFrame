package com.kee.common.toxml;


import java.io.StringWriter;
import java.net.URL;

import org.apache.log4j.Logger;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;

/**
 *
 */
public class JavaBeanToXml { 
	private Logger log = Logger.getLogger(this.getClass());
	
	public static Marshaller marshaller = null;
	public static Mapping _mapping = new Mapping();

	public static String JavaBeanToXml(Object object, URL path) {
		StringWriter sw = new StringWriter();
		try {
			marshaller = new Marshaller(sw);
			if (path != null){
				_mapping.loadMapping(path);
				marshaller.setMapping(_mapping);
			}
			marshaller.marshal(object);
			return sw.toString();
		} catch (Exception e) {
			System.out.println("Java对象转换xml出错 ...");
			e.printStackTrace();
			return null;
		} finally {
			sw.flush();
		}
	}
}
