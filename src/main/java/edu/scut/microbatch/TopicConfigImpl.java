package edu.scut.microbatch;

import edu.scut.microbatch.Interface.MicroBatchManager;
import edu.scut.microbatch.Interface.MissionMapper;
import edu.scut.microbatch.Interface.TaskQueue;
import edu.scut.microbatch.Interface.TopicConfig;
import lombok.Data;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Data
public class TopicConfigImpl implements TopicConfig {
    //channel�Ĺ���Ŀ¼
    protected String workPath="./";
    //���ӵĹ���ָ���һ��Ϊ��ִ�г���
    protected List<String> workCmd;
    //���������񣬴���������񳬹���������ֱ��ִ�С�
    protected int maxBatchTask;
    //������ʱ�䣬�����ʱ�����ڵ�����������һ������,��λ΢��
    protected long batchTime;
    protected TaskQueue taskQueue;
    protected MissionMapper missionMapper;
    protected ThreadPoolExecutor threadPool;
    protected MicroBatchManagerImpl manager;
    protected String topicName;
    protected int maxTask;
    protected int taskCount;
    protected long processWaitTime;
    public String getWorkPath() {
        return workPath;
    }
    public List<String> getWorkCmd() {
        return workCmd;
    }

    @Override
    public String getTopicName() {
        return topicName;
    }

    public int getMaxBatchTask() {
        return maxBatchTask;
    }
    public long getBatchTime() {
        return batchTime;
    }
    public MissionMapper getMissionMapper() {
        return missionMapper;
    }
    public ThreadPoolExecutor getThreadPool() {
        return threadPool;
    }

    @Override
    public synchronized void addTaskCount(int num) {
        taskCount+=num;
    }


    public TaskQueue getTaskQueue() {
        return taskQueue;
    }

    @Override
    public MicroBatchManager getManager() {
        return manager;
    }

    public int getMaxTask() {
        return maxTask;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public long getProcessWaitTime() {
        return processWaitTime;
    }

    public void setProcessWaitTime(long processWaitTime) {
        this.processWaitTime = processWaitTime;
    }

    public TopicConfigImpl(List<String> workCmd, MissionMapper missionMapper, String topicName){
        if(workCmd==null||missionMapper==null){
            throw(new NullPointerException());
        }
        this.missionMapper=missionMapper;
        maxBatchTask=3;
        batchTime=100;
        this.workCmd=workCmd;
        threadPool=new ThreadPoolExecutor(5,10,100,
                TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
        taskQueue=new TaskQueueImpl();
        manager=new MicroBatchManagerImpl(this);
        this.topicName=topicName;
        taskCount=0;
        maxTask=20;
        processWaitTime=1000;
    }

    public void setWorkPath(String workPath) {
        this.workPath = workPath;
    }

    public void setWorkCmd(List<String> workCmd) {
        this.workCmd = workCmd;
    }

    public void setMaxBatchTask(int maxBatchTask) {
        this.maxBatchTask = maxBatchTask;
    }

    public void setBatchTime(long batchTime) {
        this.batchTime = batchTime;
    }

    public void setTaskQueue(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void setMissionMapper(MissionMapper missionMapper) {
        this.missionMapper = missionMapper;
    }

    public void setThreadPool(ThreadPoolExecutor threadPool) {
        this.threadPool = threadPool;
    }

    public void setManager(MicroBatchManagerImpl manager) {
        this.manager = manager;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setMaxTask(int maxTask) {
        this.maxTask = maxTask;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }


}
