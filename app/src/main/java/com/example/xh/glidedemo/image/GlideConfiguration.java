package com.example.xh.glidedemo.image;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * 自定义GlideModule
 * Created by xiehui on 2016/9/1.
 */
public class GlideConfiguration implements GlideModule {
    private File cacheLocation;
    private File file;

    @Override
    public void applyOptions(final Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //内存缓存
        MemorySizeCalculator memorySizeCalculator=new MemorySizeCalculator(context);
        int defaultMemoryCacheSize=memorySizeCalculator.getMemoryCacheSize();
        int defalutBitmapPoolSize=memorySizeCalculator.getBitmapPoolSize();
        builder.setMemoryCache(new LruResourceCache((int)(defalutBitmapPoolSize*1.2)));//内部
        builder.setBitmapPool(new LruBitmapPool((int)(defalutBitmapPoolSize*1.2)));

        //磁盘缓存
        //builder.setDiskCache(new InternalCacheDiskCacheFactory(context,1024*1024*10));//内部磁盘缓存
        //builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,10*1024*1024));//磁盘缓存到外部存储
        //指定缓存目录1
        String downLoadPath= Environment.getDownloadCacheDirectory().getPath();
        String path=Environment.getExternalStorageDirectory() + File.separator +"jqgj"+ File.separator+ "Images";
        file=new File(path);
        if (!file.exists()){
           file.mkdirs(); 
        }
        //builder.setDiskCache(new DiskLruCacheFactory(downLoadPath,defaultMemoryCacheSize));
        cacheLocation=new File(context.getExternalCacheDir(),"jqgj");
        //指定缓存目录2
        builder.setDiskCache(new DiskCache.Factory() {
            @Override
            public DiskCache build() {
                cacheLocation.mkdirs();
                return DiskLruCacheWrapper.get(file,1024*1024*10);
            }
        });
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
