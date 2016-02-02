package com.kcb.common.dhtmlx;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;
import org.xml.sax.SAXException;

import com.wfos.engine.transfer.Tasks;
import com.wfos.engine.wrapper.model.Msg;

public class xmlLoader extends Tasks {
	private boolean getPermission(String permission) {
		return true;
	}

	public Msg generateXML(String url) throws ParserConfigurationException,
			SAXException, IOException, XPathExpressionException, JDOMException {
		Msg msg = new Msg();

		url = "/grid_header_xml/" + url;
		System.out.println(url);
		Document doc = readerDom(url);

		List nodeList = XPath.selectNodes(doc, "//rows/head/column");
		StringBuffer result = new StringBuffer();
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><rows><head height=\"30\">");
		for (int i = 0; i < nodeList.size(); i++) {
			Element e = (Element) nodeList.get(i);
			String permission = e.getAttributeValue("permission");
			if (getPermission(permission)) {
				result.append("<column ");
				List<Attribute> attr = e.getAttributes();
				for (int j = 0; j < attr.size(); j++) {
					result.append(attr.get(j).getName() + "=\""
							+ attr.get(j).getValue() + "\" ");
					// System.out.println(attr.get(j).getName() + "=\"" +
					// attr.get(j).getValue() + "\" ");
				}
				result.append(">");
				result.append(e.getValue());
				result.append("</column>");
			} else {

			}
		}
		result.append("</head></rows>");

		msg.svalue = result.toString();
		return msg;
	}

	private Document readerDom(String url) {
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			doc = builder.build(this.getClass().getResource(url).getFile());
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}

	@Override
	public Msg execute(Map _mParam, Map<String, Msg> _mReturnParam, Msg _oMsg)
			throws Exception {
		Msg msg = new Msg();

		String url = (String) _mParam.get("url");
		msg = generateXML(url);

		return msg;
	}

	public static InputStream Bytes2InputStream(byte[] bytes) {
		ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
		return stream;
	}

	/**
	 * 美团网订单导入
	 * 
	 * @throws Exception
	 * 
	 */
	public static Msg getListByXml(InputStream is) throws Exception {
		Msg msg = new Msg();
		byte[] bs=null;
		InputStream is1 = null;
		try {
			bs = input2byte(is);
			is1 = Bytes2InputStream(bs);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		SAXBuilder builder = new SAXBuilder();

		List<String[]> result = new ArrayList<String[]>();
		Document doc = null;
		try {
		doc = builder.build(is1);
		}catch (Exception e) {
			msg.setByvalue(bs);
			msg.setState(-1);
			return msg;
		}finally{
			is1.close();
		}
		Element workbook = doc.getRootElement();

		List list = workbook.getContent();

		Element ele = null;
		for (int i = 0; i < list.size(); i++) {

			String cn = list.get(i).getClass().getName();

			if (cn.indexOf("Element") >= 0) {

				ele = (Element) list.get(i);

				System.out.println(ele.getName());

				if ("Worksheet".equals(ele.getName())) {

					break;
				}
			}

		}

		List tablesList = ele.getChildren();

		for (int i = 0; i < tablesList.size(); i++) {

			String cn = tablesList.get(i).getClass().getName();

			if (cn.indexOf("Element") >= 0) {

				ele = (Element) tablesList.get(i);

				if ("Table".equals(ele.getName())) {

					break;
				}

			}
		}

		List rowsList = ele.getChildren();
		int size = -1;
		Namespace type = Namespace.getNamespace("ss",
				"urn:schemas-microsoft-com:office:spreadsheet");
		for (int i = 0; i < rowsList.size(); i++) {
			ele = (Element) rowsList.get(i);
			if ("Row".equals(ele.getName())) {
				List cellsList = ele.getChildren();
				if (size < 0) {
					size = cellsList.size();
				}
				String[] datas = new String[size];
				int plusnum = 0;
				for (int j = 0; j < cellsList.size(); j++) {
					ele = (Element) cellsList.get(j);

					if (ele.getAttributeValue("Index", type) != null) {
						plusnum = Integer.parseInt(ele.getAttributeValue(
								"Index", type) + "")
								- j - 1;
					}
					List datasList = ele.getChildren();

					if (datasList.size() > 0) {
						// System.out.println(i+"-"+j+"-"+plusnum);
						datas[j + plusnum] = ((Element) datasList.get(0))
								.getText();
					} else {
						// System.out.println("no data");
					}

				}
				result.add(datas);

			}

		}
		doc = null;
		msg.setLvalue(result);
		return msg;
	}

	public static byte[] input2byte(InputStream inStream) throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		try{
		String xml = "<xml version>";
		byte[] buff1 = xml.getBytes();
		byte[] buff = new byte[100];
		int rc = 0;
		boolean first = false;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			if (first == false) {
				first = true;
				boolean equal = true;
				byte[] buff2 = new byte[100];
				for (int i = 0; i < buff1.length; i++) {
					byte b = buff1[i];
					if (b != buff[i]) {
						equal = false;
						break;
					}
				}
				if (equal == true) {
					swapStream.write(buff, buff1.length, rc - buff1.length);
				} else {
					swapStream.write(buff, 0, rc);
				}
			} else {
				swapStream.write(buff, 0, rc);
			}

		}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			inStream.close();
			swapStream.close();
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}

}
