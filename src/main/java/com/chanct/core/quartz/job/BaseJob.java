package com.chanct.core.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class  BaseJob implements Job {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			executeJob(context);
		} catch (Throwable e) {			
			//IsmpDExceptionServiceImpl.writeException(e);
			e.printStackTrace();
		}
		
	}
	public abstract void executeJob(JobExecutionContext context) throws JobExecutionException;
	
}
