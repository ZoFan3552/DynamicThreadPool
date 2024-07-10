package com.zeddic.dynamic.thread.pool.trigger.job;


import com.alibaba.fastjson.JSON;
import com.zeddic.dynamic.thread.pool.domain.IDynamicThreadPoolService;
import com.zeddic.dynamic.thread.pool.domain.model.entity.ThreadPoolConfigEntity;
import com.zeddic.dynamic.thread.pool.registry.IRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @author: zeddic
 * @description:
 * @date: 2024/7/10 下午6:08
 */
public class ThreadPoolDataReportJob {

    private final Logger logger = LoggerFactory.getLogger(ThreadPoolDataReportJob.class);

    private final IDynamicThreadPoolService dynamicThreadPoolService;

    private final IRegistry registry;

    public ThreadPoolDataReportJob(IDynamicThreadPoolService dynamicThreadPoolService, IRegistry registry) {
        this.dynamicThreadPoolService = dynamicThreadPoolService;
        this.registry = registry;
    }

    @Scheduled(cron = "0/20 * * * * ?")//每分钟的每20秒（即0秒、20秒、40秒）执行一次任务
    //上报列表以及每一条数据
    public void execReportThreadPoolList(){
        List<ThreadPoolConfigEntity> threadPoolConfigEntityList = dynamicThreadPoolService.queryThreadPoolList();
        registry.reportThreadPoolList(threadPoolConfigEntityList);
        logger.info("上报线程池信息：{}" , JSON.toJSONString(threadPoolConfigEntityList));

        for (ThreadPoolConfigEntity threadPoolConfigEntity : threadPoolConfigEntityList) {
            logger.info("上报线程池配置：{}" , JSON.toJSONString(threadPoolConfigEntity));
            registry.reportThreadPoolConfigParameter(threadPoolConfigEntity);
        }
    }

}
