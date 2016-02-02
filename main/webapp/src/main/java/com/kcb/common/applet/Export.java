package com.kcb.common.applet;
import java.awt.Graphics;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JApplet;


public class Export  extends JApplet{
	 public String directoryIsExist(String path){
	    	File dir = new File(path);
	    	if(!dir.isDirectory()){
	    		return "-1";
	    	}
	    	return "1";
	    }
	    
	    public String overWriteFile(String path,String remote_path){
	    	File dir = new File(path);
	    	if(!dir.isDirectory()){
	    		return "-1";
	    	}
	    	File file = new File(path+"\\localdata.mdb");
	    	try{
	    		if(file.isFile()){
		    		file.delete();
		    	}
	    		if(file.exists()){
	    			return "-2";
	    		}
	    		String host = this.getParameter("appRoot");
	    		host = host.substring(0, host.substring(0, host.length()-2).lastIndexOf("/"));
	    		System.out.println(host+remote_path);
	    		download(host+remote_path,path+"\\localdata.mdb");
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		return "-3";
	    	}
	    	return "1";
	    }
	    
		public void paint(Graphics g) {
		       g.drawString("称重",20,20);
		    }
		
		public void download(String urlString, String filename) throws Exception{   
	    	URL url = new URL(urlString);
				URLConnection con = url.openConnection();   
		    	InputStream is = con.getInputStream();   
		    	byte[] bs = new byte[1024];   
		    	int len;
		    	File file = new File(filename);
		    
		    	//file.createNewFile();
		    	if (file.exists())
		    		return ;
		    	else 
		    		file.createNewFile();

		    	OutputStream os = new FileOutputStream(filename);   
		    	while ((len = is.read(bs)) != -1) {   
		    		os.write(bs, 0, len);   
		    	}   
		    	os.close();   
		    	is.close();   
	    	
	    }
}
