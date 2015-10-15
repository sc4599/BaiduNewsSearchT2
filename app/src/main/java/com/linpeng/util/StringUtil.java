package com.linpeng.util;

import java.io.UnsupportedEncodingException;

public class StringUtil {

	/**
	 * 改变字符编码
	 * @param str 目标字符
	 * @return 转换字符
	 */
	public static String changeEncoding(String str,String encoding){
		try {
			String returnStr = new String(str.getBytes(), encoding);
			return returnStr;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

}
