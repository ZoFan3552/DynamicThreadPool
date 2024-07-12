/**
 * @author:     zeddic
 * @description:  获取线程池配置的 api 地址
 * @date:    2024/7/12 上午10:25
 */
import axios from 'axios';

export const threadPoolAPI = axios.create({
    baseURL: 'http://localhost:8089/api/v1/dynamic/thread/pool/query_thread_pool_list'
})