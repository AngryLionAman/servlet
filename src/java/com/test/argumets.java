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

/**
 *
 * @author AngryLion
 */
public class argumets {

    protected static void name1(int age, String... names) {
        if (names.length > 0) {
            if (names[0] != null) {
                String[] kaam = null;
                main(kaam);
                System.out.println("Age & Name : " + age + " & " + names[0]);
            }
        } else {
            System.out.println("Age : " + age);
        }
    }

    public static void main(String[] args) {
        name1(16);
        name1(24, "This is aman kumar age");
        name1(444, "This is random number");

        name1(16688888);
        
    }
}
