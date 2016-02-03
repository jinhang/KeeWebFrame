package com.kcb.common.liquibase;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kee.common.date.DateUtil;


public class GenerateChangelogService {
	static public Connection getHBConnect() throws SQLException {
		Connection con;

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		con = DriverManager.getConnection(
				"jdbc:postgresql://127.0.0.1:5432/yw1", "root",
				"123456");
		List<String> Tables_list = new ArrayList<String>();
//		Tables_list.add("badproduct");
//		Tables_list.add("barcodebg");
//		Tables_list.add("barcodeclass");
//		Tables_list.add("barcodeparams");
//		Tables_list.add("barcodetype");
//		Tables_list.add("buyershop");
//		Tables_list.add("cclient");
//		Tables_list.add("cclientcare");
//		Tables_list.add("cctype");
//		Tables_list.add("change");
//		Tables_list.add("changedetail");
		Tables_list.add("client");
//		Tables_list.add("clientforwardcompany");
//		Tables_list.add("clientforwardpriceinfo");
//		Tables_list.add("clientforwardpricetemp");
//		Tables_list.add("clientinitparam");
//		Tables_list.add("clientwarehouse");
//		Tables_list.add("collect");
//		Tables_list.add("collecttype");
		Tables_list.add("company");
//		Tables_list.add("databasechangelog");
//		Tables_list.add("databasechangeloglock");
//		Tables_list.add("defective");
//		Tables_list.add("department");
//		Tables_list.add("dispatchcodetemplate");
//		Tables_list.add("expenditure");
//		Tables_list.add("expendtype");
//		Tables_list.add("expressbarcode");
//		Tables_list.add("expresstemplate");
//		Tables_list.add("financeobjid");
//		Tables_list.add("forwardarea");
//		Tables_list.add("forwardcompany");
//		Tables_list.add("forwardcustom");
//		Tables_list.add("forwardoutlets");
//		Tables_list.add("forwardrecord");
//		Tables_list.add("goodscommontype");
//		Tables_list.add("goodsselftype");
//		Tables_list.add("goodsshop");
//		Tables_list.add("goodswhitem");
//		Tables_list.add("instock");
//		Tables_list.add("instockdetail");
//		Tables_list.add("instockflow");
//		Tables_list.add("instocklog");
//		Tables_list.add("inventory");
//		Tables_list.add("inventoryapp");
//		Tables_list.add("inventoryappgoods");
//		Tables_list.add("inventorydetail");
//		Tables_list.add("inventorygoods");
//		Tables_list.add("invoice");
//		Tables_list.add("itemplan");
//		Tables_list.add("itemprops");
//		Tables_list.add("kcbproduct");
//		Tables_list.add("kcbproductprop");
//		Tables_list.add("layer");
//		Tables_list.add("leave");
//		Tables_list.add("listingplan");
//		Tables_list.add("material");
//		Tables_list.add("memory");
//		Tables_list.add("orderflow");
//		Tables_list.add("orderlog");
//		Tables_list.add("orderrelation");
//		Tables_list.add("outstock");
//		Tables_list.add("outstockdetail");
//		Tables_list.add("outstockflow");
//		Tables_list.add("outstocklog");
//		Tables_list.add("outstockres");
//		Tables_list.add("outstockresgoods");
//		Tables_list.add("package");
//		Tables_list.add("paper");
//		Tables_list.add("paydetail");
//		Tables_list.add("permission");
//		Tables_list.add("plantime");
//		Tables_list.add("product");
//		Tables_list.add("propvalue");
//		Tables_list.add("prureturn");
//		Tables_list.add("purchase");
//		Tables_list.add("purchasedetail");
//		Tables_list.add("purreturndetail");
//		Tables_list.add("quantityx");
//		Tables_list.add("replenish");
//		Tables_list.add("replenishdetail");
//		Tables_list.add("replenishlog");
//		Tables_list.add("return");
//		Tables_list.add("returndetail");
		Tables_list.add("role");
		Tables_list.add("rolepermission");
//		Tables_list.add("scrap");
//		Tables_list.add("scrapd");
//		Tables_list.add("shelf");
//		Tables_list.add("shiftwarehouse");
//		Tables_list.add("shiftwhapp");
//		Tables_list.add("shiftwhdetail");
//		Tables_list.add("shop");
//		Tables_list.add("shopex_goods");
//		Tables_list.add("shopex_order_items");
//		Tables_list.add("shopex_orders");
//		Tables_list.add("shopex_products");
//		Tables_list.add("smstemplate");
		Tables_list.add("staff");
		Tables_list.add("staffpermission");
//		Tables_list.add("staffshop");
//		Tables_list.add("staffwarehouse");
//		Tables_list.add("stockfreezelog");
//		Tables_list.add("stockinfo");
//		Tables_list.add("supplier");
//		Tables_list.add("taobao_order");
//		Tables_list.add("taobao_order_detail");
//		Tables_list.add("taobaoitem");
//		Tables_list.add("taobaoitemcat");
//		Tables_list.add("taobaosku");
//		Tables_list.add("taobaostaff");
//		Tables_list.add("telmsg");
//		Tables_list.add("templateparams");
//		Tables_list.add("test");
//		Tables_list.add("warehouse");
//		Tables_list.add("warehousewharea");
//		Tables_list.add("wharea");
//		Tables_list.add("whitem");
//		Tables_list.add("whitemtype");
//		Tables_list.add("whtype");

		
		
		OutputStreamWriter output = null;
		int ver = 1000;
		for (String tablename : Tables_list) {
			String sql = "select * from " + tablename;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rst = ps.executeQuery();
			ResultSetMetaData rsmd = rst.getMetaData();

			int count = rsmd.getColumnCount();
			try {
				output = new OutputStreamWriter(new FileOutputStream("d:\\liquibase\\"
						+ tablename + ".changelog.xml"), "utf-8");

				output
						.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n"
								+ "<databaseChangeLog xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog/1.9\" "
								+ "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
								+ "xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog/1.9 "
								+ "http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-1.9.xsd\">\r\n"
								+ "<changeSet author=\"Administrator (generated)\" id=\""+System.currentTimeMillis()+"-"
								+ (ver++) + "\">");

				while (rst.next()) {

					output.write("<insert tableName=\"" + tablename + "\">\r\n");
					for (int i = 0; i < count; i++) {
						String colName = rsmd.getColumnName(i + 1);
						String colType = rsmd.getColumnTypeName(i + 1);
						String valuest = "";
						if (colType.equalsIgnoreCase("varchar")) {
							valuest = "value";
						} else if (colType.startsWith("int") || colType.equalsIgnoreCase("numeric")) {
							valuest = "valueNumeric";
						} else if(colType.equalsIgnoreCase("date") || colType.equalsIgnoreCase("datetime")||colType.startsWith("timestamp") ) {
							valuest = "valueDate";
						} else {
							valuest = "value";
						}
						
						String value = rst.getString(i + 1);
						if(null != value && value.compareToIgnoreCase("")!=0){
							if(colType.startsWith("timestamp")){
								value = DateUtil.getCurrDateStr(DateUtil.formart3, rst.getDate(i + 1)) ;
							}
								output.write("<column name=\"" + colName + "\" " + valuest + "=\"" + value + "\"/>\r\n");
						}
					}
					// table = table + column + "</insert>\r\n";
					output.write("</insert>\r\n");

				}

				output.write("</changeSet></databaseChangeLog>\r\n");

				rst.close();
				ps.close();

				output.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static void main(String[] args) {
		try {
			getHBConnect();
			System.out.print("ok");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
