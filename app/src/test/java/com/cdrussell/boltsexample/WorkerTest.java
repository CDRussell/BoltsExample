package com.cdrussell.boltsexample;

import org.junit.Assert;
import org.junit.Test;

import bolts.Task;

import static org.junit.Assert.assertTrue;

public class WorkerTest {

    @Test
    public void directTaskAccess() throws InterruptedException {
        Task<Boolean> task = new Worker().doWorkTask();
        task.waitForCompletion();
        assertTrue(task.getResult());
    }

    @Test
    public void noDirectTaskAccess() throws InterruptedException {
        Worker worker = new Worker();
        worker.bgExecutor = Runnable::run;
        worker.doWorkCallback(Assert::assertTrue);
    }
}