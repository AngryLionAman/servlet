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

import java.util.Objects;

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
    boolean crawl;
    int totalFollowers;
    int relatedQuestion;

    public topicPojo(String topicName, int topicId, String imageUrl, String descHindi, String descEng, boolean crawl, int totalFollowers, int relatedQuestion) {
        this.topicName = topicName;
        this.topicId = topicId;
        this.imageUrl = imageUrl;
        this.descHindi = descHindi;
        this.descEng = descEng;
        this.crawl = crawl;
        this.totalFollowers = totalFollowers;
        this.relatedQuestion = relatedQuestion;
    }

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

    public boolean isCrawl() {
        return crawl;
    }

    public int getTotalFollowers() {
        return totalFollowers;
    }

    public int getRelatedQuestion() {
        return relatedQuestion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.topicName);
        hash = 61 * hash + this.topicId;
        hash = 61 * hash + Objects.hashCode(this.imageUrl);
        hash = 61 * hash + Objects.hashCode(this.descHindi);
        hash = 61 * hash + Objects.hashCode(this.descEng);
        hash = 61 * hash + (this.crawl ? 1 : 0);
        hash = 61 * hash + this.totalFollowers;
        hash = 61 * hash + this.relatedQuestion;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final topicPojo other = (topicPojo) obj;
        if (this.topicId != other.topicId) {
            return false;
        }
        if (this.crawl != other.crawl) {
            return false;
        }
        if (this.totalFollowers != other.totalFollowers) {
            return false;
        }
        if (this.relatedQuestion != other.relatedQuestion) {
            return false;
        }
        if (!Objects.equals(this.topicName, other.topicName)) {
            return false;
        }
        if (!Objects.equals(this.imageUrl, other.imageUrl)) {
            return false;
        }
        if (!Objects.equals(this.descHindi, other.descHindi)) {
            return false;
        }
        if (!Objects.equals(this.descEng, other.descEng)) {
            return false;
        }
        return true;
    }
    
}
