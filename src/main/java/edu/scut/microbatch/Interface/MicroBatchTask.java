package edu.scut.microbatch.Interface;

import edu.scut.microbatch.Mission;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public interface MicroBatchTask extends Runnable{
    Mission getMission();
    //�Խ��̽�������ʱ�������Ĭ��ʵ���������һ�������ļ��У����̵�����ʹ������ļ��л�ȡ��
    OutputStream getStream() throws IOException;
    //���ý��̵�����������������̨�����ļ�
    void setOutput();
    //���ý��̵Ĵ�����Ϣ�������������̨�����ļ�
    void setError();
    //���ý��̵�����
    void setInput();
}
