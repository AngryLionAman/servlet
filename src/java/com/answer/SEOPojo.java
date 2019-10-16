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
package com.answer;

/**
 *
 * @author AngryLion
 */
public class SEOPojo {

    int questionId;
    String questionTitle;
    String questionDescription;
    String imageLinkResult;

    public String getImageLinkResult() {
        return imageLinkResult;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public SEOPojo(int questionId, String questionTitle, String questionDescription, String imageLinkResult) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.questionDescription = questionDescription;
        this.imageLinkResult = imageLinkResult;
    }

    public SEOPojo(int questionId, String questionTitle, String questionDescription) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.questionDescription = questionDescription;
    }
}
