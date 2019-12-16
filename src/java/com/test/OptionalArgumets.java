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

import java.util.Arrays;

/**
 *
 * @author AngryLion
 */
public class OptionalArgumets {

    /**
     *
     * @param age
     * @param names
     */
    protected static void name1(int age, String... names) {
        if (names.length > 0) {
            if (names[0] != null) {
                System.out.println("Age & Name : " + age + " & " + names[0]);
            }
        } else {
            System.out.println("Age : " + age);
        }
    }

    /**
     *
     * @param name
     * @param age
     */
    public void test(String name, int... age) {//Varargs
        if(age.length > 0){
                
            String toString = Arrays.toString(age);
            
            for(int i = 0 ; i< toString.length(); i++){
                System.out.println("\n Age - " + Arrays.toString(age));
            }
         
        }
        System.out.println("\n Name - " + name);
        //System.out.println("\n Age - " + Arrays.toString(age));
    }

    /**
     *
     * @param name
     * @param age
     */
    public void load(String name,int age){
        System.out.println("\nName - "+name);
        System.out.println("age - "+age);
        
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        
        //name1(16);
        //name1(24, "This is aman kumar age");
        //name1(444, "This is random number");
        //name1(16688888);
        OptionalArgumets obj = new OptionalArgumets();
        String name = "this is aman kumar";
        obj.load(name, 55);
        obj.load(null,55);
        //obj.test("aman", 25);
        //obj.test("aman", 6,9);
        //obj.test("aman");
        //obj.test("aman", 6,9,7);

    }
}
