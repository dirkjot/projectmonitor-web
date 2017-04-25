package com.projectmonitor.productionrelease;

import org.springframework.stereotype.Component;

@Component
public class ThreadSleepService {

    public void sleep(long millisToSleep) throws InterruptedException {
        Thread.sleep(millisToSleep);
    }
}
