package edu.scut.microbatch;

import edu.scut.microbatch.Exception.TaskRejectException;
import edu.scut.microbatch.Interface.*;
import javafx.concurrent.Task;

import java.io.File;
import java.util.Date;
import java.util.concurrent.*;

public class TopicChannelImpl implements TopicChannel {
    private String topicName;
    protected ThreadPoolExecutor threadPool;
    protected TaskQueue taskQueue;
    protected TopicConfig topicConfig;
    protected MicroBatchManager manager;
    protected TopicChannelImpl(TopicConfig topicConfig){
        if(topicConfig==null){
            throw (new NullPointerException());
        }
        //初始化变量
        this.topicName=topicConfig.getTopicName();
        this.topicConfig=topicConfig;
        taskQueue=topicConfig.getTaskQueue();
        //启动manager
        manager=topicConfig.getManager();
        manager.start();
    }
    public MicroBatchTask createTask() throws TaskRejectException {
        if(topicConfig.getMaxTask()<=topicConfig.getTaskCount()){
            throw new TaskRejectException();
        }
//        MissionMapper mapper=topicConfig.getMissionMapper();
//        Mission mission=createMission();
//        mapper.insertMission(mission);
        MicroBatchTaskImpl taskRes=new MicroBatchTaskImpl(topicConfig);
        return taskRes;
    }
    public void pushTask(MicroBatchTask task)throws TaskRejectException {
        if(task==null){
            throw new NullPointerException();
        }
        if(topicConfig.getMaxTask()<=topicConfig.getTaskCount()){
            throw new TaskRejectException();
        }
        synchronized (this){
            MissionMapper mapper=topicConfig.getMissionMapper();
            Mission mission=createMission();
            mapper.insertMission(mission);
            task.setMission(mission);
            topicConfig.addTaskCount(1);
            taskQueue.offer(task);
            if(taskQueue.size()>=topicConfig.getMaxBatchTask()){
                manager.pause();
            }
        }
    }
    private Mission createMission(){
        Date date=new Date();
        Mission mission=new Mission();
        mission.setMissionState(MicroBatch.MISSIONCREATED);
        mission.setTopicName(topicName);
        mission.setCreateDate(date);
        return mission;
    }
    public Mission getMission(long missionId){
        return topicConfig.getMissionMapper().getMission(missionId);
    }
    public TopicConfig getTopicConfig(){
        return topicConfig;
    }
}
