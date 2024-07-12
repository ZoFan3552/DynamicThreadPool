/**
 * @author:     zeddic
 * @description:  线程池页面
 * @date:    2024/7/12 上午10:19
 */

import React, {useEffect} from 'react';
import {Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper} from '@mui/material';
import {ThreadPoolConfigEntity} from "@/src/type/ThreadPoolConfigEntity";
import {threadPoolAPI} from "@/src/apis/thread-pool-api";


const fetchThreadPoolConfig = async () => {
    const response = await threadPoolAPI.get('');
    return response.data;
}


const ThreadPoolPage: React.FC = () => {
    const [threadPoolConfigList, setThreadPoolConfigList] = React.useState<ThreadPoolConfigEntity[]>([]);

    useEffect(() => {
        fetchThreadPoolConfig()
            .then(response => setThreadPoolConfigList(response.data))
            .catch(error => console.error(error));
    }, []);
    return (
        <TableContainer component={Paper}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>应用名称</TableCell>
                        <TableCell>线程池名称</TableCell>
                        <TableCell>核心线程数</TableCell>
                        <TableCell>最大线程数</TableCell>
                        <TableCell>当前活跃线程数</TableCell>
                        <TableCell>当前池中线程数</TableCell>
                        <TableCell>队列类型</TableCell>
                        <TableCell>当前队列任务数</TableCell>
                        <TableCell>队列剩余任务数</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {threadPoolConfigList.map((row, index) => (
                        <TableRow key={index}>
                            <TableCell>{row.appName}</TableCell>
                            <TableCell>{row.threadPoolName}</TableCell>
                            <TableCell>{row.corePoolSize}</TableCell>
                            <TableCell>{row.maximumPoolSize}</TableCell>
                            <TableCell>{row.activeCount}</TableCell>
                            <TableCell>{row.poolSize}</TableCell>
                            <TableCell>{row.queueType}</TableCell>
                            <TableCell>{row.queueSize}</TableCell>
                            <TableCell>{row.remainingCapacity}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
};

export default ThreadPoolPage;
