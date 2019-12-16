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

    /**
     *
     * @param args
     * @return
     */
    public int getInputInt(String args) {

        if (args == null) {
            return 0;
        } else if (args.isEmpty()) {
            return 0;
        } else if ((args.trim()).equals("null")) {
            return 0;
        } else if (args.equals("")) {
            return 0;
        } else {
            return Integer.parseInt(args);
        }
    }

    /**
     *
     * @param args
     * @return
     */
    public String getInputString(String args) {

        if (args == null) {
            return null;
        } else if (args.isEmpty()) {
            return null;
        } else if (args.trim().isEmpty()) {
            return null;
        } else if (((args.trim()).equals("null"))) {
            return null;
        } else if (args.equals("")) {
            return null;
        } else {
            return args.trim();
        }
    }

    /**
     *
     * @param args
     * @return
     */
    public int getOnlyInteger(String args) {
        return Integer.valueOf(args.trim().replaceAll("[^0-9]", ""));
    }

}
