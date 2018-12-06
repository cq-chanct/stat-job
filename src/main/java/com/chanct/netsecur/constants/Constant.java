package com.chanct.netsecur.constants;

import com.chanct.core.util.PropertyUtil;

public class Constant {
	
	public final static String LEVEL_DEEP = PropertyUtil.getConfig("levelDeepName");
	public final static String LEVEL_EASY = PropertyUtil.getConfig("levelEasyName");
	public final static int POINT_LIMIT =1000;
	
	public final static String FTP_RESULT_CNCERT_FILE_PATH = PropertyUtil.getConfig("ftp.result.cncert.file.path");
	public final static String JDBC_DRIVER= PropertyUtil.getConfig("dsCradar3.jdbc.driver");
	public final static String JDBC_URL = PropertyUtil.getConfig("dsCradar3.jdbc.url");
	public final static String JDBC_USER = PropertyUtil.getConfig("dsCradar3.jdbc.username");
	public final static String JDBC_PASSWORD = PropertyUtil.getConfig("dsCradar3.jdbc.password");
}
