package com.example.newcoolmenu.Utils;

import android.content.Context;
import android.util.Log;

import com.example.newcoolmenu.R;

public class Colors {
    public static int colors[];
    public Colors(Context context){
        colors = new int[]{context.getResources().getColor(R.color.introduction_pageColor),
                context.getResources().getColor(R.color.search_pageColor),
                context.getResources().getColor(R.color.recognition_pageColor)};
//        Log.d("CHh",String.valueOf(context.getResources().getColor(R.color.colorPrimaryDark)));
    }
}
