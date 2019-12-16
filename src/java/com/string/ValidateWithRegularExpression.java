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

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author AngryLion
 */
public class ValidateWithRegularExpression {

    /**
     *
     * @param args
     * @return
     */
    public boolean validateEamil(String args) {
        try {
            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            return args.matches(ePattern);
        } catch (Exception msg) {
            Logger.getLogger(ValidateWithRegularExpression.class.getName()).log(Level.SEVERE, args, msg);
        }
        return false;
    }

    /**
     *
     * @param agrs
     * @return
     */
    public boolean validateFullName(String agrs) {
        int length = agrs.length();
        if (length < 25) { //If the name length is too long
            Pattern p = Pattern.compile("[^A-Za-z0-9\\s]");
            Matcher m = p.matcher(agrs);
            boolean b = m.find();
            if (b != true) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param agrs
     * @return
     */
    public boolean validateMobileNumber(String agrs) {

        String pattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        return agrs.matches(pattern);
    }

    /**
     *
     * @param args
     * @return
     */
    public boolean validatePassword(String args) {
        return args.length() >= 6;
    }
}
