package com.kee.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 文件操作工具类
 * 功能介绍：实现单个文件的复制、移动、文件夹及子文件的复制、移动、删除
 * @author jiangyongliang
 * 
 */
public class FileUtil {

	public static int BF_SIZE = 2048;

	/**
	 * 复制src目录下的所有文件到dist目录
	 * 
	 * @param src
	 * @param dist
	 * @return
	 * @throws Exception
	 */
	public static boolean copy(File src, File dist) throws Exception {
		boolean flag = false;
		if (src.exists()) {
			if (src.isDirectory()) {
				// 如果目标路径在源路径里，则不也许拷贝
				if (dist.getPath().indexOf(src.getPath()) != -1) {
					return false;
				}
				dist.mkdirs();
				File[] fs = src.listFiles();
				for (File f : fs) {
					copy(f,
							new File(dist.getPath() + File.separator
									+ f.getName()));
				}
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 复制单个src文件到目标dest文件
	 * 
	 * @param fromFile
	 * @param toFile
	 * @throws Exception
	 */
	public static boolean copyTo(String src, String dest) throws Exception {
		boolean flag = false;
		File file = new File(src);
		if (file.exists()) {
			File f = new File(dest);
			File dir = f.getParentFile();
			if (!dir.exists()) {
				f.mkdirs();
			}
			f.createNewFile();
			FileInputStream fis = new FileInputStream(src);
			FileOutputStream fos = new FileOutputStream(dest);
			byte[] bs = new byte[1024];
			int len = -1;
			while ((len = fis.read(bs)) != -1) {
				fos.write(bs, 0, len);
			}
			fos.close();
			fis.close();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录及该目录下的所有文件
	 * 
	 * @param dir
	 */
	public static void deleteDir(File dir) {
		if (dir.exists()) {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					deleteDir(file);
				} else {
					file.delete();
				}
			}
			dir.delete();
		}
	}
	
	/**
	 * 删除该目录下的所有文件
	 * 
	 * @param dir
	 */
	public static void deleteSubDir(File dir) {
		if (dir.exists()) {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					deleteDir(file);
				} else {
					file.delete();
				}
			} 
		}
	}

	/**
	 * 获取后缀名
	 * 
	 * @param file_name
	 * @return
	 */
	public static String getFileExtension(String file_name) {
		String return_value = file_name.substring(
				file_name.lastIndexOf(".") == -1 ? 0 : file_name
						.lastIndexOf(".")).toLowerCase();
		return return_value;
	}

	/**
	 * 获取重UUID新命名的32位文件名
	 * 
	 * @param file_name
	 * @return
	 */
	public static String getUUIDName(String file_name) {
		return UUID.randomUUID().toString().replace("-", "")
				+ getFileExtension(file_name);
	}

	/**
	 * 移动指定文件夹内的全部文件
	 * 
	 * @param from
	 * @param to
	 * @throws Exception
	 */
	public static void moveAllTo(String from, String to) throws Exception {
		try {
			File dir = new File(from);
			File[] files = dir.listFiles();// 将文件或文件夹放入文件集
			if (files == null)// 判断文件集是否为空
				return;
			File moveDir = new File(to);// 创建目标目录
			if (!moveDir.exists()) {// 判断目标目录是否存在
				moveDir.mkdirs();// 不存在则创建
			}
			for (int i = 0; i < files.length; i++) {// 遍历文件集
				if (files[i].isDirectory()) {// 如果是文件夹或目录,则递归调用fileMove方法，直到获得目录下的文件
					moveAllTo(files[i].getPath(),
							to + "\\" + files[i].getName());// 递归移动文件
					files[i].delete();// 删除文件所在原目录
				}
				File moveFile = new File(moveDir.getPath() + "\\"// 将文件目录放入移动后的目录
						+ files[i].getName());
				if (moveFile.exists()) {// 目标文件夹下存在的话，删除
					moveFile.delete();
				}
				files[i].renameTo(moveFile);// 移动文件
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 移动单个文件到指定文件
	 * 
	 * @param from
	 * @param to
	 */
	public static void moveTo(String from, String to) {
		File file = new File(from);
		File toFile = new File(to);
		file.renameTo(toFile);
	}

	/**
	 * 读取文件内容
	 * 
	 * @param path
	 * @return
	 */
	public static String readFile(String path) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			try {
				StringBuilder builder = new StringBuilder();
				char[] ch = new char[BF_SIZE];
				for (int i = 0; (i = reader.read(ch)) != -1;) {
					builder.append(ch, 0, i);
				}
				return builder.toString();
			} finally {
				reader.close();
			}
		} catch (FileNotFoundException e) {
			// logger.warn("文件未找到！：" + path, e);
		} catch (IOException e) {
			// logger.warn("IO异常！：" + path, e);
		}
		return "";
	}

	/**
	 * 将文本内容写入文件中
	 * 
	 * @param path
	 * @param name
	 * @param content
	 */
	public void writeFile(String path, String name, String content) {
		if (content == null) {
			content = "";
		}
		try {
			File f = new File(path);
			if (!f.exists()) {
				f.mkdirs();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(path
					+ name));
			try {
				writer.write(content);
			} finally {
				writer.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
