package com.chanct.insertEvilOracle.job;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.chanct.core.quartz.job.BaseJob;
import com.chanct.insertEvilOracle.service.InsertEvilService;
import com.chanct.insertEvilOracle.service.impl.InsertEvilServiceImpl;

public class InsertEvilJob extends BaseJob {
	private static Logger logger = Logger.getLogger(InsertEvilJob.class);
	InsertEvilService insertEvilServcie=new InsertEvilServiceImpl();

	@Override
	public void executeJob(JobExecutionContext context)
			throws JobExecutionException {
		insertEvilServcie.insertObject();
		
	}

}
