package com.lessons.celac.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ExternalServiceImpl implements Runnable {
    private static final Logger logger =  LoggerFactory.getLogger(ExternalServiceImpl.class);
    private final AtomicBoolean running = new AtomicBoolean(true);

    public String executeExternalRequest() {

    for (int i = 0; i < 100; i++) {
        if(!running.get()){
            break;
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        logger.info("Operation was executed whit number: " + i);
    }
        return null;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" ExternalService");
        executeExternalRequest();
        System.out.println(Thread.currentThread().getName()+" End.");
    }

    public void shutdown() {
            running.set(false);
    }


}
