package com.chanct.insertFraudOracle.job;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.chanct.core.quartz.job.BaseJob;
import com.chanct.insertEvilOracle.service.InsertEvilService;
import com.chanct.insertEvilOracle.service.impl.InsertEvilServiceImpl;
import com.chanct.insertFraudOracle.service.InsertFraudService;
import com.chanct.insertFraudOracle.service.impl.InsertFraudServiceImpl;

public class InsertFraudJob extends BaseJob {
	private static Logger logger = Logger.getLogger(InsertFraudJob.class);
	InsertFraudService insertFraudService=new InsertFraudServiceImpl();

	@Override
	public void executeJob(JobExecutionContext context)
			throws JobExecutionException {
		insertFraudService.insertObject();
		
	}

}
