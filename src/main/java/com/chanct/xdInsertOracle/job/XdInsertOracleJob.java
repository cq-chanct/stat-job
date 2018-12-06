package com.chanct.xdInsertOracle.job;


import com.chanct.xdInsertOracle.service.XdInsertServcie;
import com.chanct.xdInsertOracle.service.impl.XdInsertServiceImpl;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.chanct.core.quartz.job.BaseJob;

public class XdInsertOracleJob extends BaseJob {
	private static Logger logger = Logger.getLogger(XdInsertOracleJob.class);
	XdInsertServcie service = new XdInsertServiceImpl();

	@Override
	public void executeJob(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("16开始");
			service.readfile();
			logger.info("16结束");
	}
	


}
