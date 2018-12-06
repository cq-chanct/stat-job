package com.chanct.netsecur.constants;

import com.chanct.core.util.PropertyUtil;


public class DBConstant {

	 public static final String tj_db = "dsCradar1";
	 public static final String fraud_db = "fraudDb";
	 public static final String mac_db = PropertyUtil.getConfig("macdb");
	 public static final String managerDb = "managerDb";
	 public static final String oracle = "oracle";
	public static final String dispose_web = "dispose";
	 
	 public final static String JDBC_DRIVER= PropertyUtil.getConfig("dsCradar1.jdbc.driver");
	 public final static String JDBC_URL = PropertyUtil.getConfig("dsCradar1.jdbc.url");
	 public final static String JDBC_USER = PropertyUtil.getConfig("dsCradar1.jdbc.username");
	 public final static String JDBC_PASSWORD = PropertyUtil.getConfig("dsCradar1.jdbc.password");
	 public final static String FTP_CONFIG_CDR_RESULT_PATH = PropertyUtil.getConfig("cdr.result.path");
	 public final static String FTP_CONFIG_CDR_BAK_PATH = PropertyUtil.getConfig("cdr.bak.path");
	 public final static String FTP_CONFIG_CDR_OUT_PATH = PropertyUtil.getConfig("cdr.out.path");
	 
	 public final static String FTP_CONFIG_CDR_TOTAL_PATH = PropertyUtil.getConfig("cdr.total.path");
	 
	 public final static String MAC = PropertyUtil.getConfig("mac");
	 public final static String CDR_SOURCE = PropertyUtil.getConfig("cdrSource");
	 public final static String FTP_FRAUD_CDR_UP_PATH = PropertyUtil.getConfig("fraudcdr.up.path");
	 public final static String FTP_FRAUD_CDR_BAK_PATH = PropertyUtil.getConfig("fraudcdr.bak.path");
	 public final static String FTP_FRAUDWAV_UP_PATH = PropertyUtil.getConfig("fraudwav.up.path");
	 
}
