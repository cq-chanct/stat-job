package com.chanct.fraudCdr.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.chanct.core.quartz.job.BaseJob;
import com.chanct.fraudCdr.service.impl.FraudCdrServiceImpl;

public class FraudCdrJob extends BaseJob{

    public 	FraudCdrServiceImpl cdrService=new FraudCdrServiceImpl();
	@Override
	public void executeJob(JobExecutionContext context) throws JobExecutionException {
		cdrService.insertObject();
	}

}
