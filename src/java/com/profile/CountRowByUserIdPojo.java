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
package com.profile;

/**
 *
 * @author AngryLion
 */
public class CountRowByUserIdPojo {

    int countQestion;
    int countAnswer;
    int countTopic;
    int countFollowing;
    int countFollowers;
    int countBlog;

    /**
     *
     * @param countQestion
     * @param countAnswer
     * @param countTopic
     * @param countFollowing
     * @param countFollowers
     * @param countBlog
     */
    public CountRowByUserIdPojo(int countQestion, int countAnswer, int countTopic, int countFollowing, int countFollowers, int countBlog) {
        this.countQestion = countQestion;
        this.countAnswer = countAnswer;
        this.countTopic = countTopic;
        this.countFollowing = countFollowing;
        this.countFollowers = countFollowers;
        this.countBlog = countBlog;
    }

    /**
     *
     * @return
     */
    public int getCountQestion() {
        return countQestion;
    }

    /**
     *
     * @return
     */
    public int getCountAnswer() {
        return countAnswer;
    }

    /**
     *
     * @return
     */
    public int getCountTopic() {
        return countTopic;
    }

    /**
     *
     * @return
     */
    public int getCountFollowing() {
        return countFollowing;
    }

    /**
     *
     * @return
     */
    public int getCountFollowers() {
        return countFollowers;
    }

    /**
     *
     * @return
     */
    public int getCountBlog() {
        return countBlog;
    }
}
