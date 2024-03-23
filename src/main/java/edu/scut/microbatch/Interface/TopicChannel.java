package edu.scut.microbatch.Interface;

import edu.scut.microbatch.Exception.TaskRejectException;
import edu.scut.microbatch.MicroBatchTaskImpl;
import edu.scut.microbatch.Mission;

public interface TopicChannel {
    MicroBatchTask createTask() throws TaskRejectException;
    void pushTask(MicroBatchTask task) throws TaskRejectException;
}
