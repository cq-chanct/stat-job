package com.chanct.netsecur.scheduler;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.transaction.Transaction;
import org.apache.log4j.Logger;
import org.quartz.SchedulerException;

import com.chanct.core.util.StringUtil;
import com.chanct.netsecur.constants.GlobalConstant;
import com.chanct.netsecur.cronjob.dao.ICronjobDAO;
import com.chanct.netsecur.cronjob.dao.impl.CronjobDAOImpl;
import com.chanct.netsecur.cronjob.vo.TCronjob;

public class LoadTask {
	
	private static Logger logger = Logger.getLogger(LoadTask.class);
	
	private TaskScheduler taskScheduler ;
	
	private ICronjobDAO cronjobDAO ;
   
	public LoadTask(){
		try {
			taskScheduler = TaskScheduler.getInstance();
		} catch (SchedulerException e) {
			logger.error(e);
		}
		cronjobDAO = new CronjobDAOImpl();
	}	
    
	/**
	 * 从数据库中获取状态为未执行和执行中的任务，加入到调度器中。
	 * @throws SchedulerException 
	 */
	public void loadCronjobToScheduler() throws Exception{
		Transaction newTransaction = null;
		try {
			String svrType = GlobalConstant.getServiceType();			
			if(StringUtil.isNotNull(svrType) && !svrType.equals(GlobalConstant.ALL_SVR)){
				svrType = "service_type in ("+svrType+")";
			}			
			newTransaction = GlobalConstant.newTransaction();
			List<TCronjob> cronjobList = cronjobDAO.getValidCronjobList(svrType);				
			if(cronjobList==null || cronjobList.size()==0){
				logger.info("there is not task to scheduler!");
			}
			else{
				int count = taskScheduler.addJobListToScheduler(cronjobList);
//				int count = taskScheduler.addJobListToScheduler(modifyCronJob(cronjobList));
				if(cronjobList.size()!=count){
					logger.info("存在未加载成功的任务，共有任务数："+cronjobList.size()+",加载成功数："+count);
				}
				else{
					logger.info("定时任务共："+count+"个！");
				}
			}	
			newTransaction.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	/**
	 * 修改任务，将执行时间改为每分钟执行一次
	 * */
	public List<TCronjob> modifyCronJob(List<TCronjob> cronjobList){
		List<TCronjob>  newJobList = new ArrayList<TCronjob>();
		for(TCronjob t: cronjobList){
			t.setStrategy("0 10 0/1 * * ?");
			newJobList.add(t);
		}
		return newJobList;
	}
	public static void updateCronjobByID(TCronjob cronjob){
		Transaction newTransaction = null;
    	try {
    		newTransaction = GlobalConstant.newTransaction();
			ICronjobDAO dao = new CronjobDAOImpl();
			dao.updateByID(cronjob);
		} catch (Exception e) {
			try {
				newTransaction.rollback();
			} catch (SQLException e1) {
				logger.error("update conjob id["+cronjob.getId()+"] rollback failure",e);
			}
			logger.error(e.getMessage());
		}finally{
			if(newTransaction != null)
				try {
					newTransaction.close();
				} catch (SQLException e) {
					logger.error("update conjob id["+cronjob.getId()+"] close failure",e);
				}
		}
    }
}
