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
import com.chanct.tjStat.service.HourJobServcie;
import com.chanct.tjStat.service.impl.HourJobServiceImpl;

public class StatHourJob  extends BaseJob{
	private static Logger logger = Logger.getLogger(StatDayJob.class);
	HourJobServcie hourService=new HourJobServiceImpl();

	@Override
	public void executeJob(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("------开始执行小时鉴任务-----");
		boolean flag;
		Map<String,String> map=this.getDateMap();
		flag=hourService.statCallTotalCount(map);
		if(flag){
			logger.info("------每小时检出数量统计成功-----");
		}else{
			logger.info("------每小时检出数量统计失败-----");
		}
		flag=hourService.statCheatedAreaUserCount(map);
		if(flag){
			logger.info("------各国家省份城市受害用户统计成功-----");
		}else{
			logger.info("------各国家省份城市受害用户统计失败-----");
		}
		flag=hourService.statCheatedTypeCount(map);
		if(flag){
			logger.info("------各诈骗类型受害用户小时统计成功-----");
		}else{
			logger.info("-----各诈骗类型受害用户小时统计失败-----");
		}
		flag=hourService.statDynamicFlow(map);
		if(flag){
			logger.info("------各国家省份城市诈骗流向统计成功-----");
		}else{
			logger.info("-----各国家省份城市诈骗流向统计失败-----");
		}
		logger.info("------结束执行天鉴统小时任务-----");
	}
	public Map<String,String> getDateMap(){
		Date date=DateUtil.getDateBeforeNow(Calendar.HOUR,1);
		String startTime=DateUtil.dateToString(date, DateUtil.FORMAT_TEN);
		String endTime=DateUtil.dateToString(date,DateUtil.FORMAT_ELEVEN );
		Map<String,String> map=new HashMap<String,String>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return map;
	}
	
}
