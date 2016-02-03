package com.kee.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class DataFile {
	  
	/**
	 * 文件复制功能
	 * @param from    起始文件路径
	 * @param to                  目标文件路径
	 * @return size     文件复制过程中操作的字节数
	 * @throws IOException
	 */
	public static long copyFile(File from, File to) throws IOException {
		if (from == null || to == null || !from.isFile())
			return -1;
		FileInputStream fis = new FileInputStream(from);
		FileOutputStream fos = new FileOutputStream(to);
		byte[] buff = new byte[1024];
		int read = 0;
		long size = 0;
		while ((read = fis.read(buff)) > 0) {
			fos.write(buff, 0, read);
			size += read;
		}
		fos.close();
		fis.close();
		return size;
	}
	

}
