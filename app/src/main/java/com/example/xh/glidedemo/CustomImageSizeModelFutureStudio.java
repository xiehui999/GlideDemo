package com.example.xh.glidedemo;

/**
 * Created by xiehui on 2016/8/24.
 */
public class CustomImageSizeModelFutureStudio implements CustomImageSizeModel{
    public String baseImageUrl;
    public CustomImageSizeModelFutureStudio(String baseImageUrl){
        this.baseImageUrl=baseImageUrl;
    }
    @Override
    public String requestCustomSizeUrl(int width, int height) {

        return baseImageUrl+"?w="+width+"&h="+height;
    }
}
