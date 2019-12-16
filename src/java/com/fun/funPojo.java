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
package com.fun;

/**
 *
 * @author AngryLion
 */
public class funPojo {

    int id;
    int postedBy;
    String title;
    String desc;
    String category;
    String basedOn;
    String type;

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public int getPostedBy() {
        return postedBy;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return
     */
    public String getDesc() {
        return desc;
    }

    /**
     *
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @return
     */
    public String getBasedOn() {
        return basedOn;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param id
     * @param postedBy
     * @param title
     * @param desc
     * @param category
     * @param basedOn
     * @param type
     */
    public funPojo(int id, int postedBy, String title, String desc, String category, String basedOn, String type) {
        this.id = id;
        this.postedBy = postedBy;
        this.title = title;
        this.desc = desc;
        this.category = category;
        this.basedOn = basedOn;
        this.type = type;
    }
}
