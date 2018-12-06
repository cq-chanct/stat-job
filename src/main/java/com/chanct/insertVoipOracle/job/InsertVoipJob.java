package com.chanct.insertVoipOracle.job;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.chanct.core.quartz.job.BaseJob;
import com.chanct.insertVoipOracle.service.InsertVoipService;
import com.chanct.insertVoipOracle.service.impl.InsertVoipServiceImpl;

public class InsertVoipJob extends BaseJob {
	private static Logger logger = Logger.getLogger(InsertVoipJob.class);
	InsertVoipService insertVoipServcie=new InsertVoipServiceImpl();

	@Override
	public void executeJob(JobExecutionContext context)
			throws JobExecutionException {
		insertVoipServcie.insertObject();
		
	}

}
