package com.example.xh.glidedemo.image;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.GifTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.xh.glidedemo.R;

/**
 * Created by xiehui on 2016/9/1.
 */
public class LoadImage<U> {


    private LoadImageListener listener;

    /**
     * 根据GlideSetting设置加载图片
     * @param url
     * @param imageView
     * @param setting
     * @param listener
     * @param <U>
     */
    public static <U> void LoadImage(U url, ImageView imageView, GlideSetting setting, LoadImageListener listener) {
        load(url, imageView.getContext(), imageView, setting, listener);
    }

    /**
     * 根据GlideSetting设置加载图片
     * @param url
     * @param context
     * @param imageView
     * @param setting
     * @param listener
     * @param <U>
     */
    public static <U> void LoadImage(U url, Context context, ImageView imageView, GlideSetting setting, LoadImageListener listener) {
        load(url, context, imageView, setting, listener);
    }

    /**
     * 根据GlideSetting设置加载图片最终执行方法，不对外公布
     * @param url
     * @param context
     * @param imageView
     * @param setting
     * @param listener
     * @param <U>
     */
    private static <U> void load(U url, Context context, ImageView imageView, GlideSetting setting, final LoadImageListener listener) {
        if (url == null || imageView == null) {
            throw new IllegalArgumentException("url or imageView are not allowed to be null");
        }
        if (setting == null) {
            Glide.with(context).load(url).into(imageView);
        } else {
            GenericRequestBuilder requestBuilder;
            if (setting.isAsGif()) {
                GifTypeRequest<U> gifRequest = Glide.with(context).load(url).asGif();
                requestBuilder = gifRequest;
            } else if (setting.isCrossFade()) {
                DrawableRequestBuilder drawableRequestBuilder = null;
                if (setting.getCrossDuration() <= 300) {
                    drawableRequestBuilder = Glide.with(imageView.getContext()).load(url).crossFade();
                } else {
                    drawableRequestBuilder = Glide.with(imageView.getContext()).load(url).crossFade(setting.getCrossDuration());
                }
                requestBuilder = drawableRequestBuilder;
            } else {
                BitmapRequestBuilder<U, Bitmap> bitmapTypeRequest = Glide.with(imageView.getContext()).load(url).asBitmap().dontAnimate();
                requestBuilder = bitmapTypeRequest;
            }

            if (setting.getPlaceHolderResId() != 0) {
                requestBuilder.placeholder(setting.getPlaceHolderResId());
            }
            if (setting.getErrorResId() != 0) {
                requestBuilder.error(setting.getErrorResId());
            }
            if (setting.getThumbnail() < 0f || setting.getThumbnail() > 1f) {
                throw new IllegalArgumentException("The thumbnail vaule must be between 0 and 1");
            } else if (setting.getThumbnail() != 0) {
                requestBuilder.thumbnail(setting.getThumbnail());
            }
            if (listener != null) {
                requestBuilder.listener(new RequestListener() {
                    @Override
                    public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                        listener.onError(e, model.toString());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                        listener.onSuccess();
                        return false;
                    }
                });
            }
            requestBuilder.into(imageView);
        }

    }

    /**
     *  加载图片
     * @param url  url
     * @param context Context对象
     * @param imageView  ImageView对象
     * @param listener   加载图片状态的监听
     *
     */
    public static <U> void LoadImageContext(U url, Context context, ImageView imageView, final LoadImageListener listener) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.loadfail)
                .listener(new RequestListener<U, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, U model, Target<Bitmap> target, boolean isFirstResource) {
                        if (listener != null) {
                            listener.onError(e, model.toString());
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, U model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (listener != null) {
                            listener.onSuccess();
                        }
                        return false;
                    }
                })
                .into(imageView);
    }

    /**
     * 加载图片到imageView
     * @param url       加载的地址
     * @param activity  Activity对象
     * @param imageView 图片展示的ImageView控件
     * @param listener  加载图片监听回调
     * @param <U>       泛型
     */
    public static <U> void LoadImageActivity(U url, Activity activity, ImageView imageView, final LoadImageListener listener) {
        Glide.with(activity)
                .load(url)
                .asBitmap()
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.loadfail)
                .listener(new RequestListener<U, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, U model, Target<Bitmap> target, boolean isFirstResource) {
                        if (listener != null) {
                            listener.onError(e, model.toString());
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, U model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (listener != null) {
                            listener.onSuccess();
                        }
                        return false;
                    }
                })
                .into(imageView);
    }

    /**
     * 加载图片到Target
     * @param url      加载的地址
     * @param context  Context对象
     * @param view     图片展示的Target控件
     * @param listener 加载图片监听回调
     * @param <U>      泛型
     */
    public static <U> void LoadImageTarget(U url, Context context, final View view, final LoadImageListener listener) {
        SimpleTarget target = new SimpleTarget<Drawable>(100,100) {
            @Override
            public void onResourceReady(Drawable resource, GlideAnimation<? super Drawable> glideAnimation) {
                view.setBackground(resource);
            }
        };
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.loadfail)
                .listener(new RequestListener<U, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, U model, Target<GlideDrawable> target, boolean isFirstResource) {
                        if (listener != null) {
                            listener.onError(e, model.toString());
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, U model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (listener != null) {
                            listener.onSuccess();
                        }
                        return false;
                    }
                })
                .into(target);
    }
}
