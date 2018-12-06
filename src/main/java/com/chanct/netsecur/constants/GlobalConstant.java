package com.chanct.netsecur.constants;


import java.sql.SQLException;

import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.chanct.core.util.BeanUtil;
import com.chanct.core.util.PropertyUtil;

public class GlobalConstant {
	
	public static String SERVICE_TYPE = null;
	
    public static final String ALL_SVR = "all";//所有服务
	public static final long REGIST_INTERVAL = 1;//注册周期，单位：分钟
	public static final String REALTIME_TASK = "0";//即时查询任务	
	public static final String TIMING_TASK = "1";//定时任务	
	public static final String JOB_ID = "id";
	public static final String JOB_NAME = "name";
	public static final String JOB_CLASS = "jobClass";
	public static final String JOB_DATA = "jobData";
	public static final String JOB_SERVICE_TYPE = "serviceType";
	public static final String JOB_TYPE = "type";
	public static final String JOB_STRATEGY = "strategy";
	public static final String JOB_YL1 = "yl1";
	public static final String JOB_YL2 = "yl2";
	public static final String JOB_YL3 = "yl3";
	public static final String JOB_BEGIN_TIME = "beginTime";
	public static final String JOB_END_TIME = "endTime";
	public static final String JOB_INVALIDATE = "2";
	public static final String SUCESS_MESG = "操作成功！";
	public static final String FAIL_MESG = "操作失败！";
	
	public static final String CRON_TYPE_SIMPLE = "1";//简单时间策略，时间间隔，单位分钟
	public static final String CRON_TYPE_CRON = "2";//复杂时间策略，quartz表达式
	public static final String CRON_TYPE_NUM = "3";//次数时间策略，立刻执行的执行次数
	
	public static final String CRONDELAY_STATUS_NOHANDLE = "0";
	public static final String CRONDELAY_STATUS_HANDLEFAIL = "1";
	public static final String CRONDELAY_STATUS_HANDLESUCC = "2";
	
	public static Transaction newTransaction() throws SQLException{
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		return transactionFactory.newTransaction(BeanUtil.getSession().getConnection());	
			
	}
	
	public static String getServiceType(){
		if(SERVICE_TYPE==null){
			SERVICE_TYPE = PropertyUtil.getConfig("service.type");
			if(SERVICE_TYPE==null || SERVICE_TYPE.equals("")){
				SERVICE_TYPE = ALL_SVR;
			}
		}
		return SERVICE_TYPE;
	}
	
}
