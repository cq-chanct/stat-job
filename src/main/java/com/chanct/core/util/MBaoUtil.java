package com.chanct.core.util;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.apache.log4j.Logger;

public class MBaoUtil {
	
	private static Logger logger = Logger.getLogger(MBaoUtil.class);
	public static String splitStr = "<--->";
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static String strencoding = "UTF-8";

	public static String getSequence() {
		String detestr = System.currentTimeMillis() + "";
		Random random = new Random();
		int n = random.nextInt(1000);
		while ((n + "").length() < 3) {
			n = random.nextInt(1000);
		}
		String result = detestr + n;
		return result;
	}

	public static String getDateString(String dateString){
		String date = "";
		if (dateString != null && dateString.trim() != "") {
			date = dateString.substring(0, dateString.length() - 2);
		}
		return date;

	}

	public static String getHost() {
		InetAddress ia = null;
		String localip = "";
		try {
			ia = InetAddress.getLocalHost();
			String localname = ia.getHostName();
			localip = ia.getHostAddress();
			System.out.println("本机名称是：" + localname);
			System.out.println("本机的ip是 ：" + localip);
		} catch (Exception e) {
			logger.error(e);
		}
		return localip;
	}

	public static void main(String[] args) {
		String sequence = getSequence();
		System.out.println(sequence);
	}
}
