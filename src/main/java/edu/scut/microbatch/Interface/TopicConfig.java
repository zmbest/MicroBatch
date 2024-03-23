package edu.scut.microbatch.Interface;

import edu.scut.microbatch.MicroBatchManagerImpl;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public interface TopicConfig {
    //进程的工作目录
    String getWorkPath();
    //进程的工作命令
    List<String> getWorkCmd();
    //TopicName
    String getTopicName();
    //一批任务的最大数量
    int getMaxBatchTask();
    //一批任务的划分时间长度
    long getBatchTime();
    //任务持久化
    MissionMapper getMissionMapper();
    //任务队列
    TaskQueue getTaskQueue();
    //获得微批管理者
    MicroBatchManager getManager();
    //获得线程池
    ThreadPoolExecutor getThreadPool();
    //获得最大接受任务数量
    int getMaxTask();
    //获得当前接受的任务数量
    int getTaskCount();
    //更改当前接收任务数量
    void addTaskCount(int num);
    long getProcessWaitTime();
}
