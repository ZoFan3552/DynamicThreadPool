package com.zeddic.dynamic.thread.pool.config;

import com.alibaba.fastjson.JSON;
import com.zeddic.dynamic.thread.pool.domain.DynamicThreadPoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: zeddic
 * @description: 动态配置入口
 * @date: 2024/7/10 下午3:18
 */
@Configuration
public class DynamicThreadPoolAutoConfig {

    private final Logger logger = LoggerFactory.getLogger(DynamicThreadPoolAutoConfig.class);

    @Bean("dynamicThreadPoolService")
    public DynamicThreadPoolService dynamicThreadPoolService(ApplicationContext applicationContext , Map<String , ThreadPoolExecutor> executorMap){
        String applicationName = applicationContext.getEnvironment().getProperty("spring.application.name");
        if (applicationName == null || applicationName.isEmpty()){
            logger.warn("动态线程池的应用名未配置");
        }

        return new DynamicThreadPoolService(applicationName, executorMap);
    }
}
