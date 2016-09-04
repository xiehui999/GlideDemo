package com.example.xh.glidedemo.image;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.xh.glidedemo.R;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 自定义加载图片控件
 * Created by xiehui on 2016/9/1.
 */
public class LoadImageLinearLayout extends LinearLayout {

    private List<String> urls;
    private Context context;
    private int imageWidth;
    private int width;
    private LayoutInflater inflater;
    private LayoutParams layoutParams;
    private AlertDialog alertDialog;
    private GlideSetting glideSetting;


    public LoadImageLinearLayout(Context context) {
        super(context);
    }

    /**
     *
     * @param context
     * @param urls  要加载的图片地址集合
     */
    public LoadImageLinearLayout(Context context, List<String> urls) {
        super(context);
        this.context=context;
        this.urls = urls;
        initParms();
        addGridView();
    }

    /**
     * 初始化参数
     */
    private void initParms() {
        width=this.getResources().getDisplayMetrics().widthPixels-32-40;
        imageWidth = width / 3;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutParams = new LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);


    }

    /**
     * 在线性布局中添加GridView
     */
    private void addGridView() {
        removeAllViews();
        View layout = inflater.inflate(R.layout.gridview, null);
        GridView gridView = (GridView) layout.findViewById(R.id.grid_view);
        gridView.setColumnWidth(imageWidth);
        gridView.setLayoutParams(layoutParams);
        gridView.setAdapter(new LoadImageAdapter(context,urls,imageWidth));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(context,urls.get(position),Toast.LENGTH_LONG).show();
                showImage(urls.get(position));
            }
        });
        addView(layout);
    }


    /**
     * 点击时Item实现图片放大功能
     * @param url  放大图片的url
     */
    private void showImage(String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflat = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View displayview = inflat.inflate(R.layout.imageview, null);
        final ImageView grid_img = (ImageView) displayview
                .findViewById(R.id.imageView);
        Button btn_image_back=(Button)displayview.findViewById(R.id.btn_image_back) ;
/*        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        grid_img.setLayoutParams(params);*/
        glideSetting=new GlideSetting.Builder().setErrorResId(R.mipmap.loadfail).setThumbnail(0.2f).build();
        LoadImage.LoadImage(url,new WeakReference<Context>(context).get(),grid_img,glideSetting,null);
//        LoadImage.LoadImageTarget(url,new WeakReference<Context>(context).get(),grid_img,null);
        //builder.setView(displayview);
        alertDialog=builder.create();
        alertDialog.show();
        alertDialog.setContentView(displayview);
        btn_image_back.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                alertDialog.dismiss();
                grid_img.setImageBitmap(null);
            }
        });
    }

}
