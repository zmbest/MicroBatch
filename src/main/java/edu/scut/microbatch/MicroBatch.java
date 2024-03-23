package edu.scut.microbatch;

import edu.scut.microbatch.Interface.TopicChannel;
import edu.scut.microbatch.Interface.TopicConfig;

import java.util.HashMap;
import java.util.Map;

public class MicroBatch{
    public static String MISSIONCREATED="MissionCreated";
    public static String MISSIONTIMEOUT="MissionTimeOut";
    public static String MISSIONDONE="MissionDone";
    public static String MISSIONFAIL="MissionFail";
    private Map<String, TopicChannel> channel;
    public MicroBatch(){
        channel=new HashMap<>();
    }
    public TopicChannel getTopicChannel(String topicName){
        return channel.get(topicName);
    }
    public synchronized TopicChannel createDefaultTopicChannel(TopicConfig topicConfig){
        if(channel.get(topicConfig.getTopicName())!=null){
            throw new RuntimeException("主题已创建");
        }
        TopicChannel topicChannelImpl =new TopicChannelImpl(topicConfig);
        channel.put(topicConfig.getTopicName(), topicChannelImpl);
        return topicChannelImpl;
    }
    public synchronized void createTopicChannel(TopicChannel topicChannel,String topicName){
        channel.put(topicName,topicChannel);
    }
    public synchronized boolean deleteTopicChannel(String topicName){
        channel.remove(topicName);
        return true;
    }
//    public synchronized void createTopicChannel(TopicChannel topicChannel,String topicName){
//        if(channel.get(topicName)!=null){
//            throw new RuntimeException("主题已创建");
//        }
//        channel.put(topicName,topicChannel);
//    }

}
