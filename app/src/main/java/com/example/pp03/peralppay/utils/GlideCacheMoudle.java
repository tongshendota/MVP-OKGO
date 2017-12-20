package com.example.pp03.peralppay.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

/**
 * Created by pp03 on 2017-12-20.
 */

public class GlideCacheMoudle implements GlideModule{
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(
                new InternalCacheDiskCacheFactory(context, "glide_cache", 100 * 1024 * 1024));

    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(GlideImage.class, InputStream.class, new GlideImageLoader.Factory());
    }
}
