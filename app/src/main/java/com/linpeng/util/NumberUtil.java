package com.linpeng.util;

public class NumberUtil {

	/**
	 * 将字符串转化成int型数据
	 * @param str
	 * @return
	 */
	public static Integer parseInt(String str){
		if(str==null||str.equals("")){
			return null;
		}else{
			try{
				return Integer.parseInt(str);
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
	}
	/**
	 * 将字符串转化成int型数据
	 * @param str
	 * @return
	 */
	public static Integer parseInt(String str,int defaultValue){
		if(str==null||str.equals("")){
			return defaultValue;
		}else{
			try{
				return Integer.parseInt(str);
			}catch(Exception e){
				e.printStackTrace();
				return defaultValue;
			}
		}
	}

	/**
	 * 将字符串转化成Double型数据
	 * @param str
	 * @return
	 */
	public static Double parseDouble(String str){
		if(str==null||str.equals("")){
			return null;
		}else{
			try{
				return Double.parseDouble(str);
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * 将字符串转化成Double数据，不可转换时返回0
	 * @param str
	 * @return
	 */
	public static Double parseDoubleZero(String str){
		return parseDouble(str, 0.0);
	}

	/**
	 * 将字符串转化成Double数据，不可转换时返回defaultNumber
	 * @param str
	 * @return
	 */
	public static Double parseDouble(String str,Double defaultNumber){
		if(str==null||str.equals("")){
			return defaultNumber;
		}else{
			try{
				return Double.parseDouble(str);
			}catch(Exception e){
				e.printStackTrace();
				return defaultNumber;
			}
		}
	}

	/**
	 * 将字符串转化成Double数据，不可转换时返回0
	 * @param str
	 * @return
	 */
	public static Double parseDoubleZeroToOne(String str){
		if(parseDoubleZero(str)==0){
			return 1.0;
		}else{
			return parseDouble(str, 1.0);
		}
	}

	/**
	 * 将字符串转化成整形数据，不可转换时返回0
	 * @param str
	 * @return
	 */
	public static Integer parseIntZero(String str){
		if(str==null||str.equals("")){
			return 0;
		}else{
			try{
				return parseInt(str);
			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
		}
	}

	/**
	 * 判断str和large的大小 
	 * @param str 比较数
	 * @param large 被比较数
	 * @return 第一个参数比large小返回true
	 */
	public static boolean idSmallThan(String str,float large){
		if(parseDoubleZero(str)<large){
			return true;
		}else{
			return false;
		}
	}

	public static boolean isAllEmptyOrZero(String... strs){
		for(String string:strs){
			if(parseDoubleZero(string)!=0){
				return false;
			}
		}
		return true;
	}
}
