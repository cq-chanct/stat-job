package com.chanct.netsecur.scheduler;



import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.chanct.core.util.DateUtil;
import com.chanct.netsecur.constants.GlobalConstant;
import com.chanct.netsecur.cronjob.vo.TCronjob;

/**
 * 
 * @author glj
 * 任务调度，主要负责图表产品和通报产品的的任务调度。
 *
 */
public class TaskScheduler{
	private static TaskScheduler instance;
	private Scheduler scheduler;
	protected static Logger logger = Logger.getLogger(TaskScheduler.class);
    
	private TaskScheduler(){
		
	}
	public static TaskScheduler getInstance() throws SchedulerException{
		if(instance==null){
			instance = new TaskScheduler();	
			instance.start();
		}
		return instance;
	}
	
	/*
	 * 向调度器中添加任务,返回添加成功的个数。
	 */
		public int addJobListToScheduler(List<TCronjob> cronjobList)			{
			int count = 0;
			if (cronjobList != null) {
				for (TCronjob cronjob : cronjobList) {	
					try {
						addJobToScheduler(cronjob);
						count ++;
					} catch (Exception e) {
						logger.error(e.getMessage(),e);
					}
					
				}
			}
			return count;
		}
		
		public void addJobToScheduler(TCronjob cronjob) throws Exception{
			start();
		
			if(isExist(cronjob)){
				if(!isTheSame(cronjob)){
					logger.info("add jobId="+cronjob.getId()+" to schedule,the job is exist,but has been update!");
					delCronjob(cronjob);
					addCronjob(cronjob);
				}			
			}
			else{
				addCronjob(cronjob);
				logger.info("add job to scheduler jobid="+cronjob.getId());
			}
		}
		
		public void delJobFormScheduler(TCronjob cronjob)throws Exception{
			start();
			if(isExist(cronjob)){
				delCronjob(cronjob);		
				logger.info("delete from scheduler jobid="+cronjob.getId());
			}
		}
		
	private void initSchedu() throws SchedulerException {
		SchedulerFactory sf = new StdSchedulerFactory();
		scheduler = sf.getScheduler();       
		/*SchedulerListenerImpl schedulerListener = new SchedulerListenerImpl();
		scheduler.getListenerManager().addSchedulerListener(schedulerListener);
		JobListenerImpl jobListener = new JobListenerImpl();
		scheduler.getListenerManager().addJobListener(jobListener);*/
	}

	private void start() throws SchedulerException {		
		if(scheduler==null){
			initSchedu();			
		}	
		if(!scheduler.isStarted()){
			scheduler.start();
		}		
	}

/**
 * 向调度器中添加任务
 * @param taskInfo
 * @return
 */
	@SuppressWarnings("unchecked")
	private boolean addCronjob(TCronjob cronjob) throws Exception{	
			
		Class<Job> jobClass = null;
		try {
			jobClass = (Class<Job>) Class.forName(cronjob.getJobClass());
		} catch (ClassNotFoundException e) {
			logger.error("the jobClass "+cronjob.getJobClass()+" is not exist!cronjob ID="+cronjob.getId(),e);
			throw new Exception("the jobClass "+cronjob.getJobClass()+" is not exist!cronjob ID="+cronjob.getId(),e);
		}
		try {
			
			JobDetail job = newJob(jobClass).withIdentity(JobKey.jobKey(
					cronjob.getId().toString()))
					.build();
			//向任务中添加信息		
			job.getJobDataMap().put(GlobalConstant.JOB_ID, cronjob.getId());
			job.getJobDataMap().put(GlobalConstant.JOB_DATA, cronjob.getJobData());
			job.getJobDataMap().put(GlobalConstant.JOB_BEGIN_TIME, cronjob.getBeginTime());
			job.getJobDataMap().put(GlobalConstant.JOB_END_TIME, cronjob.getEndTime());
			job.getJobDataMap().put(GlobalConstant.JOB_STRATEGY, cronjob.getType()+":"+cronjob.getStrategy());	
			job.getJobDataMap().put(GlobalConstant.JOB_NAME, cronjob.getName());
			job.getJobDataMap().put(GlobalConstant.JOB_SERVICE_TYPE, cronjob.getServiceType());
			job.getJobDataMap().put(GlobalConstant.JOB_YL1, cronjob.getYl1());	
			job.getJobDataMap().put(GlobalConstant.JOB_YL2, cronjob.getYl2());
			Trigger trigger = null;
			Date beginTime = DateUtil.stringtoDate(cronjob.getBeginTime(),
					"yyyy-MM-dd HH:mm:ss");
			Date endTime = DateUtil.stringtoDate(cronjob.getEndTime(),"yyyy-MM-dd HH:mm:ss");
			if (cronjob.getType().equals(GlobalConstant.CRON_TYPE_SIMPLE)) {//简单时间策略调度
				int interval = Integer.parseInt(cronjob.getStrategy());
				trigger = newTrigger()
							.withIdentity(TriggerKey.triggerKey(cronjob.getId().toString()))
							.startAt(beginTime)
							.endAt(endTime)
							.withSchedule(
									simpleSchedule()
									.withIntervalInSeconds(interval*60)
									.repeatForever().withMisfireHandlingInstructionFireNow())
						//			.withMisfireHandlingInstructionNextWithExistingCount())
							.build();								

			}
			else if(cronjob.getType().equals(GlobalConstant.CRON_TYPE_NUM)){
				int num = Integer.parseInt(cronjob.getStrategy());
				trigger = newTrigger()
						.withIdentity(TriggerKey.triggerKey(cronjob.getId().toString()))
						.startAt(new Date())					
						.withSchedule(
								simpleSchedule()
								.withMisfireHandlingInstructionNextWithExistingCount()
								.withRepeatCount(num)
								.withIntervalInHours(5))
						.build();		
			}
			else {
					trigger = newTrigger()
							.withIdentity(TriggerKey.triggerKey(
									cronjob.getId().toString()))
							.endAt(endTime)
							.withSchedule(
									CronScheduleBuilder
									.cronSchedule(cronjob.getStrategy()).withMisfireHandlingInstructionIgnoreMisfires())
							//		.withMisfireHandlingInstructionFireAndProceed())//以当前时间为触发频率立刻触发一次执行,然后按照Cron频率依次执行
							.build();
				}
				
//			}
			
			scheduler.scheduleJob(job, trigger);
		} catch (Exception e) {
			if(e instanceof SchedulerException){
				if(e.getMessage().contains("will never fire")){
					logger.error("crontab error! this job will never fire!jobid="+cronjob.getId(),e);	
					TCronjob job = new TCronjob();
					job.setId(cronjob.getId());
					job.setJobStatus(GlobalConstant.JOB_INVALIDATE);
					job.setUpdateTime(DateUtil.getNow());
					LoadTask.updateCronjobByID(job);
					throw new Exception("crontab error! this job will never fire!jobid="+cronjob.getId(),e);
				}
			}			
			logger.error(" add task failed!the cronjob ID="+cronjob.getId(),e);
			throw new Exception("add task failed!the cronjob ID="+cronjob.getId(),e);
		}
        return true;
	}

