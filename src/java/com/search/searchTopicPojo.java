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
package com.search;

/**
 *
 * @author AngryLion
 */
public class searchTopicPojo {

    int topicId;
    String topicName;
    int totalRelatedQuestion;
    String imagePath;

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
    public int getTotalRelatedQuestion() {
        return totalRelatedQuestion;
    }

    /**
     *
     * @return
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     *
     * @param topicId
     * @param topicName
     * @param totalRelatedQuestion
     * @param imagePath
     */
    public searchTopicPojo(int topicId, String topicName, int totalRelatedQuestion, String imagePath) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.totalRelatedQuestion = totalRelatedQuestion;
        this.imagePath = imagePath;
    }
}
