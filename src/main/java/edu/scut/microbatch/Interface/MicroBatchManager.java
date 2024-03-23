package edu.scut.microbatch.Interface;

import java.util.concurrent.ThreadPoolExecutor;

public interface MicroBatchManager extends Runnable{
    void commitTask();
    void start();
    void pause();
}
