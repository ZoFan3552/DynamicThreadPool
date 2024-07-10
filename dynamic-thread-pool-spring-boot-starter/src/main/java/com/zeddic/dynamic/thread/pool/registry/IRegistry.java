package com.zeddic.dynamic.thread.pool.registry;

import com.zeddic.dynamic.thread.pool.domain.model.entity.ThreadPoolConfigEntity;

import java.util.List;

/**
 * @author: zeddic
 * @description: 注册中心接口
 * @date: 2024/7/10 下午5:41
 */
public interface IRegistry {
    void reportThreadPoolList(List<ThreadPoolConfigEntity> entityList);

    void reportThreadPoolConfigParameter(ThreadPoolConfigEntity entity);
}
