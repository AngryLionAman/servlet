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

import com.string.validateInput;

/**
 *
 * @author AngryLion
 */
public class NewClass1 {
  public static void main(String[] args){
      validateInput input = new validateInput();
      System.out.println( input.getOnlyInteger("aman8kjsadkf843982@@"));
      System.out.println( input.getInputInt("8888"));
      
  }
}
