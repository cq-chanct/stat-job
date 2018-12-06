package com.chanct.insertLowInterfaceOracle.job;

import com.chanct.insertLowInterfaceOracle.service.InsertLowInterfaceService;
import com.chanct.insertLowInterfaceOracle.service.impl.InsertLowInterfaceServiceImpl;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.chanct.core.quartz.job.BaseJob;

public class InsertLowInterfaceJob extends BaseJob {
	private static Logger logger = Logger.getLogger(InsertLowInterfaceJob.class);
	InsertLowInterfaceService insertEvilServcie=new InsertLowInterfaceServiceImpl();

	@Override
	public void executeJob(JobExecutionContext context)
			throws JobExecutionException {
		insertEvilServcie.insertObject();
		logger.info("进入推送低度疑似！");
	}

}
