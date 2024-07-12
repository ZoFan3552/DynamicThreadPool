/**
 * @author:     zeddic
 * @description:  线程池配置实体类
 * @date:    2024/7/12 上午10:29
 */

export interface ThreadPoolConfigEntity {
    appName: string;
    threadPoolName: string;
    corePoolSize: number;
    maximumPoolSize: number;
    activeCount: number;
    poolSize: number;
    queueType: string;
    queueSize: number;
    remainingCapacity: number;
}