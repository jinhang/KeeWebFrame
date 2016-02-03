package com.kee.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * Excel导出公用类
 * 
 * @author sundonghui
 * 
 */
public class ExcelExportUtils<T> {

	private int columnNum = 10;

	public ExcelExportUtils() {

	}

	public ExcelExportUtils(int columnNum) {

		this.columnNum = columnNum;
	}

	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	private ByteArrayOutputStream exportExcel(String title, Collection<T> dataset)
			throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);

		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);

		// header样式
		HSSFCellStyle style = workbook.createCellStyle();

		// 设置样式
		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_NONE);
		style.setBorderRight(HSSFCellStyle.BORDER_NONE);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLUE.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		// 把字体应用到当前的样式
		style.setFont(font);

		// 单元格样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.WHITE.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		// 把字体应用到当前的样式
		style2.setFont(font2);

		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

		Field[] fields = null;
		Field field = null;
		Method getMethod = null;

		Object value = null;

		String[] relFields = new String[columnNum];

		String[] header = new String[columnNum];

		HSSFRow row = null;
		HSSFCell cell = null;

		Export export = null;

		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();

		int index = 0;// 记录行标志

		int titleIndex = 2;// sheet标志
		try {
			while (it.hasNext()) {
				T t = (T) it.next();

				// 60000行分页
				if (index == 60000) {
					sheet = workbook.createSheet(title + "(" + titleIndex + ")");
					sheet.setDefaultColumnWidth((short) 15);
					index = 0;
					titleIndex++;
				}

				// 第一次设置表头
				if (index == 0) {
					fields = t.getClass().getDeclaredFields();
					for (int i = 0; i < fields.length; i++) {
						field = fields[i];
						export = field.getAnnotation(Export.class);
						if (export != null) {
							header[export.sort()] = export.header();
							relFields[export.sort()] = field.getName();
							sheet.setColumnWidth(export.sort(), export.columnWidth()*50);
						}
					}
					row = sheet.createRow(0);

					for (short i = 0; i < header.length; i++) {
						cell = row.createCell(i);
						cell.setCellStyle(style);
						HSSFRichTextString text = new HSSFRichTextString(
								header[i]);
						cell.setCellValue(text);
					}

				}

				index++;

				row = sheet.createRow(index);

				for (short i = 0; i < relFields.length; i++) {
					if (relFields[i] == null) {
						continue;
					}
					cell = row.createCell(i);
					cell.setCellStyle(style2);
					String fieldName = relFields[i];
					String getMethodName = "get"
							+ fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1);

					Class tCls = t.getClass();

					getMethod = tCls.getMethod(getMethodName, new Class[] {});

					value = getMethod.invoke(t, new Object[] {});

					// 判断值的类型后进行强制类型转换

					String textValue = null;

					if (value instanceof Boolean) {

						boolean bValue = (Boolean) value;

						textValue = "是";

						if (!bValue) {

							textValue = "否";

						}

					} else if (value instanceof Date) {

						Date date = (Date) value;

						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm");

						textValue = sdf.format(date);

					} else if (value instanceof byte[]) {

						// 有图片时，设置行高为60px;
						row.setHeightInPoints(60);

						// 设置图片所在列宽度为80px,注意这里单位的一个换算
						sheet.setColumnWidth(i, (short) (35.7 * 80));

						// sheet.autoSizeColumn(i);
						byte[] bsValue = (byte[]) value;

						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,

						1023, 255, (short) 6, index, (short) 6, index);

						anchor.setAnchorType(2);

						patriarch.createPicture(anchor, workbook.addPicture(

						bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));

					} else if (value != null) {

						// 其它数据类型都当作字符串简单处理

						textValue = value.toString();

					}

					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成

					if (textValue != null && !"null".equals(textValue)) {

						Pattern p = Pattern.compile("^//d+(//.//d+)?$");

						Matcher matcher = p.matcher(textValue);

						if (matcher.matches()) {

							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));

						} else {
							cell.setCellValue(textValue);
						}
					}
				}
			}
			workbook.write(out);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {

			out.flush();
			out.close();

		}

		return out;

	}
	
	/**
	 * 
	 * @param title 导出的名称
	 * @param dataset 数据集合
	 * @param type 1:2003 2:2007
	 * @return
	 * @throws IOException 
	 */
	public InputStream getIs(String title, Collection<T> dataset,int type){
		InputStream is = null;
		ByteArrayOutputStream bos = null;
		try {
			if(type==1){
				bos = this.exportExcel(title, dataset);
			}else if(type==2){
				bos = this.exportExcelBy2007(title, dataset);
			}
			
			is = new ByteArrayInputStream(bos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bos!=null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return is;
		
	}
	
	
	
	@SuppressWarnings("unused")
	private ByteArrayOutputStream exportExcelBy2007(String title, Collection<T> dataset)
			throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		// 声明一个工作薄
		XSSFWorkbook workbook = new XSSFWorkbook();

		// 生成一个表格
		XSSFSheet sheet = workbook.createSheet(title);

		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);

		// header样式
		XSSFCellStyle style = workbook.createCellStyle();

		// 设置样式
		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_NONE);
		style.setBorderRight(HSSFCellStyle.BORDER_NONE);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		XSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLUE.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		// 把字体应用到当前的样式
		style.setFont(font);

		// 单元格样式
		XSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.WHITE.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// 生成另一个字体
		XSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		// 把字体应用到当前的样式
		style2.setFont(font2);

		// 声明一个画图的顶级管理器
		XSSFDrawing patriarch = sheet.createDrawingPatriarch();


		Field[] fields = null;
		Field field = null;
		Method getMethod = null;

		Object value = null;

		String[] relFields = new String[columnNum];

		String[] header = new String[columnNum];

		XSSFRow row = null;
		XSSFCell cell = null;

		Export export = null;

		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();

		int index = 0;// 记录行标志

		int titleIndex = 2;// sheet标志
		try {
			while (it.hasNext()) {
				T t = (T) it.next();

				// 第一次设置表头
				if (index == 0) {
					fields = t.getClass().getDeclaredFields();
					for (int i = 0; i < fields.length; i++) {
						field = fields[i];
						export = field.getAnnotation(Export.class);
						if (export != null) {
							header[export.sort()] = export.header();
							relFields[export.sort()] = field.getName();
							sheet.setColumnWidth(export.sort(), export.columnWidth()*50);
						}
					}
					row = sheet.createRow(0);

					for (short i = 0; i < header.length; i++) {
						cell = row.createCell(i);
						cell.setCellStyle(style);
						XSSFRichTextString text = new XSSFRichTextString(
								header[i]);
						cell.setCellValue(text);
					}

				}

				index++;

				row = sheet.createRow(index);

				for (short i = 0; i < relFields.length; i++) {
					if (relFields[i] == null) {
						continue;
					}
					cell = row.createCell(i);
					cell.setCellStyle(style2);
					String fieldName = relFields[i];
					String getMethodName = "get"
							+ fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1);

					Class tCls = t.getClass();

					getMethod = tCls.getMethod(getMethodName, new Class[] {});

					value = getMethod.invoke(t, new Object[] {});

					// 判断值的类型后进行强制类型转换

					String textValue = null;
					if (value instanceof Boolean) {

						boolean bValue = (Boolean) value;

						textValue = "是";

						if (!bValue) {

							textValue = "否";

						}

					} else if (value instanceof Date) {

						Date date = (Date) value;

						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm");

						textValue = sdf.format(date);

					} else if (value instanceof byte[]) {

						// 有图片时，设置行高为60px;
						row.setHeightInPoints(60);

						// 设置图片所在列宽度为80px,注意这里单位的一个换算
						sheet.setColumnWidth(i, (short) (35.7 * 80));

						// sheet.autoSizeColumn(i);
						byte[] bsValue = (byte[]) value;

						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,

						1023, 255, (short) 6, index, (short) 6, index);

						anchor.setAnchorType(2);

						patriarch.createPicture(anchor, workbook.addPicture(

						bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));

					} else if (value != null) {

						// 其它数据类型都当作字符串简单处理

						textValue = value.toString();

					}

					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成

					if (textValue != null && !"null".equals(textValue)) {

						Pattern p = Pattern.compile("^//d+(//.//d+)?$");

						Matcher matcher = p.matcher(textValue);

						if (matcher.matches()) {

							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));

						} else {
							cell.setCellValue(textValue);
						}
					}
				}
			}
			workbook.write(out);
		} catch (SecurityException e) {
			e.printStackTrace();

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return out;
	}

	public int getColumnNum() {
		return columnNum;
	}

	public void setColumnNum(int columnNum) {
		this.columnNum = columnNum;
	}
	
	
	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	private ByteArrayOutputStream exportAllExcel(List<String> titles, List<Collection<T>> datasets)
			throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		for(int j=0;j<titles.size();j++){
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet(titles.get(j));

			// 设置表格默认列宽度为15个字节
			sheet.setDefaultColumnWidth((short) 15);

			// header样式
			HSSFCellStyle style = workbook.createCellStyle();

			// 设置样式
			style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_NONE);
			style.setBorderRight(HSSFCellStyle.BORDER_NONE);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			HSSFFont font = workbook.createFont();
			font.setColor(HSSFColor.BLUE.index);
			font.setFontHeightInPoints((short) 12);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			// 把字体应用到当前的样式
			style.setFont(font);

			// 单元格样式
			HSSFCellStyle style2 = workbook.createCellStyle();
			style2.setFillForegroundColor(HSSFColor.WHITE.index);
			style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			// 生成另一个字体
			HSSFFont font2 = workbook.createFont();
			font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

			// 把字体应用到当前的样式
			style2.setFont(font2);

			// 声明一个画图的顶级管理器
			HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

			Field[] fields = null;
			Field field = null;
			Method getMethod = null;

			Object value = null;

			String[] relFields = new String[columnNum];

			String[] header = new String[columnNum];

			HSSFRow row = null;
			HSSFCell cell = null;

			Export export = null;

			// 遍历集合数据，产生数据行
			Iterator<T> it = datasets.get(j).iterator();

			int index = 0;// 记录行标志

			int titleIndex = 2;// sheet标志
			try {
				while (it.hasNext()) {
					T t = (T) it.next();

					// 60000行分页
					if (index == 60000) {
						sheet = workbook.createSheet(titles.get(j) + "(" + titleIndex + ")");
						sheet.setDefaultColumnWidth((short) 15);
						index = 0;
						titleIndex++;
					}

					// 第一次设置表头
					if (index == 0) {	
						fields = t.getClass().getDeclaredFields();				
						for (int i = 0; i < fields.length; i++) {
							field = fields[i];
							export = field.getAnnotation(Export.class);
							if (export != null) {
								header[export.sort()] = export.header();
								relFields[export.sort()] = field.getName();
								sheet.setColumnWidth(export.sort(), export.columnWidth()*50);
							}
						}
						row = sheet.createRow(0);

						for (short i = 0; i < header.length; i++) {
							cell = row.createCell(i);
							cell.setCellStyle(style);
							HSSFRichTextString text = new HSSFRichTextString(
									header[i]);
							cell.setCellValue(text);
						}

					}

					index++;

					row = sheet.createRow(index);

					for (short i = 0; i < relFields.length; i++) {
						if (relFields[i] == null) {
							continue;
						}
						cell = row.createCell(i);
						cell.setCellStyle(style2);
						String fieldName = relFields[i];
						String getMethodName = "get"
								+ fieldName.substring(0, 1).toUpperCase()
								+ fieldName.substring(1);

						Class tCls = t.getClass();

						getMethod = tCls.getMethod(getMethodName, new Class[] {});

						value = getMethod.invoke(t, new Object[] {});

						// 判断值的类型后进行强制类型转换

						String textValue = null;

						if (value instanceof Boolean) {

							boolean bValue = (Boolean) value;

							textValue = "是";

							if (!bValue) {

								textValue = "否";

							}

						} else if (value instanceof Date) {

							Date date = (Date) value;

							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm");

							textValue = sdf.format(date);

						} else if (value instanceof byte[]) {

							// 有图片时，设置行高为60px;
							row.setHeightInPoints(60);

							// 设置图片所在列宽度为80px,注意这里单位的一个换算
							sheet.setColumnWidth(i, (short) (35.7 * 80));

							// sheet.autoSizeColumn(i);
							byte[] bsValue = (byte[]) value;

							HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,

							1023, 255, (short) 6, index, (short) 6, index);

							anchor.setAnchorType(2);

							patriarch.createPicture(anchor, workbook.addPicture(

							bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));

						} else if (value != null) {

							// 其它数据类型都当作字符串简单处理

							textValue = value.toString();

						}

						// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成

						if (textValue != null && !"null".equals(textValue)) {

							Pattern p = Pattern.compile("^//d+(//.//d+)?$");

							Matcher matcher = p.matcher(textValue);

							if (matcher.matches()) {

								// 是数字当作double处理
								cell.setCellValue(Double.parseDouble(textValue));

							} else {
								cell.setCellValue(textValue);
							}
						}
					}
				}
				
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		workbook.write(out);
		out.flush();
		out.close();
		return out;

	}
	
	/**
	 * 
	 * @param titles 导出的名称
	 * @param datasets 数据集合
	 * @param type 1:2003 2:2007
	 * @return
	 * @throws IOException 
	 */
	public InputStream getAllIs(List<String> titles, List<Collection<T>> datasets,int type){
		InputStream is = null;
		ByteArrayOutputStream bos = null;
		try {
			if(type==1){
				bos = this.exportAllExcel(titles, datasets);
			}else if(type==2){
				bos = this.exportAllExcelBy2007(titles, datasets);
			}
			
			is = new ByteArrayInputStream(bos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bos!=null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return is;
	}
	
	@SuppressWarnings("unused")
	private ByteArrayOutputStream exportAllExcelBy2007(List<String> titles, List<Collection<T>> datasets)
			throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		// 声明一个工作薄
		XSSFWorkbook workbook = new XSSFWorkbook();

		for(int j=0;j<titles.size();j++){
			// 生成一个表格
			XSSFSheet sheet = workbook.createSheet(titles.get(j));

			// 设置表格默认列宽度为15个字节
			sheet.setDefaultColumnWidth((short) 15);

			// header样式
			XSSFCellStyle style = workbook.createCellStyle();

			// 设置样式
			style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_NONE);
			style.setBorderRight(HSSFCellStyle.BORDER_NONE);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			XSSFFont font = workbook.createFont();
			font.setColor(HSSFColor.BLUE.index);
			font.setFontHeightInPoints((short) 12);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			// 把字体应用到当前的样式
			style.setFont(font);

			// 单元格样式
			XSSFCellStyle style2 = workbook.createCellStyle();
			style2.setFillForegroundColor(HSSFColor.WHITE.index);
			style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			// 生成另一个字体
			XSSFFont font2 = workbook.createFont();
			font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

			// 把字体应用到当前的样式
			style2.setFont(font2);

			// 声明一个画图的顶级管理器
			XSSFDrawing patriarch = sheet.createDrawingPatriarch();


			Field[] fields = null;
			Field field = null;
			Method getMethod = null;

			Object value = null;

			String[] relFields = new String[columnNum];

			String[] header = new String[columnNum];

			XSSFRow row = null;
			XSSFCell cell = null;

			Export export = null;

			// 遍历集合数据，产生数据行
			Iterator<T> it = datasets.get(j).iterator();

			int index = 0;// 记录行标志

			int titleIndex = 2;// sheet标志
			try {
				while (it.hasNext()) {
					T t = (T) it.next();

					// 第一次设置表头
					if (index == 0) {
						fields = t.getClass().getDeclaredFields();
						for (int i = 0; i < fields.length; i++) {
							field = fields[i];
							export = field.getAnnotation(Export.class);
							if (export != null) {
								header[export.sort()] = export.header();
								relFields[export.sort()] = field.getName();
								sheet.setColumnWidth(export.sort(), export.columnWidth()*50);
							}
						}
						row = sheet.createRow(0);

						for (short i = 0; i < header.length; i++) {
							cell = row.createCell(i);
							cell.setCellStyle(style);
							XSSFRichTextString text = new XSSFRichTextString(
									header[i]);
							cell.setCellValue(text);
						}

					}

					index++;

					row = sheet.createRow(index);

					for (short i = 0; i < relFields.length; i++) {
						if (relFields[i] == null) {
							continue;
						}
						cell = row.createCell(i);
						cell.setCellStyle(style2);
						String fieldName = relFields[i];
						String getMethodName = "get"
								+ fieldName.substring(0, 1).toUpperCase()
								+ fieldName.substring(1);

						Class tCls = t.getClass();

						getMethod = tCls.getMethod(getMethodName, new Class[] {});

						value = getMethod.invoke(t, new Object[] {});

						// 判断值的类型后进行强制类型转换

						String textValue = null;
						if (value instanceof Boolean) {

							boolean bValue = (Boolean) value;

							textValue = "是";

							if (!bValue) {

								textValue = "否";

							}

						} else if (value instanceof Date) {

							Date date = (Date) value;

							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm");

							textValue = sdf.format(date);

						} else if (value instanceof byte[]) {

							// 有图片时，设置行高为60px;
							row.setHeightInPoints(60);

							// 设置图片所在列宽度为80px,注意这里单位的一个换算
							sheet.setColumnWidth(i, (short) (35.7 * 80));

							// sheet.autoSizeColumn(i);
							byte[] bsValue = (byte[]) value;

							HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,

							1023, 255, (short) 6, index, (short) 6, index);

							anchor.setAnchorType(2);

							patriarch.createPicture(anchor, workbook.addPicture(

							bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));

						} else if (value != null) {

							// 其它数据类型都当作字符串简单处理

							textValue = value.toString();

						}

						// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成

						if (textValue != null && !"null".equals(textValue)) {

							Pattern p = Pattern.compile("^//d+(//.//d+)?$");

							Matcher matcher = p.matcher(textValue);

							if (matcher.matches()) {

								// 是数字当作double处理
								cell.setCellValue(Double.parseDouble(textValue));

							} else {
								cell.setCellValue(textValue);
							}
						}
					}
				}
			} catch (SecurityException e) {
				e.printStackTrace();

			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		workbook.write(out);
		out.flush();
		out.close();
		return out;
	}


}
