package com.example.pp03.peralppay.utils;

import android.content.Context;

import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.HttpUrlFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.stream.StreamModelLoader;

import java.io.InputStream;

/**
 * Created by pp03 on 2017-12-20.
 */

public class GlideImageLoader implements StreamModelLoader<GlideImage> {
    @Override
    public DataFetcher<InputStream> getResourceFetcher(final GlideImage model, int width, int height) {
        return new HttpUrlFetcher(new GlideUrl(model.getImageUrl())) {
            @Override
            public String getId() {
                return model.getImageId();
            }
        };

    }
    public static class Factory implements ModelLoaderFactory<GlideImage, InputStream> {
        @Override
        public ModelLoader<GlideImage, InputStream> build(Context context,
                                                          GenericLoaderFactory factories) {
            return new GlideImageLoader();
        }

        @Override
        public void teardown() { /* no op */ }
    }

}
