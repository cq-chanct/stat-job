package com.chanct.insertRejectPhone.job;

import com.chanct.core.quartz.job.BaseJob;
import com.chanct.insertRejectPhone.service.InsertRejectPhonService;
import com.chanct.insertRejectPhone.service.impl.InsertRejectPhonServiceImpl;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class InsertRejectPhone extends BaseJob {
	private static Logger logger = Logger.getLogger(InsertRejectPhone.class);
	InsertRejectPhonService insertEvilServcie=new InsertRejectPhonServiceImpl();

	@Override
	public void executeJob(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("19开始");
		insertEvilServcie.insertObject();
		logger.info("19结束");
	}

}
