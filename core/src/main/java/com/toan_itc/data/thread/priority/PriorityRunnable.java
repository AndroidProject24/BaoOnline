package com.toan_itc.data.thread.priority;

/**
 * Created by Toan.IT
 * Date: 11/07/2016
 */

public class PriorityRunnable implements Runnable {

    private final Priority priority;

    public PriorityRunnable(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void run() {
        // nothing to do here.
    }

    public Priority getPriority() {
        return priority;
    }

}