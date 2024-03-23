package edu.scut.microbatch.Interface;

import edu.scut.microbatch.MicroBatchManagerImpl;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public interface TopicConfig {
    //���̵Ĺ���Ŀ¼
    String getWorkPath();
    //���̵Ĺ�������
    List<String> getWorkCmd();
    //TopicName
    String getTopicName();
    //һ��������������
    int getMaxBatchTask();
    //һ������Ļ���ʱ�䳤��
    long getBatchTime();
    //����־û�
    MissionMapper getMissionMapper();
    //�������
    TaskQueue getTaskQueue();
    //���΢��������
    MicroBatchManager getManager();
    //����̳߳�
    ThreadPoolExecutor getThreadPool();
    //�����������������
    int getMaxTask();
    //��õ�ǰ���ܵ���������
    int getTaskCount();
    //���ĵ�ǰ������������
    void addTaskCount(int num);
    long getProcessWaitTime();
}
