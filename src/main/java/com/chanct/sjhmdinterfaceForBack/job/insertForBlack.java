package com.chanct.sjhmdinterfaceForBack.job;

import com.chanct.insertEvilOracle.job.InsertEvilJob;
import com.chanct.insertEvilOracle.service.InsertEvilService;
import com.chanct.insertEvilOracle.service.impl.InsertEvilServiceImpl;
import com.chanct.sjhmdinterfaceForBack.service.InsertBackService;
import com.chanct.sjhmdinterfaceForBack.service.serviceImpl.InsertBackServiceImpl;
import com.chanct.core.quartz.job.BaseJob;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by Administrator on 2017/9/16.
 */
public class insertForBlack extends  BaseJob {

    private static Logger logger = Logger.getLogger(insertForBlack.class);
    InsertBackService insertBackService=new InsertBackServiceImpl();

    @Override
    public void executeJob(JobExecutionContext context)
            throws JobExecutionException {
        logger.info("11开始");
    	insertBackService.insertObject();
        logger.info("11结束");

    }

}
