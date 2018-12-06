package com.chanct.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.chanct.core.domain.Dbproperty;

@SuppressWarnings("rawtypes")
public class PropertyUtil {
	protected static Logger logger = Logger.getLogger(PropertyUtil.class);
	public static Map<String, String> configs = new ConcurrentHashMap<String, String>();
	public static Map<String, Dbproperty> dbconfigs = new ConcurrentHashMap<String, Dbproperty>();
	private static Properties props = new Properties();
	static{
		InputStream is = PropertyUtil.class.getClass().getResourceAsStream("/config.properties");
		try {
			props.load(is);
			for (Map.Entry entry : props.entrySet()) {
				String key = (String) entry.getKey();
				String value = (String) entry.getValue();
				if (value == null || "".equals(value.trim())) {
					continue;
				}
				value = value.trim();
				configs.put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 获取数据库配置
	 * @param key
	 * @return
	 */
	public static Dbproperty getDBConfig(String dbIndex) {
		if (dbconfigs.isEmpty() || dbconfigs.get(dbIndex) == null) {
			initDBProperty(dbIndex, dbconfigs);
		}
		return dbconfigs.get(dbIndex);
	}
	/**
	 * 根据错误码获取错误信息
	 * 
	 * @param key
	 * @return
	 */
	public static String getErrorInfo(String errorCode) {
		return "错误";
	}
	
	/**
	 * 获取配置信息
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		String value = props.getProperty(key);
		return value == null ? "" : value;
	}
	/**
	 * 获取配置信息
	 */
	public static String getConfig(String key) {
		return configs.get(key);
	}
	
	
	private static void initDBProperty(String dbIndex, Map<String, Dbproperty> property) {
		Dbproperty db = new Dbproperty();
		db.setDriver(configs.get(dbIndex+".jdbc.driverName"));
		db.setUrl(configs.get(dbIndex+".jdbc.url"));
		db.setUsername(configs.get(dbIndex+".jdbc.username"));
		db.setPassword(configs.get(dbIndex+".jdbc.password"));
		property.put(dbIndex, db);
		logger.info("数据库配置信息"+dbIndex+"导入系统成功!");
	}
}
