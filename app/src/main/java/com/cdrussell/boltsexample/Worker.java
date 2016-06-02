package com.cdrussell.boltsexample;

import android.support.annotation.VisibleForTesting;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

import bolts.Task;

public class Worker {

    @VisibleForTesting
    Executor bgExecutor = Task.BACKGROUND_EXECUTOR;

    public Task<Boolean> doWorkTask() {
        return Task.callInBackground(() -> {

            // simulate doing 'hard' work
            Thread.sleep(1000);
            return true;

        });
    }

    public void doWorkCallback(final WorkCompletionListener callback) {
        Task.call((Callable<Void>) () -> {

            // simulate doing 'hard' work
            Thread.sleep(1000);

            if (callback != null) {
                callback.finished(true);
            }
            return null;
        }, bgExecutor);
    }

    public interface WorkCompletionListener {
        void finished(boolean successful);
    }

}
