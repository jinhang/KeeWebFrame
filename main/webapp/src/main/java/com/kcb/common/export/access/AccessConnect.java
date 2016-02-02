package com.kcb.common.export.access;

import java.sql.*;
import org.apache.log4j.Logger;
public class AccessConnect {
	private String driverClassName = "com.caigen.sql.access.AccessDriver";
	private String username = null;
	private String password = null;
	private String url = null;
	


	private Logger log = Logger.getLogger(this.getClass());
	public String getUrl() {
		return url;
	}


	public  String getDriverClassName() {
		return driverClassName;
	}


	public  String getUsername() {
		return username;
	}


	public  String getPassword() {
		return password;
	}

	
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	

	public Connection getConnect(String data_base_name) {
		String all_url = "jdbc:access:///"+data_base_name;
		if(data_base_name == null){
			all_url = url;
		}
		Connection conn = null;
		try {
			Class.forName(driverClassName).newInstance();
			log.info("开始连接access数据库");
			conn = DriverManager.getConnection(all_url,username, password);
			log.info("连接access数据库完成");
			//Statement stmt = conn.createStatement();
		} catch (Exception e) {
			System.out.println("操作数据库出错，请仔细检查");
			System.err.println(e.getMessage());
		}
		return conn;
	}

}