	/**
	 * 从调度器中删除任务，同时删除该任务的触发器
	 * @param taskInfo
	 * @return
	 */
	private boolean delCronjob(TCronjob cronjob) throws Exception {
		boolean result = false;
          try {
			if(scheduler!=null){
				  JobKey jobKey = new JobKey(cronjob.getId().toString());
				  result = scheduler.deleteJob(jobKey);
				  if(result);
					//  logger.info("delete cronjob id="+cronjob.getId()+" success!");
				  else
					  logger.info("delete cronjob id="+cronjob.getId()+" failed!");
			  }
		} catch (Exception e) {
			throw new Exception("delete cronjob id="+cronjob.getId()+" failed!",e);
		}
          return result;
	}
	
	/**
	 * 判断调度器中是否存在该任务
	 * @param taskInfo
	 * @return
	 */
	private boolean isExist(TCronjob cronjob) throws Exception{
		boolean result = false;			
		JobKey jobKey = new JobKey(cronjob.getId().toString());
		result = scheduler.checkExists(jobKey);					
		return result;
	}
	private boolean isTheSame(TCronjob cronjob)throws Exception{
		boolean result = false;
		JobKey jobKey = new JobKey(cronjob.getId().toString().trim());
		String strategy = cronjob.getType()+":"+cronjob.getStrategy();
		String beginTime = cronjob.getBeginTime();
		String endTime = cronjob.getEndTime();
		String jobdata = cronjob.getJobData()==null?"":cronjob.getJobData();
		JobDetail detail = scheduler.getJobDetail(jobKey);		
		
		String quz_btime = detail.getJobDataMap().getString(GlobalConstant.JOB_BEGIN_TIME);
		String quz_etime = detail.getJobDataMap().getString(GlobalConstant.JOB_END_TIME);
		String quz_strategy = detail.getJobDataMap().get(GlobalConstant.JOB_STRATEGY).toString();
		String quz_jobdata = detail.getJobDataMap().get(GlobalConstant.JOB_DATA)==null?"":detail.getJobDataMap().get(GlobalConstant.JOB_DATA).toString();
		if(cronjob.getType().equals(GlobalConstant.CRON_TYPE_NUM)){//单次通报，可能没有开始和结束时间。
			if(endTime!=null && endTime.equals(quz_etime) && strategy.equals(quz_strategy)){
				result = true;
			}
			else if(endTime==null && strategy.equals(quz_strategy)){
				result = true;
			}
		}
		else{	
//			logger.info("glj--isTheSame database beginTime="+beginTime+",endTime="+endTime+",strategy="+strategy+",jobdata="+jobdata+"," +
//					"quartzData beginTime="+quz_btime+",endTime="+quz_etime+",strategy="+quz_strategy+",jobdata="+quz_jobdata);
			if(beginTime.equals(quz_btime) && endTime.equals(quz_etime) && strategy.equals(quz_strategy) && jobdata.equals(quz_jobdata)){
				result = true;
			}									
        }			
	     return result;
	}

	
}
