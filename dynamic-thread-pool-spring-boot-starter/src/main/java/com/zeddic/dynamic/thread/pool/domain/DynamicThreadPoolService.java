package com.zeddic.dynamic.thread.pool.domain;

import com.alibaba.fastjson.JSON;
import com.zeddic.dynamic.thread.pool.domain.model.entity.ThreadPoolConfigEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: zeddic
 * @description:
 * @date: 2024/7/10 下午4:52
 */
public class DynamicThreadPoolService implements IDynamicThreadPoolService{
    private final Logger logger = LoggerFactory.getLogger(DynamicThreadPoolService.class);

    private final Map<String , ThreadPoolExecutor> threadPoolExecutorMap;

    private final String applicationName;

    public DynamicThreadPoolService(String applicationName , Map<String , ThreadPoolExecutor> threadPoolExecutorMap) {
        this.applicationName = applicationName;
        this.threadPoolExecutorMap = threadPoolExecutorMap;
    }
    @Override
    public List<ThreadPoolConfigEntity> queryThreadPoolList() {
        Set<String> threadPoolBeanNames = threadPoolExecutorMap.keySet();
        List<ThreadPoolConfigEntity> threadPoolConfigEntityList = new ArrayList<>(threadPoolBeanNames.size());
        for (String threadPoolBeanName : threadPoolBeanNames) {
            ThreadPoolExecutor threadPoolExecutor = threadPoolExecutorMap.get(threadPoolBeanName);
            ThreadPoolConfigEntity threadPoolConfigEntity = ThreadPoolConfigEntity.builder()
                    .corePoolSize(threadPoolExecutor.getCorePoolSize())
                    .maximumPoolSize(threadPoolExecutor.getMaximumPoolSize())
                    .activeCount(threadPoolExecutor.getActiveCount())
                    .poolSize(threadPoolExecutor.getPoolSize())
                    .queueType(threadPoolExecutor.getQueue().getClass().getSimpleName())
                    .queueSize(threadPoolExecutor.getQueue().size())
                    .remainingCapacity(threadPoolExecutor.getQueue().remainingCapacity())
                    .build();
            threadPoolConfigEntityList.add(threadPoolConfigEntity);
        }
        return threadPoolConfigEntityList;
    }

    @Override
    public ThreadPoolConfigEntity queryThreadPoolConfigByName(String threadPoolName) {
        ThreadPoolExecutor threadPoolExecutor = threadPoolExecutorMap.get(threadPoolName);
        if (threadPoolExecutor == null) {
            ThreadPoolConfigEntity nullEntity = new ThreadPoolConfigEntity();
            nullEntity.setAppName(applicationName);
            nullEntity.setThreadPoolName(threadPoolName);
            return nullEntity;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("动态线程池配置 -> 应用名: {}  线程名：{}  池化配置：{} "
                    , threadPoolName , threadPoolName , JSON.toJSONString(threadPoolExecutor));
        }
        return ThreadPoolConfigEntity.builder()
                .corePoolSize(threadPoolExecutor.getCorePoolSize())
                .maximumPoolSize(threadPoolExecutor.getMaximumPoolSize())
                .activeCount(threadPoolExecutor.getActiveCount())
                .poolSize(threadPoolExecutor.getPoolSize())
                .queueType(threadPoolExecutor.getQueue().getClass().getSimpleName())
                .queueSize(threadPoolExecutor.getQueue().size())
                .remainingCapacity(threadPoolExecutor.getQueue().remainingCapacity())
                .build();
    }

    @Override
    public void updateThreadPoolConfig(ThreadPoolConfigEntity threadPoolConfigEntity) {
        if (threadPoolConfigEntity == null || !applicationName.equals(threadPoolConfigEntity.getAppName())) return;
        ThreadPoolExecutor threadPoolExecutor = threadPoolExecutorMap.get(threadPoolConfigEntity.getThreadPoolName());
        if (threadPoolExecutor == null) return;

        //设置参数，只设置核心线程数和最大线程数
        threadPoolExecutor.setCorePoolSize(threadPoolConfigEntity.getCorePoolSize());
        threadPoolExecutor.setMaximumPoolSize(threadPoolConfigEntity.getMaximumPoolSize());
    }
}
