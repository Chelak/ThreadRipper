package com.lessons.celac.demo.components;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class LocalExecutorService {



  public ExecutorService getLocalExecutionService() {
      ThreadFactory threadFactory = Executors.defaultThreadFactory();
      ThreadPoolExecutor executorService =
              new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                      new ArrayBlockingQueue<Runnable>(1), threadFactory);

    return executorService;
  }
}
