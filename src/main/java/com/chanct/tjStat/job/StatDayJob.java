package com.chanct.tjStat.job;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.chanct.core.quartz.job.BaseJob;
import com.chanct.core.util.DateUtil;
import com.chanct.tjStat.service.DayJobServcie;
import com.chanct.tjStat.service.impl.DayJobServiceImpl;

public class StatDayJob extends BaseJob {
	private static Logger logger = Logger.getLogger(StatDayJob.class);
	DayJobServcie dayService=new DayJobServiceImpl();
	
	@Override
	public void executeJob(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("------开始执行天鉴统天任务-----");
		boolean flag;
		Map<String,String> map=this.getDateMap();
		flag=dayService.statCallIsp(map);
		if(flag){
			logger.info("------运营商天任务统计成功-----");
		}else{
			logger.info("------运营商天任务统计失败-----");
		}
		flag=dayService.statCheatedTypeDayCount(map);
		if(flag){
			logger.info("------各诈骗类型天任务统计成功-----");
		}else{
			logger.info("------各诈骗类型天任务统计失败-----");
		}
		flag=dayService.statCheatedUserCity(map);
		if(flag){
			logger.info("------各城市场受害用户数量天任务统计成功-----");
		}else{
			logger.info("-----各城市场受害用户数量天任务统计失败-----");
		}
		logger.info("------开始执行天鉴统天任务-----");
	}
	
	public Map<String,String> getDateMap(){
		Date date=DateUtil.getDateBeforeNow(Calendar.DATE,1);
		String startTime=DateUtil.dateToString(date, DateUtil.FORMAT_sjq01);
		String endTime=DateUtil.dateToString(date,DateUtil.FORMAT_sjq59 );
		String jobTime=DateUtil.currDayZero();
		Map<String,String> map=new HashMap<String,String>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("jobTime", jobTime);
		return map;
	}
	

}
