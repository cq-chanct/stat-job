package com.chanct.interfaceProcess.job;

import com.chanct.core.quartz.job.BaseJob;
import com.chanct.interfaceProcess.service.InterfaceProcessService;
import com.chanct.interfaceProcess.service.impl.InterfaceProcessServiceImpl;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class InterfaceProcessJob extends BaseJob {
    private static Logger logger = Logger.getLogger(InterfaceProcessJob.class);
    InterfaceProcessService insertServcie=new InterfaceProcessServiceImpl();

    @Override
    public void executeJob(JobExecutionContext context)
            throws JobExecutionException {
        logger.info("17开始");
        insertServcie.query();
        logger.info("17结束");

    }

}
