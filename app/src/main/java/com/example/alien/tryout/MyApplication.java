package com.example.alien.tryout;

import android.app.Application;
import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by toyosi.adebayo-ige on 22/11/2017.
 */

public class MyApplication extends Application {


    private static File httpCacheDirectory;
    private static Cache cache;

    private static OkHttpClient.Builder okHttpClientBuilder;
    private static Picasso picassoWithCache;

    public static Picasso picassoWithCache(Context context) {
        if (picassoWithCache == null) {
            cache = new Cache(httpCacheDirectory, 15 * 1024 * 1024);
            httpCacheDirectory = new File(context.getCacheDir(), "picasso-cache");
            okHttpClientBuilder = new OkHttpClient.Builder().cache(cache);
            picassoWithCache = new Picasso.Builder(context).downloader(new OkHttp3Downloader(okHttpClientBuilder.build())).build();
        }
        return picassoWithCache;
    }
}
