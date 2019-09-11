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
package com.advertise;

/**
 *
 * @author AngryLion
 */
public class displayAdsPojo {

    int adsId;
    String imageName;
    String imageAlt;
    int imageHeight;
    int imageWidth;
    String promotedBy;
    String forwardUrl;

    public int getAdsId() {
        return adsId;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageAlt() {
        return imageAlt;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public String getPromotedBy() {
        return promotedBy;
    }

    public String getForwardUrl() {
        return forwardUrl;
    }

    public displayAdsPojo(int adsId, String imageName, String imageAlt, int imageHeight, int imageWidth, String promotedBy, String forwardUrl) {
        this.adsId = adsId;
        this.imageName = imageName;
        this.imageAlt = imageAlt;
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        this.promotedBy = promotedBy;
        this.forwardUrl = forwardUrl;
    }
}
