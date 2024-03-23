package edu.scut.microbatch.Interface;

import edu.scut.microbatch.MicroBatchTaskImpl;
import edu.scut.microbatch.Mission;

public interface TopicChannel {
    MicroBatchTask createTask();
    void pushTask(MicroBatchTask task);
}
