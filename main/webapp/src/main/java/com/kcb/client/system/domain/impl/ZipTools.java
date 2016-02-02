package com.kcb.client.system.domain.impl;
import java.io.*;
import java.util.Enumeration;

import org.apache.log4j.Logger;
import org.apache.tools.zip.*;
public class ZipTools{
  private static Logger log = Logger.getLogger("压缩文件日志记录");
	
  private static final int BUFFEREDSIZE = 1024;
 
  public static  void zip(String inputFilename) throws IOException {   
       String outFileName= inputFilename+".zip";
            zip(inputFilename,outFileName);    
    }  
 /**
     * 压缩zip格式的压缩文件
     * @param inputFilename 压缩的文件或文件夹及详细路径
     * @param zipFilename 输出文件名称及详细路径
     * @throws IOException
     */
    public static  void zip(String inputFilename, String zipFilename) throws IOException {    
        zip(new File(inputFilename), zipFilename);    
    }    
    
    /**
     * 压缩zip格式的压缩文件
     * @param inputFile 需压缩文件
     * @param zipFilename 输出文件及详细路径
     * @throws IOException
     */
    public static  void zip(File inputFile, String zipFilename) throws IOException {    
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFilename));
        try {
        	int pos1 = zipFilename.lastIndexOf("/");
        	int pos2 = zipFilename.lastIndexOf(".");
        	String base = zipFilename.substring(pos1+1,pos2);
            zip(inputFile, out, base);    
        } catch (IOException e) {    
            throw e;    
        }  
    }
    /**
     * 压缩zip格式的压缩文件
     * @param inputFile 需压缩文件
     * @param out 输出压缩文件
     * @param base 结束标识
     * @throws IOException
     */
    private static  void zip(File inputFile, ZipOutputStream out, String base) throws IOException {
        if (inputFile.isDirectory()) {    //如果是目录
            File[] inputFiles = inputFile.listFiles();    
            out.putNextEntry(new ZipEntry(base + "/"));    
            base = base.length() == 0 ? "" : base + "/";    
            for (int i = 0; i < inputFiles.length; i++) {    
                zip(inputFiles[i], out, base + inputFiles[i].getName());    
            }
        } else {    //如果是文件
            if (base.length() > 0) {    
                out.putNextEntry(new ZipEntry(base));    
            } else {    
                out.putNextEntry(new ZipEntry(inputFile.getName()));    
            }
            FileInputStream in = new FileInputStream(inputFile);    
            try {    
                int len;    
                byte[] buff = new byte[BUFFEREDSIZE];    
                while ((len = in.read(buff)) != -1) {    
                    out.write(buff, 0, len);    
                }    
            } catch (IOException e) {    
                throw e;    
            } finally {
                in.close();    
                out.close();
            }    
        }    
    }
    public static  void unzip(String zipFileName) throws Exception {//解压到当前文件夹
       String outFile=zipFileName.substring(0,zipFileName.lastIndexOf("."));
       unzip(zipFileName,outFile);
    }
    /**
     * 解压zip格式的压缩文件到指定位置
     * @param zipFileName 压缩文件
     * @param extPlace 解压目录,解压出来的目录名
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
	public static  void unzip(String zipFileName, String extPlace) throws Exception {
        try {
            (new File(extPlace)).mkdirs();
            File f = new File(zipFileName);
            ZipFile zipFile = new ZipFile(zipFileName);
            if((!f.exists()) && (f.length() <= 0)) {
                throw new Exception("要解压的文件不存在!");
            }
            String strPath, gbkPath, strtemp;
            File tempFile = new File(extPlace);//从当前目录开始
            strPath = tempFile.getAbsolutePath();//输出的绝对位置
            Enumeration e = zipFile.getEntries();
            while(e.hasMoreElements()){
                org.apache.tools.zip.ZipEntry zipEnt = (ZipEntry) e.nextElement();
                gbkPath=zipEnt.getName();
                if(zipEnt.isDirectory()){
                    strtemp = strPath + File.separator + gbkPath;
                    File dir = new File(strtemp);
                    dir.mkdirs();
                    continue;
                } else {
                    //读写文件
                    InputStream is = zipFile.getInputStream(zipEnt);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    gbkPath=zipEnt.getName();
                    strtemp = strPath + File.separator + gbkPath;
                    
                    //建目录
                    String strsubdir = gbkPath;
                    for(int i = 0; i < strsubdir.length(); i++) {
                        if(strsubdir.substring(i, i + 1).equalsIgnoreCase("/")) {
                            String temp = strPath + File.separator + strsubdir.substring(0, i);
                            File subdir = new File(temp);
                            if(!subdir.exists())
                            subdir.mkdir();
                        }
                    }
                    FileOutputStream fos = new FileOutputStream(strtemp);
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    int len;
                    byte[] buff=new byte[BUFFEREDSIZE];
                    while((len = bis.read(buff)) != -1) {
                        bos.write(buff,0,len);
                    }
                    bos.close();
                    fos.close();
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
  public static void main(String args[])throws Exception{
      zip("D:/kcb_data/20121205153418","D:/kcb_data/ccc.zip");
      //unzip("lzma912.zip");
  } 
}