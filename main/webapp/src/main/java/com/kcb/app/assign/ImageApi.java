package com.kcb.app.assign;

import java.io.File;
import java.io.FileOutputStream;

import sun.misc.BASE64Decoder;

import com.kcb.common.constant.Constants;
import com.wfs.task.Msg;

public class ImageApi {
	
	public static Msg<String> saveImage(String filename, String image,String path) {   
		Msg<String> msg = new Msg<String>();
        FileOutputStream fos = null;    
        try{    
            String toDir = Constants.EWP_LOCAL_URL+path;   //存储路径    
            byte[] buffer = new BASE64Decoder().decodeBuffer(image);   //对android传过来的图片字符串进行解码     
            File destDir = new File(toDir);      
            if(!destDir.exists()) {  
                destDir.mkdir();    
            }  
            fos = new FileOutputStream(new File(destDir,filename));   //保存图片    
            fos.write(buffer);    
            fos.flush();    
            fos.close(); 
            msg.setResult(true);
            msg.setValue(Constants.EWP_PICTURE_URL+path+"/"+filename);
        }catch (Exception e){    
            e.printStackTrace();   
            msg.setResult(false);
        }    
        return msg;    
    }  
	
	
	public static Msg<String> saveRecords(String filename, String records,String path) {   
		Msg<String> msg = new Msg<String>();
        FileOutputStream fos = null;    
        try{    
            String toDir = Constants.EWP_LOCAL_URL+path;   //存储路径    
            byte[] buffer = new BASE64Decoder().decodeBuffer(records);   //对android传过来的图片字符串进行解码     
            File destDir = new File(toDir);      
            if(!destDir.exists()) {  
                destDir.mkdir();    
            }  
            fos = new FileOutputStream(new File(destDir,filename));   //保存图片    
            fos.write(buffer);    
            fos.flush();    
            fos.close(); 
            msg.setResult(true);
            msg.setValue(Constants.EWP_PICTURE_URL+path+"/"+filename);
        }catch (Exception e){    
            e.printStackTrace();   
            msg.setResult(false);
        }    
        return msg;    
    }  

}
