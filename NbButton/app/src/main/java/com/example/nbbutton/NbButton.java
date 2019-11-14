package com.example.nbbutton;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import android.content.Context;
import android.util.AttributeSet;

@SuppressLint("AppCompatCustomView")
public class NbButton extends Button {
    public  boolean isFirst=true;
    GradientDrawable backDrawable;
    ValueAnimator arcValueAnimator;
    Paint paint;
    public NbButton(Context context) {
        super(context);
        init(context);
    }

    public NbButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NbButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context){
        backDrawable = new GradientDrawable();
        int colorDrawable = context.getColor(R.color.colorAccent);
        backDrawable.setColor(colorDrawable);
        backDrawable.setCornerRadius(120);
        setBackground(backDrawable);
        setText("Login");

    }
/*    public void drawLoad(Canvas canvas,int mWidth,int mHeight,int degree) {
        Paint loadPaint = new Paint();
        loadPaint.setColor(Color.WHITE);
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.rotate(degree);
        float r = mHeight / 3;
        RectF rectf = new RectF(-r, -r, r, r);
        canvas.drawArc(rectf, 0,360, false, loadPaint);
    }*/
    public void onDraw(Canvas canvas){
        if(!isFirst)
        {
            paint=new Paint();
            paint.setColor(getResources().getColor(R.color.colorPrimaryDark));
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
            paint.setTextSize(1);
            //Log.d("Info",String.valueOf(getAnimation()));
            RectF rectF=new RectF(getWidth()*5/11,getHeight()/7,getWidth()*6/11,getHeight()-getHeight()/7);
            canvas.drawArc(rectF,0,270,false,paint);
        }
        else
        {

        }

    }

}
