package com.example.xh.glidedemo;

import android.animation.ObjectAnimator;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.NotificationTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID =1 ;
    private ImageView imageView;
    private TextView textView;
    private Context context;
    private String[] urls = {"http://img2.3lian.com/2014/f6/173/d/51.jpg", "http://img2.3lian.com/2014/f6/173/d/52.jpg",
            "http://img2.3lian.com/2014/f6/173/d/53.jpg", "http://img2.3lian.com/2014/f6/173/d/54.jpg",
            "http://img2.3lian.com/2014/f6/173/d/55.jpg", "http://img2.3lian.com/2014/f6/173/d/56.jpg",
            "http://img16.3lian.com/gif2016/q21/43/81.jpg", "http://img16.3lian.com/gif2016/q21/43/84.jpg",
            "http://img16.3lian.com/gif2016/q21/43/87.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.context = this;
        imageView = (ImageView) findViewById(R.id.imageView);
        textView=(TextView)findViewById(R.id.textView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        glideLoadImage();
        //设置通知栏网络图标
        notificationTarget();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void glideLoadImage() {
        int  resourceId=R.mipmap.image;
        //Glide.with(context).load("http://img2.3lian.com/2014/f6/173/d/55.jpg").placeholder(R.mipmap.place).error(R.mipmap.error).into(imageView);

        //设置错误监听
         RequestListener<String,GlideDrawable> errorListener=new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {

                Log.e("onException",e.toString()+"  model:"+model+" isFirstResource: "+isFirstResource);
                imageView.setImageResource(R.mipmap.ic_launcher);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                Log.e("onResourceReady","isFromMemoryCache:"+isFromMemoryCache+"  model:"+model+" isFirstResource: "+isFirstResource);
                return false;
            }
        } ;
       // Glide.with(context).load("http://img2.3lian.com/2014/f6/173/d/51.jpg").error(R.mipmap.error).placeholder(R.mipmap.place).crossFade(3000).into(imageView);
        //Glide.with(context).load("http://116.255.134.172:9090/jqgj_server_client/mobilephotos/2016/8/11/18603718778_2016081110201470a7684a-0b4c-44db-8676-8bfa00359d19.jpg").dontAnimate().override(400,600).fitCenter().into(imageView);
       // Glide.with(context).load("http://img2.3lian.com/2014/f6/173/d/51.jpg").thumbnail(0.2f).centerCrop().animate(R.anim.anim).into(imageView);

        //java文件设置动画
        ViewPropertyAnimation.Animator animator=new ViewPropertyAnimation.Animator() {
            @Override
            public void animate(View view) {
              view.setAlpha(0f);
                ObjectAnimator fadeAnim = ObjectAnimator.ofFloat( view, "alpha", 0f, 1f );
                fadeAnim.setDuration( 2500 );
                fadeAnim.start();
            }
        };
        //Glide.with(context).load("http://img2.3lian.com/2014/f6/173/d/51.jpg").thumbnail(0.2f).centerCrop().animate(animator).into(imageView);
        //加载Gif文件
        // Glide.with(context).load("http://img1.3lian.com/2015/w4/17/d/64.gif").asBitmap().into(imageView);
        //transform()
        //Glide.with(context).load("http://img2.3lian.com/2014/f6/173/d/51.jpg").centerCrop().transform(new GlideRoundTransform(this,50),new GlideRotateTransform(this)).animate(animator).into(imageView);

/*        //缓存基础
        Glide.with(context)
                .load("http://img2.3lian.com/2014/f6/173/d/51.jpg")
                .skipMemoryCache(true)//跳过内存缓存
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .animate(animator)
                .into(imageView);*/


        //SimpleTarget
        SimpleTarget target = new SimpleTarget<Drawable>(100,100){
            @Override
            public void onResourceReady(Drawable resource, GlideAnimation<? super Drawable> glideAnimation) {
                textView.setBackground(resource);
            }
        };
/*       Glide.with(context)
                .load("http://img2.3lian.com/2014/f6/173/d/51.jpg")
                .skipMemoryCache(true)//跳过内存缓存
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .animate(animator)
                .into(target);*/

        //using() 的动态使用
        CustomImageSizeModel customImageSizeModel=new CustomImageSizeModelFutureStudio("http://img2.3lian.com/2014/f6/173/d/51.jpg");
        Glide.with(context)
                .using(new CustomImageSizeUrlLoader(this))
                .load(customImageSizeModel)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .animate(animator)
                .into(target);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * 设置通知栏网络图标
     */
    private void notificationTarget() {
        RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.notifition);
        remoteViews.setImageViewResource(R.id.notification_icon, R.mipmap.ic_launcher);
        remoteViews.setTextViewText(R.id.notification_text, "HeadLine");

        //build notifition
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Content Title")
                .setContentText("Content Text")
                .setContent(remoteViews)
                .setPriority(NotificationCompat.PRIORITY_MIN);
        final Notification notification=builder.build();
        if (Build.VERSION.SDK_INT>=16){
            notification.bigContentView=remoteViews;
        }
        NotificationManager notificationManager=(NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID,notification);

        NotificationTarget notificationTarget=new NotificationTarget(this,remoteViews,R.id.notification_icon,notification,NOTIFICATION_ID);
        Glide.with(this).load(urls[4]).asBitmap().placeholder(R.mipmap.ic_launcher).error(R.mipmap.place).listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                Log.e("TAG",e.toString());
                return true;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                Log.e("TAG","1111111111111111111");
                return false;
            }
        }) .dontAnimate().into(notificationTarget);

    }
    /**
     * 将图像转换为四个角有弧度的图像
     */
    public class GlideRoundTransform extends BitmapTransformation {
        private float radius = 0f;

        public GlideRoundTransform(Context context) {
            this(context, 100);
        }

        public GlideRoundTransform(Context context, int dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

        private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            Log.e("11aa", radius + "");
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName() + Math.round(radius);
        }
    }

    /**
     *将图像做旋转操作
     */
    public class GlideRotateTransform extends BitmapTransformation {
        private float rotateAngle = 0f;

        public GlideRotateTransform(Context context) {
            this(context, 90);
        }

        public GlideRotateTransform(Context context, float rotateAngle) {
            super(context);
            this.rotateAngle = rotateAngle;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            Matrix matrix=new Matrix();
            matrix.postRotate(rotateAngle);
            return Bitmap.createBitmap(toTransform,0,0,toTransform.getWidth(),toTransform.getHeight(),matrix,true);
        }
        @Override
        public String getId() {
            return getClass().getName() + rotateAngle;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
