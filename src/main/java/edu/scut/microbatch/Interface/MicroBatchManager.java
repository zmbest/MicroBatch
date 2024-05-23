package edu.scut.microbatch.Interface;

import java.util.concurrent.ThreadPoolExecutor;

public interface MicroBatchManager extends Runnable{
    //提交任务
    void commitTask();
    //开启管理者，只被执行一次
    void start();
    //暂停管理者，指的是让管理者停止调度，直接推送任务
    void pause();
}