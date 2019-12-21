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
class Main {

    public int modalSet;
    public int questionId;
    public String answerId;

    Main(int modalSet, int questionId, String answerId) {
        this.modalSet = modalSet;
        this.questionId = questionId;
        this.answerId = answerId;
    }

    public static void main(String args[]) {
        // declaring and initializing 2D array 
        int modalSetId = 2;
        int arraySize = 5;
        int questionId = 4;
        int answerId = 3;

       // int array[][][][] = new int[answerId][answerId][answerId][answerId];

        Main[] obj = new Main[3];
        obj[0] = new Main(modalSetId, questionId, "A");
        obj[1] = new Main(modalSetId, questionId, "b");
        obj[2] = new Main(0, 0, null);

        // printing 3D array 
        try{
            for(int i =0 ; i< obj.length; i++){
                obj[i] = new Main(0, 0, null);
            }
            
            
           for (int i = 0; i < obj.length; i++) {
            System.out.println(i +"->"+ obj[i].answerId +","+ obj[i].modalSet +","+ obj[i].questionId +"\n");
        }  
        }catch(Exception msg){
            System.out.println("com.test.Main.main()"+msg);
        }
       

    }
}
