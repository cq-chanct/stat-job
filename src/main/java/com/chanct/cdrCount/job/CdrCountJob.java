package com.chanct.cdrCount.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.chanct.cdrCount.service.impl.CdrCountServiceImpl;
import com.chanct.core.quartz.job.BaseJob;
@DisallowConcurrentExecution
public class CdrCountJob extends BaseJob{

    public 	CdrCountServiceImpl cdrService=new CdrCountServiceImpl();
	@Override
	public void executeJob(JobExecutionContext context) throws JobExecutionException {
		cdrService.insertObject();
	}



}
