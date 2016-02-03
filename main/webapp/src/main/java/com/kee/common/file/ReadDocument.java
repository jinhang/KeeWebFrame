package com.kee.common.file;

import java.io.File;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class ReadDocument {
	public static Document readerDom(String file) {
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			doc = builder.build(file);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
}
