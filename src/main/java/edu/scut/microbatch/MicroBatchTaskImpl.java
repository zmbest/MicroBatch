package edu.scut.microbatch;

import edu.scut.microbatch.Interface.MicroBatchTask;
import edu.scut.microbatch.Interface.TopicConfig;
import lombok.extern.java.Log;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class MicroBatchTaskImpl implements MicroBatchTask {
    protected String inputPath;
    protected String outputPath;
    protected String errorPath;
    protected Mission mission;
    protected TopicConfig topicConfig;
    protected ProcessBuilder builder;

    protected MicroBatchTaskImpl(Mission mission, TopicConfig topicConfig){
        this.mission = mission;
        this.topicConfig = topicConfig;
        builder = new ProcessBuilder(topicConfig.getWorkCmd());
        builder.directory(new File(topicConfig.getWorkPath()));
        {
        inputPath=topicConfig.getWorkPath()+mission.getTopicName()+"/input/"+mission.getMissionId();
        outputPath=topicConfig.getWorkPath()+mission.getTopicName()+"/output/"+mission.getMissionId();
        errorPath=topicConfig.getWorkPath()+mission.getTopicName()+"/error/"+mission.getMissionId();
        }
    }
    @Override
    public void run() {
        //设置输入、输出、异常
        setError();
        setInput();
        setOutput();
        try {
            Process p= builder.start();
            boolean timeOut=p.waitFor(topicConfig.getProcessWaitTime(), TimeUnit.MILLISECONDS);
            if(timeOut==true){
                mission.setMissionState(MicroBatch.MISSIONDONE);
            }else{
                mission.setMissionState(MicroBatch.MISSIONTIMEOUT);
            }
        } catch (IOException e) {
            mission.setMissionState(MicroBatch.MISSIONFAIL);
        } catch (InterruptedException e) {
            mission.setMissionState(MicroBatch.MISSIONFAIL);
        }finally {
            topicConfig.addTaskCount(-1);
        }
        try{
            topicConfig.getMissionMapper().updateMission(mission.getMissionId(),mission);
        }catch (Exception e){
        }
    }
    @Override
    public OutputStream getStream() throws IOException{
        OutputStream stream= null;
        stream = new FileOutputStream(new File(inputPath));
        return stream;
    }

    @Override
    public void setOutput() {
        builder.redirectOutput(new File(outputPath));
    }

    @Override
    public void setError() {
        builder.redirectError(new File(errorPath));
    }

    @Override
    public void setInput() {
        File inputFile=new File(inputPath);
        if(inputFile.exists()){
        builder.redirectInput(inputFile);
        }
    }
    @Override
    public Mission getMission(){
        return mission;
    }
}
