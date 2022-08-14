package com.example.android.studio.ja_ar.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager{
  private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
  public static void taskInThread(Runnable task){
    executorService.submit(()->task.run());
  }
  @Override
  public void finalize() throws Throwable{
    if(executorService != null && !executorService.isShutdown()){
      executorService.shutdown();
    }
    super.finalize();
  }
}

