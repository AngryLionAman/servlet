/*
 * Copyright 2019 AngryLion.
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

import java.util.ArrayList;

/**
 *
 * @author AngryLion
 */
public class MemoryUtil{

       private static final int MegaBytes = 10241024;

       public static void main(String args[]) {

              long freeMemory = Runtime.getRuntime().freeMemory()/MegaBytes;
              long totalMemory = Runtime.getRuntime().totalMemory()/MegaBytes;
              long maxMemory = Runtime.getRuntime().maxMemory()/MegaBytes;

              System.out.println("JVM freeMemory: " + freeMemory);
              System.out.println("JVM totalMemory also equals to initial heap size of JVM : "+ totalMemory);
              System.out.println("JVM maxMemory also equals to maximum heap size of JVM: "+ maxMemory);

              ArrayList objects = new ArrayList();

              for (int i = 0; i < 10000000; i++) {
                     objects.add(("" + 10 * 2710));
              }

              freeMemory = Runtime.getRuntime().freeMemory() / MegaBytes;
              totalMemory = Runtime.getRuntime().totalMemory() / MegaBytes;
              maxMemory = Runtime.getRuntime().maxMemory() / MegaBytes;

              System.out.println("Used Memory in JVM: " + (maxMemory - freeMemory));
              System.out.println("freeMemory in JVM: " + freeMemory);
              System.out.println("totalMemory in JVM shows current size of java heap : "
                                         + totalMemory);
              System.out.println("maxMemory in JVM: " + maxMemory);

       }
}