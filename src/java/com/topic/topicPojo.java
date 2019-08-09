/*
 * Copyright 2019 inquiryhere.com.
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
 * @author inquiryhere.com
 */
public class topicPojo {

    String topicName;
     int topicId;
    String imageUrl;
    String descHindi;
    String descEng;
    int totalFollowers;
    int relatedQuestion;

    public String getTopicName() {
        return topicName;
    }

    public int getTopicId() {
        return topicId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescHindi() {
        return descHindi;
    }

    public String getDescEng() {
        return descEng;
    }

    public int getTotalFollowers() {
        return totalFollowers;
    }

    public int getRelatedQuestion() {
        return relatedQuestion;
    }

    public topicPojo(String topicName, int topicId, String imageUrl, String descHindi, String descEng, int totalFollowers, int relatedQuestion) {
        this.topicName = topicName;
        this.topicId = topicId;
        this.imageUrl = imageUrl;
        this.descHindi = descHindi;
        this.descEng = descEng;
        this.totalFollowers = totalFollowers;
        this.relatedQuestion = relatedQuestion;
    }
   
}
