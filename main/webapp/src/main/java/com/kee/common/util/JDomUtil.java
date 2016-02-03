package com.kee.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * @author 张海峰 zhanghaifeng@kucangbao.com
 * @version 创建时间：2013-10-12 上午9:56:16
 * 类说明
 */

/**
 * 这个类提供一些 JDom 对象常用的方法。
 * 
 */
public class JDomUtil {

	/**
	 * 根据指定路径的XML文件建立JDom对象
	 * 
	 * @param filePath
	 *            XML文件的路径
	 * @return 返回建立的JDom对象，建立不成功返回null 。
	 */
	public static Document buildFromFile(String filePath) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document anotherDocument = builder.build(new File(filePath));
			return anotherDocument;
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据XML 字符串 建立JDom对象
	 * 
	 * @param xmlString
	 *            XML格式的字符串
	 * @return 返回建立的JDom对象，建立不成功返回null 。
	 */
	public static Document buildFromXMLString(String xmlString) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document anotherDocument = builder.build(new StringReader(xmlString));
			return anotherDocument;
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据Dom对象建立JDom对象
	 * 
	 * @param Dom
	 *            org.w3c.dom.Document对象
	 * @return 返回建立的JDom对象，建立不成功返回null 。
	 */
	public static Document buildFromDom(org.w3c.dom.Document Dom) throws JDOMException, IOException {
		org.jdom.input.DOMBuilder builder = new org.jdom.input.DOMBuilder();
		Document jdomDoc = builder.build(Dom);
		return jdomDoc;
	}

	/**
	 * 这个方法使用XMLOutputer将一个JDom对象输出到标准输出设备，使用 utf-8 编码
	 * 
	 * @param myDocument
	 *            将要被输出的JDom对象
	 */
	public static void outputToStdout(Document myDocument) {
		outputToStdout(myDocument, "utf-8");
	}

	/**
	 * 这个方法使用XMLOutputer将一个JDom对象输出到标准输出设备
	 * 
	 * @param myDocument
	 *            将要被输出的JDom对象
	 * @param encoding
	 *            输出使用的编码
	 */
	public static void outputToStdout(Document myDocument, String encoding) {
		try {
			XMLOutputter outputter = new XMLOutputter();
			Format fm = Format.getRawFormat();
			fm.setEncoding(encoding);
			outputter.setFormat(fm);
			outputter.output(myDocument, System.out);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 这个方法将JDom对象转换字符串.
	 * 
	 * @param document
	 *            将要被转换的JDom对象
	 */
	public static String outputToString(Document document) {
		return outputToString(document, "utf-8");
	}

	/**
	 * 这个方法将JDom对象转换字符串.
	 * 
	 * @param document
	 *            将要被转换的JDom对象
	 * @param encoding
	 *            输出字符串使用的编码
	 */
	public static String outputToString(Document document, String encoding) {
		ByteArrayOutputStream byteRep = new ByteArrayOutputStream();
		XMLOutputter outputter = new XMLOutputter();
		Format fm = Format.getRawFormat();
		fm.setEncoding(encoding);
		outputter.setFormat(fm);
		try {
			outputter.output(document, byteRep);
		} catch (Exception e) {

		}

		return byteRep.toString();
	}

	/**
	 * 这个方法将List对象转换字符串.
	 * 
	 * @param document
	 *            将要被转换的JDom对象
	 */
	public static String outputToString(List list) {
		return outputToString(list, "utf-8");
	}

	/**
	 * 这个方法将List对象转换字符串.
	 * 
	 * @param document
	 *            将要被转换的JDom对象
	 * @param encoding
	 *            输出字符串使用的编码
	 */
	public static String outputToString(List list, String encoding) {
		ByteArrayOutputStream byteRep = new ByteArrayOutputStream();
		XMLOutputter outputter = new XMLOutputter();
		Format fm = Format.getRawFormat();
		fm.setEncoding(encoding);
		outputter.setFormat(fm);
		try {
			outputter.output(list, byteRep);
		} catch (Exception e) {

		}

		return byteRep.toString();
	}

	public static org.w3c.dom.Document outputToDom(org.jdom.Document jdomDoc) throws JDOMException {
		org.jdom.output.DOMOutputter outputter = new org.jdom.output.DOMOutputter();
		return outputter.output(jdomDoc);
	}

	/**
	 * 这个方法使用XMLOutputter将JDom对象输出到文件
	 * 
	 * @param myDocument
	 *            将要输出的JDom对象
	 * @param filePath
	 *            将要输出到的磁盘路径
	 */
	public static void outputToFile(Document myDocument, String filePath) {
		outputToFile(myDocument, filePath, "utf-8");
	}

	/**
	 * 这个方法使用XMLOutputter将JDom对象输出到文件
	 * 
	 * @param myDocument
	 *            将要输出的JDom对象
	 * @param filePath
	 *            将要输出到的磁盘路径
	 * @param encoding
	 *            编码方式
	 */
	public static void outputToFile(Document myDocument, String filePath, String encoding) {
		// setup this like outputDocument
		try {
			XMLOutputter outputter = new XMLOutputter();
			Format fm = Format.getRawFormat();
			fm.setEncoding(encoding);
			outputter.setFormat(fm);
			// output to a file
			FileWriter writer = new FileWriter(filePath);
			outputter.output(myDocument, writer);
			writer.close();

		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 这个方法将JDom对象通过样式单转换.
	 * 
	 * @param myDocument
	 *            将要被转换的JDom对象
	 * @param xslFilePath
	 *            XSL文件的磁盘路径
	 */
	public static void executeXSL(Document myDocument, String xslFilePath, StreamResult xmlResult) {
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			// Make the input sources for the XML and XSLT documents
			org.jdom.output.DOMOutputter outputter = new org.jdom.output.DOMOutputter();
			org.w3c.dom.Document domDocument = outputter.output(myDocument);
			javax.xml.transform.Source xmlSource = new javax.xml.transform.dom.DOMSource(domDocument);
			StreamSource xsltSource = new StreamSource(new FileInputStream(xslFilePath));
			// Get a XSLT transformer
			Transformer transformer = tFactory.newTransformer(xsltSource);
			// do the transform
			transformer.transform(xmlSource, xmlResult);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (org.jdom.JDOMException e) {
			e.printStackTrace();
		}
	}

	// Main 函数，局部测试用。
	public static void main(String argv[]) {
		// Document dom = JDomUtil.buildFromFile("f:/web.xml");
		// List list=dom.getRootElement().getChildren("servlet");
		// String vs_dom = JDomUtil.outputToString(list,"gb2312");
		// System.out.println(vs_dom);
		String xmlString = "<record><row f1=\"111\" f2=\"111\"/><row f1=\"222\" f2=\"222\"/></record>";
		Document dom = JDomUtil.buildFromXMLString(xmlString);
		List list = dom.getRootElement().getChildren("row");
		int num = list.size();
		for (int i = 0; i < num; i++) {
			Element element = (Element) list.get(i);
			System.out.println(element.getAttributeValue("f1"));
			System.out.println(element.getAttributeValue("f2"));
		}
	}
}
