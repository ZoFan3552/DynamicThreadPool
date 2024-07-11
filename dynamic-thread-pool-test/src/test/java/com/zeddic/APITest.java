package com.zeddic;

import com.zeddic.dynamic.thread.pool.domain.model.entity.ThreadPoolConfigEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RTopic;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * @author: zeddic
 * @description: 线程池测试
 * @date: 2024/7/10 下午4:10
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class APITest {

    @Resource
    private RTopic dynamicThreadPoolRedisTopic;

    @Test
    public void test_dynamicThreadPoolRedisTopic() throws InterruptedException {
        ThreadPoolConfigEntity entity = new ThreadPoolConfigEntity();
        entity.setAppName("dynamic-thread-pool-test-app");
        entity.setThreadPoolName("threadPoolExecutor01");
        entity.setPoolSize(100);
        entity.setMaximumPoolSize(100);
        dynamicThreadPoolRedisTopic.publish(entity);

        new CountDownLatch(1).await();
    }
}
