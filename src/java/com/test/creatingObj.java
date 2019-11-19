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
import java.util.List;

/**
 *
 * @author AngryLion
 */
public class creatingObj {
    String name;
    int id;

    public creatingObj(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static void main(String[] args) {
        List<creatingObj> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            String name = String.valueOf(i);
            
            list.add(new creatingObj(name, i));
            System.out.println(i + "com.test.creatingObj.main()\n");
        }
        list.clear();
    }

}
