package com.example.xh.glidedemo.image;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.xh.glidedemo.R;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by xiehui on 2016/8/31.
 */
public class LoadImageAdapter extends BaseAdapter{
    private final LayoutInflater inflater;
    private GlideSetting glideSetting;
    private WeakReference<Context> context;
    private int width;
    private List<String> urls;
    public LoadImageAdapter(Context context, List<String> urls,int width) {
        this.context=new WeakReference<Context>(context);
        this.urls=urls;
        this.width=width;
        inflater = LayoutInflater.from(context);
        glideSetting=new GlideSetting.Builder().setAsBitmap(true)
                .setCrossDuration(400)
                .setPlaceHolderResId(R.mipmap.loading)
                .setErrorResId(R.mipmap.loadfail)
                .build();

    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Object getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.gridview_item,null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.grid_img);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        int pHight = parent.getHeight();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,width);
        viewHolder.imageView.setLayoutParams(params);
        //Glide.with(context).load(urls.get(position)).placeholder(R.drawable.loadsuccess).error(R.drawable.loadfail).into(viewHolder.imageView);
        //LoadImage.LoadImage(urls.get(position),context,viewHolder.imageView,glideSetting,null);
        LoadImage.LoadImageTarget(urls.get(position),context.get(),viewHolder.imageView,null);
        return convertView;
    }
    public  class ViewHolder{
        ImageView imageView;
    }
}
