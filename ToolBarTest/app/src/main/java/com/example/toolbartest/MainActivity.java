package com.example.toolbartest;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.funtory.slideshowimageview.SlideshowImageView;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class MainActivity extends AppCompatActivity {
    Element element;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        element = new Element();
        element.setTitle("立华奏");
        element.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,element.getTitle()+"是我的",Toast.LENGTH_LONG).show();
            }
        });
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.angel_4)
                .setDescription("立华奏是动画作品《Angel Beats!》中的角色。\n在死后世界的学校中担任着学生会会长。" +
                        "被“死后世界战线”的人称为“天使”，不" +
                        "过自己否认这一说法。很少有表情变化，同时也非常寡言，所以很难得知她在想什么。" +
                        "喜欢吃麻婆豆腐（在食堂里因为其恐怖的辣味而无人问津）。" +
                        "前世接受了音无结弦的恩惠，但还未对结弦表达感谢，所以落入死后世界后，只为与恩人说一声谢谢。")
                .addItem(element)
                .addItem(new Element().setTitle("小奏"))
                .addGroup("Connect with us")
                .addEmail("chhchchch@163.com")
                .addWebsite("https://github.com/chhchchch")
                //.addFacebook("the.medy")
                //.addTwitter("medyo80")
                //.addYoutube("UCdPQtdWIsg7_pi4mrRu46vA")
                //.addPlayStore("com.ideashower.readitlater.pro")
                .addGitHub("chhchchch")
                //.addInstagram("medyo80")
                .create();
        setContentView(aboutPage);
    }
}
