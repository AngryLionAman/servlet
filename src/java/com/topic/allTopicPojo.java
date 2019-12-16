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
package com.topic;

/**
 *
 * @author AngryLion
 */
public class allTopicPojo {

    int topicId;
    String topicName;
    String imageUrl;
    int totalQuestion;
    int totalFollowers;

    /**
     *
     * @param topicId
     * @param topicName
     * @param imageUrl
     * @param totalQuestion
     * @param totalFollowers
     */
    public allTopicPojo(int topicId, String topicName, String imageUrl, int totalQuestion, int totalFollowers) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.imageUrl = imageUrl;
        this.totalQuestion = totalQuestion;
        this.totalFollowers = totalFollowers;
    }

    /**
     *
     * @return
     */
    public int getTopicId() {
        return topicId;
    }

    /**
     *
     * @return
     */
    public String getTopicName() {
        return topicName;
    }

    /**
     *
     * @return
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     *
     * @return
     */
    public int getTotalQuestion() {
        return totalQuestion;
    }

    /**
     *
     * @return
     */
    public int getTotalFollowers() {
        return totalFollowers;
    }
}
