package com.chanct.core.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
public class LoadData2MySQLUtil {
	 private static final Logger logger = Logger.getLogger(LoadData2MySQLUtil.class);  
	    /** 
	     *  
	     * load bulk data from InputStream to MySQL 
	     */  
	    public static int bulkLoadFromInputStream(String loadDataSql,InputStream dataStream) throws SQLException {  
	        if(dataStream==null){  
	            logger.info("InputStream is null ,No data is imported");  
	            return 0;  
	        }  
	        Connection conn  = JdbcUtil.getConnection();  
	        PreparedStatement statement = conn.prepareStatement(loadDataSql);  
	        int result = 0;  
	        if (statement.isWrapperFor(com.mysql.jdbc.Statement.class)) {  
	            com.mysql.jdbc.PreparedStatement mysqlStatement = statement.unwrap(com.mysql.jdbc.PreparedStatement.class);  
	            mysqlStatement.setLocalInfileInputStream(dataStream);  
	            result = mysqlStatement.executeUpdate();  
	        }  
	        conn.close();
	        try {
				dataStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        return result;  
	    }  
	    
	    
	    public static InputStream getTestDataInputStream() {  
	        StringBuilder builder = new StringBuilder();  
	        for (int i = 1; i <= 10; i++) {  
	            for (int j = 0; j <= 10000; j++) {  
	  
	                builder.append(4);  
	                builder.append("\t");  
	                builder.append(4 + 1);  
	                builder.append("\t");  
	                builder.append(4 + 2);  
	                builder.append("\t");  
	                builder.append(4 + 3);  
	                builder.append("\t");  
	                builder.append(4 + 4);  
	                builder.append("\t");  
	                builder.append(4 + 5);  
	                builder.append("\n");  
	            }  
	        }  
	        byte[] bytes = builder.toString().getBytes();  
	        InputStream is = new ByteArrayInputStream(bytes);  
	        return is;  
	    } 
	    
	    
	public static void main(String[] args) {
		String testSql = "LOAD DATA LOCAL INFILE 'sql.csv' IGNORE INTO TABLE ipip_info(start_ip,end_ip,country,province,city,district,ip_owner,district_code,dimension,longitude)	";
		InputStream dataStream = getTestDataInputStream();
		try {
			long beginTime = System.currentTimeMillis();
			int rows = bulkLoadFromInputStream(testSql, dataStream);
			long endTime = System.currentTimeMillis();
			logger.info("importing " + rows + " rows data into mysql and cost " + (endTime - beginTime) + " ms!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	    
	    
	    
}
