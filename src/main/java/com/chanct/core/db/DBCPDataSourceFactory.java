package com.chanct.core.db;


import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

public class DBCPDataSourceFactory implements DataSourceFactory {
	
    private BasicDataSource datasource = null;
    
    public DBCPDataSourceFactory(){
        this.datasource = new BasicDataSource();
    }
    
    public DataSource getDataSource() {
        // TODO Auto-generated method stub
        return datasource;
    }

    public void setProperties(Properties ps) {
        datasource.setDriverClassName( ps.getProperty("driverclassname"));
        datasource.setUsername( ps.getProperty("username"));
        datasource.setUrl( ps.getProperty("url"));
        datasource.setPassword( ps.getProperty("password"));
        datasource.setMaxActive( Integer.parseInt(ps.getProperty("maxActive","30")));
        datasource.setInitialSize( Integer.parseInt(ps.getProperty("initialSize","5")) );
        datasource.setMaxIdle( Integer.parseInt(ps.getProperty("maxIdle","3")));
        datasource.setMaxWait( Long.parseLong(ps.getProperty("maxWait","1000")));  
        datasource.setTimeBetweenEvictionRunsMillis(30000);
        datasource.setTestOnBorrow(false);
        datasource.setTestWhileIdle(true);
        datasource.setValidationQuery("SELECT 1 FROM DUAL");
        datasource.setRemoveAbandoned(true);
        datasource.setRemoveAbandonedTimeout(180);
    }
}