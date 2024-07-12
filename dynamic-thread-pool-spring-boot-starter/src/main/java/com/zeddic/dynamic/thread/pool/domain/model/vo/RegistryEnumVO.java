package com.zeddic.dynamic.thread.pool.domain.model.vo;

import lombok.Getter;

/**
 * @author: zeddic
 * @description: 注册中心枚举值对象
 * @date: 2024/7/10 下午5:56
 */
@Getter
public enum RegistryEnumVO {

    THREAD_POOL_CONFIG_LIST_KEY("THREAD_POOL_CONFIG_LIST_KEY", "池化配置列表"),
    THREAD_POOL_CONFIG_PARAMETER_LIST_KEY("THREAD_POOL_CONFIG_PARAMETER_LIST_KEY", "池化配置参数"),
    DYNAMIC_THREAD_POOL_REDIS_TOPIC("DYNAMIC_THREAD_POOL_REDIS_TOPIC", "动态线程池监听主题配置");

    private final String key;
    private final String description;

    RegistryEnumVO(String key, String description) {
        this.key = key;
        this.description = description;
    }

}
