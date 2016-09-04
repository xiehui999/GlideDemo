package com.example.xh.glidedemo.image;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by xiehui on 2016/9/2.
 */
public class GlideSetting {
    private int placeHolderResId;
    private int errorResId;
    private boolean crossFade=true;
    private int crossDuration;
    private boolean asGif;
    private boolean asBitmap;
    private boolean skipMemoryCache;
    private DiskCacheStrategy diskCacheStrategy = DiskCacheStrategy.ALL;
    private Priority prioriy = Priority.HIGH;
    private float thumbnail;
    private String thumbnailUrl;

    public  GlideSetting(Builder builder) {
        this.placeHolderResId= builder.placeHolderResId;
        this.errorResId= builder.errorResId;
        this.crossFade =builder.crossFade;
        this.crossDuration= builder.crossDuration;
        this.asGif= builder.asGif;
        this.asBitmap= builder.asBitmap;
        this.skipMemoryCache= builder.skipMemoryCache;
        this.diskCacheStrategy= builder.diskCacheStrategy;
        this.prioriy= builder.prioriy;
        this.thumbnail=builder.thumbnail;
        this.thumbnailUrl=builder.thumbnailUrl;
    }

    public int getPlaceHolderResId() {
        return placeHolderResId;
    }

    public void setPlaceHolderResId(int placeHolderResId) {
        this.placeHolderResId = placeHolderResId;
    }

    public int getErrorResId() {
        return errorResId;
    }

    public void setErrorResId(int errorResId) {
        this.errorResId = errorResId;
    }

    public boolean isCrossFade() {
        return crossFade;
    }

    public void setCrossFade(boolean crossFade) {
        this.crossFade = crossFade;
    }

    public int getCrossDuration() {
        return crossDuration;
    }

    public void setCrossDuration(int crossDuration) {
        this.crossDuration = crossDuration;
    }

    public boolean isAsGif() {
        return asGif;
    }

    public void setAsGif(boolean asGif) {
        this.asGif = asGif;
    }

    public boolean isAsBitmap() {
        return asBitmap;
    }

    public void setAsBitmap(boolean asBitmap) {
        this.asBitmap = asBitmap;
    }

    public boolean isSkipMemoryCache() {
        return skipMemoryCache;
    }

    public void setSkipMemoryCache(boolean skipMemoryCache) {
        this.skipMemoryCache = skipMemoryCache;
    }

    public DiskCacheStrategy getDiskCacheStrategy() {
        return diskCacheStrategy;
    }

    public void setDiskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
        this.diskCacheStrategy = diskCacheStrategy;
    }

    public Priority getPrioriy() {
        return prioriy;
    }

    public void setPrioriy(Priority prioriy) {
        this.prioriy = prioriy;
    }

    public float getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(float thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }


    public static class Builder {
        private int placeHolderResId;
        private int errorResId;
        private boolean crossFade=true;
        private int crossDuration;
        private int CropType;
        private boolean asGif;
        private boolean asBitmap;
        private boolean skipMemoryCache;
        private DiskCacheStrategy diskCacheStrategy = DiskCacheStrategy.ALL;
        private Priority prioriy = Priority.HIGH;
        private float thumbnail;
        private String thumbnailUrl;
        public Builder setErrorResId(int errorResId) {
            this.errorResId = errorResId;
            return this;
        }

        public Builder setCrossFade(boolean crossFade) {
            this.crossFade = crossFade;
            return this;
        }

        public Builder setPlaceHolderResId(int placeHolderResId) {
            this.placeHolderResId = placeHolderResId;
            return  this;
        }

        public Builder setCrossDuration(int crossDuration) {
            this.crossDuration = crossDuration;
            return this;
        }

        public Builder setCropType(int cropType) {
            CropType = cropType;
            return this;
        }


        public Builder setAsGif(boolean asGif) {
            this.asGif = asGif;
            return this;
        }


        public Builder setAsBitmap(boolean asBitmap) {
            this.asBitmap = asBitmap;
            return this;
        }

        public Builder setSkipMemoryCache(boolean skipMemoryCache) {
            this.skipMemoryCache = skipMemoryCache;
            return this;
        }


        public Builder setDiskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
            this.diskCacheStrategy = diskCacheStrategy;
            return this;
        }


        public Builder setPrioriy(Priority prioriy) {
            this.prioriy = prioriy;

            return this;
        }

        public Builder setThumbnail(float thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public Builder setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
            return this;
        }

        public GlideSetting build() {
            return new GlideSetting(this);
        }
    }
}
