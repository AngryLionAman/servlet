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

import java.util.Date;

/**
 *
 * @author AngryLion
 */
public class BlogByUserIdPojo {

    int blogId;
    String blogSub;
    Date lastModifiedTime;
    int postedById;
    int totalView;

    /**
     *
     * @param blogId
     * @param blogSub
     * @param lastModifiedTime
     * @param postedById
     * @param totalView
     */
    public BlogByUserIdPojo(int blogId, String blogSub, Date lastModifiedTime, int postedById, int totalView) {
        this.blogId = blogId;
        this.blogSub = blogSub;
        this.lastModifiedTime = lastModifiedTime;
        this.postedById = postedById;
        this.totalView = totalView;
    }

    /**
     *
     * @return
     */
    public int getTotalView() {
        return totalView;
    }

    /**
     *
     * @return
     */
    public int getPostedById() {
        return postedById;
    }

    /**
     *
     * @return
     */
    public int getBlogId() {
        return blogId;
    }

    /**
     *
     * @return
     */
    public String getBlogSub() {
        return blogSub;
    }

    /**
     *
     * @return
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }
}
