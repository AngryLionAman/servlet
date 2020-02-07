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

import com.string.validateInput;

/**
 *
 * @author AngryLion
 */
public class thisClass {

    public static void main(String[] agrs) {
        validateInput input = new validateInput();
        int inputInt = input.getInputInt("@7h3");
        String n = String.valueOf(inputInt);
        System.out.println("value is - " + inputInt);
        System.out.println("length - " + n.length());
      
    }
}
