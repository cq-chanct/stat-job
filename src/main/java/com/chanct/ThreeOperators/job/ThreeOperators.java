package com.chanct.ThreeOperators.job;

import com.chanct.ThreeOperators.service.OperatorsService;
import com.chanct.ThreeOperators.service.impl.OperatorsServiceImpl;
import com.chanct.core.quartz.job.BaseJob;
import com.chanct.sjhmdinterfaceForBack.job.insertForBlack;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

    public class ThreeOperators extends BaseJob {
    private static Logger logger = Logger.getLogger(ThreeOperators.class);
    OperatorsService service = new OperatorsServiceImpl();
    @Override
    public void executeJob(JobExecutionContext context) throws JobExecutionException {
        logger.info("政企联动开始！");
        service.insertThree();
        logger.info("政企联动结束！");
    }
}
