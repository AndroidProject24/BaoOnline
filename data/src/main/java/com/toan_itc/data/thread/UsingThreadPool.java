package com.toan_itc.data.thread;

import com.toan_itc.data.thread.priority.Priority;
import com.toan_itc.data.thread.priority.PriorityRunnable;

import java.util.concurrent.Future;

/**
 * Created by Toan.IT
 * Date: 11/07/2016
 */

public class UsingThreadPool {
     /*
     * Using it for HighPriority Background Tasks
     */

    public void doSomeTaskAtHighPriority(){
        DefaultExecutorSupplier.getInstance().forBackgroundPriorityTasks()
                .submit(new PriorityRunnable(Priority.HIGH) {
                    @Override
                    public void run() {
                        // do some background work here at high priority.
                    }
                });
    }
    /*
    * Using it for Background Tasks
    */
    public void doSomeBackgroundWork(){
        DefaultExecutorSupplier.getInstance().forBackgroundTasks()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        // do some background work here.
                    }
                });
    }

    /*
    * Using it for Light-Weight Background Tasks
    */
    public void doSomeLightWeightBackgroundWork(){
        DefaultExecutorSupplier.getInstance().forLightWeightBackgroundTasks()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        // do some light-weight background work here.
                    }
                });
    }

    /*
    * Using it for MainThread Tasks
    */
    public void doSomeMainThreadWork(){
        DefaultExecutorSupplier.getInstance().forMainThreadTasks()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        // do some Main Thread work here.
                    }
                });
    }
    Future future = DefaultExecutorSupplier.getInstance().forBackgroundTasks()
            .submit(new Runnable() {
                @Override
                public void run() {
                    // do some background work here.
                }
            });
    /*
    * cancelling the task
    */
    //future.cancel(true);

}
