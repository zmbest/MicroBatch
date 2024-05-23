package edu.scut.microbatch.Interface;

import java.util.concurrent.ThreadPoolExecutor;

public interface MicroBatchManager extends Runnable{
    //�ύ����
    void commitTask();
    //���������ߣ�ֻ��ִ��һ��
    void start();
    //��ͣ�����ߣ�ָ�����ù�����ֹͣ���ȣ�ֱ����������
    void pause();
}