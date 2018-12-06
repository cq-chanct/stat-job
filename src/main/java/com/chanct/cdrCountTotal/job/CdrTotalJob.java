package com.chanct.cdrCountTotal.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.chanct.cdrCountTotal.service.impl.CdrTotalJobServiceImpl;
import com.chanct.core.quartz.job.BaseJob;
@DisallowConcurrentExecution
public class CdrTotalJob extends BaseJob{

	public 	CdrTotalJobServiceImpl cdrService=new CdrTotalJobServiceImpl();
	@Override
	public void executeJob(JobExecutionContext context) throws JobExecutionException {
		cdrService.insertObject();
	}



}
