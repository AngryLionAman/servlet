/*
 * Copyright 2020 AngryLion.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author AngryLion
 */
public class PausableThreadPoolExecutor {
    public static void main(String[] args) {

    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    final Object LOCK = new Object();

    threadPoolExecutor.submit(() -> {
        while (true) {
            synchronized (LOCK) {
                System.out.println("Thread 'A' never ends");
            }
            Thread.sleep(1000L);
        }
    });

    threadPoolExecutor.submit(() -> {
        int lifespan = 3;
        while (lifespan > 0) {
            synchronized (LOCK) {
                System.out.println("Thread 'B' is living for " + lifespan + " seconds");
            }
            lifespan--;
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Thread 'B' finished");
    });

    threadPoolExecutor.submit(() -> {
        int lifespan = 3;
        while (lifespan > 0) {
            synchronized (LOCK) {
                System.out.println("Thread 'C' is living for " + lifespan + " seconds");
            }
            lifespan--;
            
            if (lifespan < 1) {
                throw new RuntimeException("lifespan reached zero");
            }
            
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Thread 'C' finished");
    });

    while (true) {
      try {
        Thread.sleep(1000L);
      } catch (InterruptedException e) {
      }
      synchronized (LOCK) {
        System.out.println("==== begin");
        System.out.println("getActiveCount: " + threadPoolExecutor.getActiveCount());
        System.out.println("getCompletedTaskCount: " + threadPoolExecutor.getCompletedTaskCount());
        System.out.println("getPoolSize: " + threadPoolExecutor.getPoolSize());
        System.out.println("==== end");
      }
    }

  }


}
