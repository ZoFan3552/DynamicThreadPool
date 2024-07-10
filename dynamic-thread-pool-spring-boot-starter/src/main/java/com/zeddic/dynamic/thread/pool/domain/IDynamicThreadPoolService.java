package com.zeddic.dynamic.thread.pool.domain;

import com.zeddic.dynamic.thread.pool.domain.model.entity.ThreadPoolConfigEntity;

import java.util.List;

/**
 * @author: zeddic
 * @description: 动态线程池服务
 * @date: 2024/7/10 下午4:37
 */
public interface IDynamicThreadPoolService {
    List<ThreadPoolConfigEntity> queryThreadPoolList();

    ThreadPoolConfigEntity queryThreadPoolConfigByName(String name);

    void updateThreadPoolConfig(ThreadPoolConfigEntity threadPoolConfigEntity);
}
