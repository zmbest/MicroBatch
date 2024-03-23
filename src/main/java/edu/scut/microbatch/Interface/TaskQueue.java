package edu.scut.microbatch.Interface;

public interface TaskQueue {
    void offer(MicroBatchTask task);
    MicroBatchTask getFirst();
    void removeFirst();
    boolean empty();
    int size();
}
