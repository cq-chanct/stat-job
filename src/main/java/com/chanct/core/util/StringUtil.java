package com.chanct.core.util;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class StringUtil {
	
	public static boolean isNotNull(String str) {
		boolean b = false;
		if (null != str && str.trim().length() > 0 && (!str.equalsIgnoreCase("null")) && (!str.equals("undefined"))) {
			b = true;
		}
		return b;
	}
	
	public static String isNullDefault(String str,String defaultStr) {
		if(!isNotNull(str))
			return defaultStr;
		return str;
	}
	
	public static boolean isNotNull(Map<?, ?> map) {
		return !((map == null) || map.isEmpty());
	}
	
	public static <T> boolean isNotNull(List<T> list) {
		boolean b = false;
		if (null != list && list.size() > 0) {
			b = true;
		}
		return b;
	}
	
	public static boolean isNotNull(int str) {
		return (str != 0);
	}
	
	public static boolean isNotNull(StringBuffer str) {
		return (str != null) && (!"".equalsIgnoreCase(str.toString())) && (str.toString().trim().length() >= 1);
	}
	
	public static boolean isNotNull(String[] str) {
		return (str != null) && (str.length != 0);
	}
	
	public static boolean isNotNull(Object obj) {
		boolean b = false;
		if (null != obj) {
			b = true;
		}
		return b;
	}
	
	public static String isnotNullDefaultStr(String str,String defaultStr)
	{
		if(isNotNull(str))
		{
			return str;
		}
		return defaultStr;
	}
	
	public static String toString(Object obj) {
		String str = null;
		if (isNotNull(obj)) {
			str = String.valueOf(obj).trim();
		} else {
			str = "";
		}
		return str;
	}
	
	public static String toString(int str) {
		return String.valueOf(str);
	}
	
	/**
	 * 锟斤拷锟斤拷址锟轿猲ull,锟斤拷锟斤拷为""
	 * 
	 * @param str
	 * @return
	 */
	public static String toString(String str) {
		return str != null ? str.trim() : "";
	}
	
	public static String toString(BigDecimal str) {
		return str != null ? str.toString() : "";
	}
	
	public static int getIntValue(String v) {
		return getIntValue(v, -1);
	}
	
	/**
	 * 转锟斤拷为Int
	 * 
	 * @param v
	 * @param def
	 *        默锟斤拷值
	 * @return
	 */
	public static int getIntValue(String v, int def) {
		try {
			return Integer.parseInt(v.trim());
		} catch (Exception ex) {
			return def;
		}
	}
	
	/**
	 * 锟斤拷ISO-8859转锟斤拷为GBK
	 * 
	 * @param str
	 * @return
	 */
	public static String formatStrGBK(String str) throws Exception {
		String result = "";
		if (isNotNull(str)) {
			result = new String(str.getBytes("ISO-8859-1"), "GBK");
		}
		return result.trim();
	}
	
	/**
	 * 锟斤拷ISO-8859转锟斤拷为UTF8
	 * 
	 * @param str
	 * @return
	 */
	public static String formatStrUTF(String str) throws Exception {
		String result = "";
		if (isNotNull(str)) {
			result = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		}
		return result.trim();
	}
	
	public static String formatStrUTF(StringBuilder str) throws Exception {
		String result = "";
		if (isNotNull(str)) {
			result = new String(str.toString().getBytes("ISO-8859-1"), "UTF-8");
		}
		return result.trim();
	}
	
	public static String formatHTML(String str) {
		return str != null ? str.trim().replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;") : "";
	}
	
	/**
	 * 锟津单碉拷锟斤拷锟揭伙拷锟接拷锏ワ拷锟斤拷锟绞的革拷锟斤拷锟斤拷式 专锟斤拷锟斤拷锟矫伙拷懈锟斤拷锟斤拷锟绞�
	 * 
	 * @param var1
	 * @return
	 */
	public static String pluralize(String var1) {
		if (var1.substring(var1.length() - 1).matches("[A-Z]"))
			return var1;
		else if (var1.endsWith("y")) {
			if (var1.endsWith("ay") || var1.endsWith("ey") || var1.endsWith("oy") || var1.endsWith("uy")) {
				return var1 + "s";
			} else {
				return var1.substring(0, var1.length() - 1) + "ies";
			}
		} else {
			if (var1.endsWith("a") || var1.endsWith("o") || var1.endsWith("u")) {
				return var1 + "es";
			} else {
				return var1 + "s";
			}
		}
	}
	
	
	/*
	 * 锟斤拷取锟侥硷拷锟斤拷
	 */
	public static String getFilename(String path) {
		if (path == null) {
			return null;
		}
		int separatorIndex = path.lastIndexOf("/");
		return separatorIndex != -1 ? path.substring(separatorIndex + 1) : path;
	}
	
	/*
	 * 锟斤拷取锟侥硷拷锟斤拷展锟斤拷
	 */
	public static String getFilenameExtension(String path) {
		if (path == null) {
			return null;
		}
		int extIndex = path.lastIndexOf('.');
		if (extIndex == -1) {
			return null;
		}
		int folderIndex = path.lastIndexOf("/");
		if (folderIndex > extIndex) {
			return null;
		}
		return path.substring(extIndex + 1);
	}
	
	/*
	 * 锟斤拷取锟侥硷拷路锟斤拷
	 */
	public static String getFilPath(String path) {
		if (path == null) {
			return null;
		}
		path = path.replaceAll("\\\\", "/");
		int separatorIndex = path.lastIndexOf("/");
		return separatorIndex != -1 ? path.substring(0, separatorIndex + 1) : path;
	}
	
	/*
	 * 锟斤拷取锟侥硷拷锟斤拷锟斤拷锟斤拷展锟斤拷
	 */
	public static String getFilenameNoExtension(String path) {
		if (path == null) {
			return null;
		}
		int extIndex = path.lastIndexOf('.');
		if (extIndex == -1) {
			return path;
		}
		int folderIndex = path.lastIndexOf("/");
		if (folderIndex > extIndex) {
			return path;
		}
		return path.substring(folderIndex+1, extIndex);
	}

}
