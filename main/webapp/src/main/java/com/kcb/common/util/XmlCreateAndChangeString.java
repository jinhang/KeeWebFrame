package com.kcb.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.xml.sax.InputSource;

public class XmlCreateAndChangeString {
	
	public Element CreateElement(String name,Object value) {
		Element element1,element2;
		element1 = new Element(name);
		element2 = element1.setText(value.toString());
		return element2;
	}
	public String OutputToString(Document document){
		ByteArrayOutputStream  byteRep  =  new  ByteArrayOutputStream();  
        XMLOutputter  docWriter  =  new  XMLOutputter();  
        try  {  
            docWriter.output(document,  byteRep);  
        }  
        catch  (Exception  e)  {  
            e.printStackTrace();  
        }  
        System.out.print(byteRep.toString());
        return  byteRep.toString(); 
	 }
	
	public Document readStringXML(String xml){
		StringReader read = new StringReader(xml);
		InputSource source = new InputSource(read);
		SAXBuilder sb = new SAXBuilder();
		try {
			Document doc = sb.build(source);
            return doc;
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	   return null;
	}

}
