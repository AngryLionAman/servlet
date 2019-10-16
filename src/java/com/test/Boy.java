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

import java.util.Scanner;

/**
 *
 * @author AngryLion
 */
public class Boy {

    public static void main(String[] args) {
        
        while (true) {
            Girl call = new Girl();
            Scanner input = new Scanner(System.in);
            System.out.println("Message :- ");
            String message = input.nextLine();
            String response = call.Girl(message);
            System.err.println("Response : - " + response);
        }

    }
}
