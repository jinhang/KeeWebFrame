package com.wfs.engine.common;




import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.wfos.engine.common.NoOpEntityResolver;
import com.wfos.engine.common.PropertiesFactory;


public class SystemInitServlet extends HttpServlet  {
private Logger log = Logger.getLogger(this.getClass());
private BasicDataSource datasource;
public static final String DATABASE_TYPE_ORACLE = "oracle";
public static final String DATABASE_TYPE_MSSQL = "mssql";
public static final String DATABASE_TYPE_DB2 = "db2";
public static final String DATABASE_TYPE_MYSQL = "mysql";
public static final String DATABASE_TYPE_POSTGRES = "postgres";
public static final String DATABASE_TYPE_SYBASE = "sybase";
public static final String DATABASE_TYPE_INGRES = "ingres";
public void init(ServletContext servletcontext) throws ServletException {
		System.out.println("开始生成配置文件...");
		File file = getJdbcProperties();
		Enumeration en = servletcontext.getInitParameterNames();
		System.out.println("是否有配置："+en.hasMoreElements());
		for (Enumeration e = en; e.hasMoreElements();) {
			String thisName = e.nextElement().toString().trim();
			String thisValue = servletcontext.getInitParameter(thisName).trim();
			log.info("name:"+thisName+";value:"+thisValue);
			writeData(thisName, thisValue, file);
		}
		
		
		PropertiesFactory pf1 = new PropertiesFactory(file);
		createDiffUrlAndSoOn(pf1.getParam("database_type"),pf1.getParam("ip"),pf1.getParam("database"),pf1.getParam("server_name"),file);
		System.out.println("生成配置文件完成...");
		PropertiesFactory pf = new PropertiesFactory(file);
		//更改日志路径
		//changeLogByUser(pf.getParam("log_file"),"A2");
		//changeLogByUser(pf.getParam("user_log_file"),"A3");
		//检查创建初始化数据库及liquibase解锁
		createInitDb(pf);
		changeReportConfig(pf);
		
		
	}
	
/**
 *
 */
private void changeReportConfig(PropertiesFactory pf) {
	File config_file =new File(getClassPath(), "reportConfig.xml");
	Document doc = readerDom(config_file);
	Element root = doc.getRootElement();
	//更改表报home路径
	Node oCfg = root.selectSingleNode("config[name='reportFileHome']/value");
	if(pf.getParam("reportFileHome")!=null){
		oCfg.setText(pf.getParam("reportFileHome"));
		File file = new File(pf.getParam("reportFileHome"));
		if(!file.exists()){
			file.mkdirs();
		}
	}
	//更改表报license路径
	Node license = root.selectSingleNode("config[name='license']/value");
	if(pf.getParam("license")!=null){
		license.setText(pf.getParam("license"));
	}
	writeConfig(doc.asXML(), config_file);
}

private Document readerDom(File flie){
	Document doc = null;
	SAXReader reader = new SAXReader();
	reader.setValidation(false);
	reader.setEntityResolver(new NoOpEntityResolver());
	try {
		doc = reader.read(flie);
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return doc;
}


/**
 * 更改日志路径
 */
private void changeLogByUser(String new_log_file_name,String apperder_name) {
	new_log_file_name = new_log_file_name.replace("\\", "/");
	new_log_file_name = new_log_file_name.replace("//", "/");
	File new_log_file = new File(new_log_file_name.substring(0, new_log_file_name.lastIndexOf("/")));
	if(!new_log_file.exists()){
		new_log_file.mkdirs();
	}
	DailyRollingFileAppender  drfa = (DailyRollingFileAppender) log.getRootLogger().getAppender(apperder_name);
	String log_file_name = drfa.getFile();
	try {
		drfa.setFile(new_log_file_name,true,drfa.getBufferedIO(),drfa.getBufferSize());
		drfa.activateOptions();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	if (log_file_name != null){
		File log_file = new File(log_file_name);
		if(log_file.exists()){
			log_file.delete();
		}
	}
	
	
//	File log_config_file = new File(getClassPath(), "log4j.xml");
//	Document doc = readerDom(log_config_file);
//	Element root = doc.getRootElement();
//	Element node =(Element) root.selectSingleNode("appender[@name='A2']/param[@name='File']");
//	node.attribute("value").setValue(new_log_file_name);
//	writeConfig(doc.asXML(), log_config_file);
}




/**
 * 获取各个数据库的url
 * @param databaseType
 * @param initParameter
 * @param initParameter2
 * @return
 */
private void createDiffUrlAndSoOn(String databaseType, String ip,
		String database,String server_name,File file) {
	writeData("url", getDiffUrl( databaseType,  ip, database,server_name), file);
	writeData("driverClassName", getDiffDriver( databaseType), file);
	writeData("sqldialect", getDiffSqldialect(databaseType), file);
}



private String getDiffDefaultDatabase(String databaseType) {
	String database = "";
	if(databaseType.compareTo(DATABASE_TYPE_ORACLE)==0){
		database = "";
	}else if(databaseType.compareTo(DATABASE_TYPE_DB2)==0){
		database = "";
	}else if(databaseType.compareTo(DATABASE_TYPE_MSSQL)==0){
		database = "pubs";
	}else if(databaseType.compareTo(DATABASE_TYPE_MYSQL)==0){
		database = "test";
	}else if(databaseType.compareTo(DATABASE_TYPE_POSTGRES)==0){
		database = "postgres";
	}else if(databaseType.compareTo(DATABASE_TYPE_SYBASE)==0){
		database = "master";
	}else if(databaseType.compareTo(DATABASE_TYPE_INGRES)==0){
		database = "rrp";
	}
	return database;
}


	/**
 * @param databaseType
 * @return
 */
private String getDiffSqldialect(String databaseType) {
	String sqldialect = "";
	if(databaseType.compareTo(DATABASE_TYPE_ORACLE)==0){
		sqldialect = "com.wfos.engine.common.sqldialect.OracleDialect";
	}else if(databaseType.compareTo(DATABASE_TYPE_DB2)==0){
		sqldialect = "com.wfos.engine.common.sqldialect.Db2SQLDialect";
	}else if(databaseType.compareTo(DATABASE_TYPE_MSSQL)==0){
		sqldialect = "com.wfos.engine.common.sqldialect.SQLServer2KDialect";
	}else if(databaseType.compareTo(DATABASE_TYPE_MYSQL)==0){
		sqldialect = "com.wfos.engine.common.sqldialect.MysqlDialect";
	}else if(databaseType.compareTo(DATABASE_TYPE_POSTGRES)==0){
		sqldialect = "com.wfos.engine.common.sqldialect.PostgreDialect";
	}else if(databaseType.compareTo(DATABASE_TYPE_SYBASE)==0){
		sqldialect = "com.wfos.engine.common.sqldialect.SybaseASEDialect";
	}
	return sqldialect;
}

	/**
 * @param databaseType
 * @return
 */
private String getDiffDriver(String databaseType) {
	String driverClassName = "";
	if(databaseType.compareTo(DATABASE_TYPE_ORACLE)==0){
		driverClassName = "oracle.jdbc.driver.OracleDriver";
	}else if(databaseType.compareTo(DATABASE_TYPE_DB2)==0){
		driverClassName = "com.ibm.db2.jcc.DB2Driver";
	}else if(databaseType.compareTo(DATABASE_TYPE_MSSQL)==0){
		driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	}else if(databaseType.compareTo(DATABASE_TYPE_MYSQL)==0){
		driverClassName = "org.gjt.mm.mysql.Driver";
	}else if(databaseType.compareTo(DATABASE_TYPE_POSTGRES)==0){
		driverClassName = "org.postgresql.Driver";
	}else if(databaseType.compareTo(DATABASE_TYPE_SYBASE)==0){
		driverClassName = "com.sybase.jdbc2.jdbc.SybDriver";
	}else if(databaseType.compareTo(DATABASE_TYPE_INGRES)==0){
		driverClassName = "com.ingres.jdbc.IngresDriver";
	}
	return driverClassName;
}



/**
 * @param databaseType
 * @param ip
 * @param database
 * @return
 */
private String getDiffUrl(String databaseType, String ip, String database,String server_name) {
	String url = "";
	if(databaseType.compareTo(DATABASE_TYPE_ORACLE)==0){
		url = "jdbc:oracle:thin:@"+ip+":"+database;
	}else if(databaseType.compareTo(DATABASE_TYPE_DB2)==0){
		url = "jdbc:db2://"+ip+"/"+database;
	}else if(databaseType.compareTo(DATABASE_TYPE_MSSQL)==0){
		url = "jdbc:sqlserver://"+ip+";database="+database+";";
		if(server_name.compareToIgnoreCase("") != 0){
			url += "Data Source="+server_name+";";
		}
	}else if(databaseType.compareTo(DATABASE_TYPE_MYSQL)==0){
		url = "jdbc:mysql://"+ip+"/"+database;
	}else if(databaseType.compareTo(DATABASE_TYPE_POSTGRES)==0){
		url = "jdbc:postgresql://"+ip+"/"+database;
	}else if(databaseType.compareTo(DATABASE_TYPE_SYBASE)==0){
		url = "jdbc:sybase:Tds:"+ip+"/"+database;
	}else if(databaseType.compareTo(DATABASE_TYPE_INGRES)==0){
		url = "jdbc:ingres://"+ip+"/"+database;
	}
	return url;
}

	private void createInitDb(PropertiesFactory pf){
		this.datasource = new BasicDataSource();
		this.datasource.setDriverClassName(pf.getParam("driverClassName"));
		this.datasource.setUrl(pf.getParam("url"));
		this.datasource.setUsername(pf.getParam("username"));
		this.datasource.setPassword(pf.getParam("password"));
		Connection conn = null;
		try {
			conn = this.datasource.getConnection();
		} catch (SQLException e2) {
			log.info("自定义数据库连接失败...");
		}
		if (conn == null) {
			// 连接默认数据库
			BasicDataSource default_ds = new BasicDataSource();
			default_ds.setUrl(getDiffUrl(pf.getParam("database_type"), pf.getParam("ip"), getDiffDefaultDatabase(pf.getParam("database_type")), pf.getParam("server_name")));
			default_ds.setPassword(pf.getParam("password"));
			default_ds.setUsername(pf.getParam("username"));
			default_ds.setDriverClassName(pf.getParam("driverClassName"));
			try {
				conn = default_ds.getConnection();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				
				e1.printStackTrace();
			}
			if (conn == null) {
				throw new RuntimeException("数据库默认库连接失败...请检查连接状态");
			} else {
				String sql = "CREATE DATABASE " + pf.getParam("database");
				log.info(sql);
				try {
					conn.createStatement().execute(sql);
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.info("创建自定义数据库失败...");
					throw new RuntimeException(e);
				}
				try {
					conn = this.datasource.getConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		String update_sql = "delete from databasechangeloglock";
		try {
			conn.createStatement().execute(update_sql);
			conn.close();
		} catch (SQLException e) {
		}
		//h2 liquibase解锁
		BasicDataSource h2_ds = new BasicDataSource();
		h2_ds.setUrl("jdbc:h2:file:"+pf.getParam("file.path")+"standard;FILE_LOCK=NO");
		h2_ds.setPassword("");
		h2_ds.setUsername("sa");
		h2_ds.setDriverClassName("org.h2.Driver");
		try {
			conn = h2_ds.getConnection();
			conn.createStatement().execute(update_sql);
			conn.close();
		} catch (SQLException e) {
		}
	}
	
	private String getClassPath(){
		
		try {
			String url = this.getClass().getClassLoader().getResource("applicationContext.xml").getPath(); 
			log.info("文件路径:"+url);
			url = java.net.URLDecoder.decode(url,"utf-8");
			return url.substring(0,url.lastIndexOf("/"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		return "";
	}
	
	public File getJdbcProperties() {
		String url = getClassPath(); 
		File file = new File(url, "jdbc.properties");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return file;
	}

	private void writeConfig(String str, File outfile) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(outfile)));
			out.write(str);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BasicDataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(BasicDataSource datasource) {
		this.datasource = datasource;
	}
	
    public static void writeData(String key, String value,File file) {  
        Properties prop = new Properties();   
        try {   
            if (!file.exists())   
                file.createNewFile();   
            InputStream fis = new FileInputStream(file);   
            prop.load(fis);   
            fis.close();//一定要在修改值之前关闭fis   
            OutputStream fos = new FileOutputStream(file);   
            prop.setProperty(key, value);   
            prop.store(fos, "Update '" + key + "' value");   
            fos.close();   
        } catch (IOException e) {   
        	e.printStackTrace();
            System.err.println("Visit " + file + " for updating "  
                    + value + " value error");   
        }   
    }
}