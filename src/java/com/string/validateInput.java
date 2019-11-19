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
package com.string;

/**
 *
 * @author AngryLion
 */
public class validateInput {

    public int getInputInt(String option) {
        int id = 0;
        if (option == null) {
            return 0;
        }
        if (option.isEmpty()) {
            return 0;
        }
        if (!option.isEmpty()) {
            id = Integer.parseInt(option);
        }
        return id;
    }

    public String getInputString(String parameter) {
        String val;
        if (parameter.isEmpty()) {
            val = null;
        } else {
            val = parameter.trim();
        }
        return val;
    }

    public int remove(String word) {
        word = word.trim().replaceAll("[^0-9]", "");
        return Integer.valueOf(word);
    }

}
