package com.chanct.maliceUrl.job;

import com.chanct.core.quartz.job.BaseJob;
import com.chanct.maliceUrl.service.impl.insertMaliceUrlServiceImpl;
import com.chanct.maliceUrl.service.insertMaliceUrlService;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class maliceUrlJob extends BaseJob {
    private static Logger logger = Logger.getLogger(maliceUrlJob.class);
    insertMaliceUrlService service = new insertMaliceUrlServiceImpl();

    @Override
    public  void executeJob(JobExecutionContext context)
            throws JobExecutionException {
        logger.info("恶意url开始");
        service.insertMalice();
        logger.info("恶意url结束");

    }


}
