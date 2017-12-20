package com.example.pp03.peralppay.utils;

/**
 * Created by pp03 on 2017-12-20.
 */

public class GlideImage {
    private final String imageUrl;

    public GlideImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImageId() {
        if (imageUrl.contains("?")) {
            return imageUrl.substring(0, imageUrl.lastIndexOf("?"));
        } else {
            return imageUrl;
        }
    }

}
