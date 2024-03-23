package edu.scut.microbatch.Interface;

import edu.scut.microbatch.Mission;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public interface MicroBatchTask extends Runnable{
    Mission getMission();
    //对进程进行输入时获得流。默认实现是输出到一个输入文件中，进程的输入就从输入文件中获取。
    OutputStream getStream() throws IOException;
    //设置进程的输出，可输出到控制台或者文件
    void setOutput();
    //设置进程的错误信息，可输出到控制台或者文件
    void setError();
    //设置进程的输入
    void setInput();
}
