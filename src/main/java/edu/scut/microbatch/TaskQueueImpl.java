package edu.scut.microbatch;

import edu.scut.microbatch.Interface.MicroBatchTask;
import edu.scut.microbatch.Interface.TaskQueue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class TaskQueueImpl implements TaskQueue {
    protected BlockingDeque<MicroBatchTask> taskQueue;
    protected TaskQueueImpl(){
        taskQueue=new LinkedBlockingDeque<>();
    }

    @Override
    public void offer(MicroBatchTask task) {
        taskQueue.offer(task);
    }

    @Override
    public MicroBatchTask getFirst() {
        return taskQueue.getFirst();
    }

    @Override
    public void removeFirst() {
        taskQueue.removeFirst();
    }
    @Override
    public boolean empty() {
        return taskQueue.isEmpty();
    }

    @Override
    public int size() {
        return taskQueue.size();
    }
}
