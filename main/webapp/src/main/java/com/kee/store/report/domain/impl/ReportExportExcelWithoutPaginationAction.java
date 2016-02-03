package com.kee.store.report.domain.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpSession;

import com.kee.common.session.DwrGetSession;
import com.kee.common.util.FileUtil;
import com.kee.engine.wrapper.model.Msg;
import com.runqian.report4.model.ReportDefine;
import com.runqian.report4.usermodel.Context;
import com.runqian.report4.usermodel.CustomDataSetConfig;
import com.runqian.report4.usermodel.DataSetConfig;
import com.runqian.report4.usermodel.DataSourceConfig;
import com.runqian.report4.usermodel.Engine;
import com.runqian.report4.usermodel.IReport;
import com.runqian.report4.util.ReportUtils;
import com.taobao.api.internal.jushita.stream.Report;
import com.wfos.engine.common.WebConfig;
import com.wfos.engine.report.ReportTaskDataSet;
import com.wfos.engine.transfer.Tasks;

/**
 * @author jyl
 * @version 创建时间：2013-7-12 上午10:10:20
 * 类说明
 */
public class ReportExportExcelWithoutPaginationAction extends Tasks {

	@Override
	public Msg execute(Map arg0, Map<String, Msg> arg1, Msg arg2)
			throws Exception {
		// TODO Auto-generated method stub
		
		HashMap map = (HashMap) arg0.get("map");
		
		String file = (String) map.get("file");
		
		HttpSession session = DwrGetSession.getHttpSession();
		map.put("session_current_user_id", session.getAttribute("session_current_user_id"));
		map.put("session_current_client_id", session.getAttribute("session_current_client_id"));
		
		
		Msg msg  = new Msg(); 
		
		String filepath = session.getServletContext().getRealPath("")+File.separator+"zipdownload";
		String clientPath = filepath+File.separator+session.getAttribute("session_current_user_id");
		
		Integer total = (Integer) map.get("total");
		
		if(total==0){
			return msg;
		}
		Integer first = 0;
		Integer limit = 60000; 
		
		File dirFile = null;  
		
		try {  
		   dirFile = new File(clientPath);  
		   FileUtil.deleteDir(dirFile);
		   if (!(dirFile.exists()) && !(dirFile.isDirectory())) {  
		    dirFile.mkdirs();  
		   }
		  }catch (Exception e) {
			// TODO: handle exception
		}

		String[] files = new String[(total-1)/limit+1];
		
		while(first<total){
			map.put("first", first);
			map.put("limit", limit);
			Context context = new Context();
			context.setHttpSession(session);
			context.setParamMap(map);
			String path = session.getServletContext().getRealPath("")+File.separator+"WEB-INF"+File.separator+"classes"+File.separator+"rqFiles"+File.separator+file;
			
			ReportDefine rd = (ReportDefine) ReportUtils.read(path);
			Engine engine = new Engine(rd,context);
			IReport ir = engine.calc();
			try {
				String filename = System.currentTimeMillis()+"_"+(first/limit+1);
				files[first/limit] = clientPath+File.separator+filename+".xls";
				ReportUtils.exportToExcel(files[first/limit],ir,false);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			first +=limit;
			if(first>=total){
				long time = System.currentTimeMillis();
				String zip = clientPath+File.separator+time+".zip";
				Zip(files,zip);
				msg.setSvalue(""+session.getAttribute("session_current_user_id")+"/"+time+".zip");
			}
		}
		return msg;
	}
	
	/**
	 * 打包
	 * @param files
	 * @param zipName
	 * @throws Exception
	 */
	public void Zip(String[] files,String zipName) throws Exception{
		  
		byte[] buffer = new byte[1024];
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipName));
		try{
			 for(int i=0;i<files.length;i++) {
				  File file = new File(files[i]);
				  FileInputStream fis = new FileInputStream(file);
				  try{
					  out.putNextEntry(new ZipEntry(file.getName()));
					  int len;
					  //读入需要下载的文件的内容，打包到zip文件
					  while((len = fis.read(buffer))>0) {
						  out.write(buffer,0,len);
					  }
				  }catch (Exception e) {
					  // TODO: handle exception
					  throw e;
				  }finally{
					  out.closeEntry();
					  fis.close();
				  }
			   }
		  }catch (Exception e) {
			  throw e;
		  }finally{
			  out.close();
		  }
	}

}
