package com.example.nbbutton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    NbButton nb;
    int width;
    int height;
    GradientDrawable backDrawable;
    ValueAnimator arcValueAnimator;
    Canvas canvas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nb = (NbButton) findViewById(R.id.n1);
/*        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        nb.measure(w, h);*/
/*        Log.d("info",String.valueOf(width));
        Log.d("info",String.valueOf(height));*/
        nb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                width = nb.getWidth();
                height= nb.getHeight();
                Log.d("info",String.valueOf(width));
                Log.d("info",String.valueOf(height));
                backDrawable = nb.backDrawable;
                ValueAnimator valueAnimator = ValueAnimator.ofInt(width,height);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int)animation.getAnimatedValue();
                        Log.d("info",String.valueOf(value));
                        int leftOffset = (width-value)/2;
                        int rightOffset = width - leftOffset;
                        backDrawable.setBounds(leftOffset,0,rightOffset,height);
                        //backDrawable.set
                    }
                });
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(backDrawable,"cornerRadius",120,height/2);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(1000);
                animatorSet.playTogether(valueAnimator,objectAnimator);
                animatorSet.start();

            }
        }
        );

    }

}

