package com.zeddic.dynamic.thread.pool.trigger.listen;

import com.alibaba.fastjson.JSON;
import com.zeddic.dynamic.thread.pool.domain.IDynamicThreadPoolService;
import com.zeddic.dynamic.thread.pool.domain.model.entity.ThreadPoolConfigEntity;
import com.zeddic.dynamic.thread.pool.registry.IRegistry;
import org.redisson.api.listener.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author: zeddic
 * @description:
 * @date: 2024/7/11 下午1:38
 */
public class ThreadPoolConfigAdjustListener implements MessageListener<ThreadPoolConfigEntity> {
    private final Logger logger = LoggerFactory.getLogger(ThreadPoolConfigAdjustListener.class);

    private final IDynamicThreadPoolService dynamicThreadPoolService;

    private final IRegistry registry;

    public ThreadPoolConfigAdjustListener(IDynamicThreadPoolService dynamicThreadPoolService, IRegistry registry) {
        this.dynamicThreadPoolService = dynamicThreadPoolService;
        this.registry = registry;
    }


    @Override
    public void onMessage(CharSequence charSequence, ThreadPoolConfigEntity entity) {
        logger.info("动态线程池->调整线程池配置。线程池名称：{} 核心线程数：{} 最大线程数{}" ,
                entity.getAppName(),entity.getCorePoolSize() ,entity.getMaximumPoolSize());
        dynamicThreadPoolService.updateThreadPoolConfig(entity);
        //更新后上报数据
        List<ThreadPoolConfigEntity> threadPoolConfigEntityList = dynamicThreadPoolService.queryThreadPoolList();
        registry.reportThreadPoolList(threadPoolConfigEntityList);
        ThreadPoolConfigEntity threadPoolConfigEntityCurrent =
                dynamicThreadPoolService.queryThreadPoolConfigByName(entity.getThreadPoolName());
        registry.reportThreadPoolConfigParameter(threadPoolConfigEntityCurrent);
        logger.info("动态线程池->上报线程池配置：{}" , JSON.toJSONString(entity));
    }
}
