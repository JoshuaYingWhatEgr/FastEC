package com.examples.joshuayingwhat.latte.utils.timer;

import java.util.TimerTask;

public class BaseTimerTask extends TimerTask {

    private ITimerListener iTimerListener = null;

    public BaseTimerTask(ITimerListener iTimerListener) {
        this.iTimerListener = iTimerListener;
    }

    @Override
    public void run() {
        if (iTimerListener != null) {
            iTimerListener.onTimer();
        }
    }
}
