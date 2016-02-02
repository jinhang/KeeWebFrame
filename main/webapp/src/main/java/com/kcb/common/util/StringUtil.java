package com.kcb.common.util;

import java.io.UnsupportedEncodingException;

/**
 * @author jyl
 * @version 创建时间：2013-8-8 上午9:22:16
 * 类说明
 */
public class StringUtil {

	/**
	 * 全角转半角
	 * @param QJstr
	 * @return
	 */
	public static String full2HalfChange(String QJstr){
		if(QJstr==null||QJstr.equals("")) return QJstr;
		
		StringBuffer outStrBuf = new StringBuffer("");
		String Tstr = "";
		byte[] b = null;
		for (int i = 0; i < QJstr.length(); i++) {
			Tstr = QJstr.substring(i, i + 1);
			// 全角空格转换成半角空格
			if (Tstr.equals("　")) {
				outStrBuf.append(" ");
				continue;
			}
			try {
				b = Tstr.getBytes("unicode");
				// 得到 unicode 字节数据
				if (b[2] == -1) {
					// 表示全角
					b[3] = (byte) (b[3] + 32);
					b[2] = 0;
					outStrBuf.append(new String(b, "unicode"));
				} else {
					outStrBuf.append(Tstr);
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // end for.
		return outStrBuf.toString();
	}
	
	public static boolean isEmpty(String value){
		return value == null ? true : value.trim().equals("");
	}
}
