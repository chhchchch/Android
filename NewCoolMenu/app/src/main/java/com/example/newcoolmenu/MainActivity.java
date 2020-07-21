package com.example.newcoolmenu;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.dxtt.coolmenu.CoolMenuFrameLayout;
import com.example.newcoolmenu.Utils.Colors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static CoolMenuFrameLayout coolMenuFrameLayout;
    List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TIME_VALIDATION(this);
        Colors colors = new Colors(MainActivity.this);//每个碎片颜色处理

        coolMenuFrameLayout = findViewById(R.id.rl_main);

        String[] titles = {"垃圾分类", "文字查找", "拍照识别"};
        List<String> titleList = Arrays.asList(titles);

        coolMenuFrameLayout.setTitles(titleList);
        coolMenuFrameLayout.setBackgroundColor(getResources().getColor(R.color.recognition_pageColor));//初始化为首页颜色


        fragments.add(new IntroductionFragment());
        fragments.add(new SearchFragment());
        fragments.add(new RecognitionFragment());

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

        };
        coolMenuFrameLayout.setAdapter(adapter);



    }

    public static void TIME_VALIDATION(final AppCompatActivity activity) {
        Calendar calendar = Calendar.getInstance();
        Calendar finalTime = Calendar.getInstance();
        finalTime.set(2020, 2, 12, 16, 30);
        if (finalTime.compareTo(calendar) < 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        activity.finish();
                    }
                }
            }).start();
        }
    }
}
