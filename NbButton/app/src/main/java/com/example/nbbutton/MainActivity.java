package com.example.nbbutton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
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
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    NbButton nb;
    int width;
    int height;
    LinearLayout l;
    GradientDrawable backDrawable;
    ValueAnimator arcValueAnimator;
    ViewAnimationUtils animator;
    Canvas canvas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nb = (NbButton) findViewById(R.id.n1);
        //l.getBackground().setAlpha(0);
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
                l = (LinearLayout)findViewById(R.id.container);
                int xc=(nb.getLeft()+nb.getRight())/2;
                int yc=(nb.getTop()+nb.getBottom())/2;
                Log.d("snfo",String.valueOf(xc));
                Animator animator= ViewAnimationUtils.createCircularReveal(nb,xc,yc,0,1111);
                animator.setDuration(300);
                animator.start();
            }

        }
        );
        //l.getBackground().setAlpha(255);
    }

}

