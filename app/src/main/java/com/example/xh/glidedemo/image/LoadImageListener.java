package com.example.xh.glidedemo.image;

/**
 * 加载图片监听接口
 * Created by xiehui on 2016/9/1.
 */
public interface LoadImageListener {
    /**
     *
     * 加载成功的回调
     */
    void onSuccess();

    /**
     * 加载失败的回调
     */
    void onError(Exception e, String url);
}
