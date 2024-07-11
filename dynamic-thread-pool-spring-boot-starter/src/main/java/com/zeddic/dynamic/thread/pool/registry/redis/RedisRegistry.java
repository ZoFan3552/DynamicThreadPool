package com.zeddic.dynamic.thread.pool.registry.redis;

import com.zeddic.dynamic.thread.pool.domain.model.entity.ThreadPoolConfigEntity;
import com.zeddic.dynamic.thread.pool.domain.model.vo.RegistryEnumVO;
import com.zeddic.dynamic.thread.pool.registry.IRegistry;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;

import java.time.Duration;
import java.util.List;

/**
 * @author: zeddic
 * @description: Redis 实现的注册中心
 * @date: 2024/7/10 下午5:42
 */
public class RedisRegistry implements IRegistry {

    private final  RedissonClient redissonClient;

    public RedisRegistry(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }
    @Override
    public void reportThreadPoolList(List<ThreadPoolConfigEntity> entityList) {
        RList<ThreadPoolConfigEntity> list =
                redissonClient.getList(RegistryEnumVO.THREAD_POOL_CONFIG_LIST_KEY.getKey());
        list.delete();//防止重复添加
        list.addAll(entityList);
    }

    @Override
    public void reportThreadPoolConfigParameter(ThreadPoolConfigEntity entity) {
        String cacheKey = RegistryEnumVO.THREAD_POOL_CONFIG_PARAMETER_LIST_KEY.getKey()
                + "_" + entity.getAppName() + "_" + entity.getThreadPoolName();
        RBucket<ThreadPoolConfigEntity> bucket = redissonClient.getBucket(cacheKey);
        bucket.set(entity , Duration.ofDays(30));
    }
}
