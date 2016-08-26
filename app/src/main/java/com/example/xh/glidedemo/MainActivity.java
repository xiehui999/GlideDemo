package com.example.xh.glidedemo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
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
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        glideLoadImage();
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
        Glide.with(context).load(resourceId).into(imageView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
