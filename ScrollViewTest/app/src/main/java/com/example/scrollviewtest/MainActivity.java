package com.example.scrollviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements NorthernScrollView.NorthernScrollViewListener {

    private NorthernScrollView northernScrollView;
    private View btn1;

    int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实力化控件
        initView();

        //计算控件高度
        getHetght();
    }

    private void getHetght() {
        ViewTreeObserver vto = btn1.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                height = btn1.getHeight();
                northernScrollView.setScrollViewListener(MainActivity.this);
            }
        });
    }

    private void initView() {
        northernScrollView = (NorthernScrollView) findViewById(R.id.northernScrollView);
        btn1 =  findViewById(R.id.btn1);
    }

    @Override
    public void onScrollChanged(NorthernScrollView scrollView, int x, int y, int oldx, int oldy) {
//        if (y <= height) {
//            title.setVisibility(View.GONE);
//        } else {
//            title.setVisibility(View.VISIBLE);
//        }
    }
}
