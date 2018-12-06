package com.chanct.netsecur.main;


import org.apache.log4j.Logger;

import com.chanct.netsecur.scheduler.LoadTask;


public class QuartzManager {
	
	protected static Logger logger = Logger.getLogger(QuartzManager.class);

	private LoadTask loadTask;
	
	public void schedulerInit()
	{
		loadTask = new LoadTask();
		//加载任务到任务调度器，并启动任务调度
	     try {
	    	 loadTask.loadCronjobToScheduler();
		} catch (Exception e) {
			logger.error("load Task to scheduler failed!",e);
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		QuartzManager manager = new QuartzManager();
		manager.schedulerInit();
    }  
	
}
