package com.chanct.xdInsertOracle.utils;

import com.chanct.core.util.DateUtil;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class KeyUtil {
	protected static Logger logger = Logger.getLogger(KeyUtil.class);
	private static Properties props = new Properties();
	
	/**
	 * 获取配置信息
	 */
	public static String getConfig(String key) {
		String value=null;
		 try {  
			 File file=new File("../key.properties");
			 if(file.exists()){
				 InputStream in = new FileInputStream(file);
		            props.load(in);
		            in.close();
		            value = props.getProperty(key);
		            return value; 
			 }else{
				 logger.info("key配置文件不存在");
				 return value;
			 }

	        } catch (Exception e) {
	        	logger.info("读取key配置文件不存在");
	        }
		 return value;
	}
	/**
	 * 获取配置信息
	 */
	public static void setConfig(String key, String value) {
		
        try {
//        	File file=new File("/key.properties");
//        	if(!file.exists()){
//        		file.mkdirs();
//        		logger.info("开始创建key配置文件:"+file.getPath()+file.getName());
//        	}
            OutputStream fos = new FileOutputStream("../key.properties");
            props.setProperty(key, value);
            props.store(fos, "Update '" + key + "' value,time="+ DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
            fos.close();
            logger.info("设置key配置成功:Update '" + key + "' value,time="+ DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
        } catch (IOException e) {
        	logger.info("属性文件更新错误:"+e.getMessage());
        }
	}
	
	public static void main(String args[]){
		System.out.println(KeyUtil.getConfig("key"));
		KeyUtil.setConfig("key", DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
	}
}
