package edu.scut.microbatch.Interface;

import edu.scut.microbatch.Mission;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public interface MicroBatchTask extends Runnable{
    Mission getMission();
    OutputStream getStream() throws IOException;
    void setOutput();
    void setError();
    void setInput();
}
