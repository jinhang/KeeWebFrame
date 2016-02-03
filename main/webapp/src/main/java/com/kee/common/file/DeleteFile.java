package com.kee.common.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;


public class DeleteFile {
	  
	static final String DELETE_PATH = "D://kcb_data/exprotOrder";
	
	
	public void doDelete(){
		File file = new File(DELETE_PATH);
		try {
			if(file.exists())
			FileUtils.deleteDirectory(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void Test(){
		DeleteFile f = new DeleteFile();
		
		f.doDelete();
		
	}

}
