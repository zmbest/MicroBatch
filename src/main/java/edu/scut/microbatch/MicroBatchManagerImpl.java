package edu.scut.microbatch;

import edu.scut.microbatch.Interface.MicroBatchManager;
import edu.scut.microbatch.Interface.MicroBatchTask;
import edu.scut.microbatch.Interface.TaskQueue;
import edu.scut.microbatch.Interface.TopicConfig;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

public class MicroBatchManagerImpl implements MicroBatchManager {
    protected TopicConfig config;
    protected TaskQueue taskQueue;
    protected ThreadPoolExecutor threadPoolExecutor;
    private Thread thread;
    boolean running=true;
    protected MicroBatchManagerImpl(TopicConfig config){
        if(config==null){
            throw (new NullPointerException());
        }
        this.config=config;
        taskQueue=config.getTaskQueue();
        threadPoolExecutor=config.getThreadPool();
    }
    @Override
    public void run() {
        {
            new File(config.getWorkPath() + config.getTopicName()+ "/input").mkdirs();
            new File(config.getWorkPath() + config.getTopicName() + "/output").mkdirs();
            new File(config.getWorkPath() + config.getTopicName() + "/error").mkdirs();
        }
        long sleepTime=config.getBatchTime();
        while(running){
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {

            }finally {
                commitTask();
            }
        }
    }
    @Override
    public void pause() {
        thread.interrupt();
    }
    public void commitTask(){
        synchronized (taskQueue){
            while(!taskQueue.empty()){
                MicroBatchTask task=taskQueue.getFirst();
                    try {
                        threadPoolExecutor.submit(task);
                        taskQueue.removeFirst();
                    }catch (Exception e){
                        break;
                    }
            }
        }
    }
    @Override
    public void start(){
        thread=new Thread(this);
        thread.start();
    }


}
