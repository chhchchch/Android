package com.example.animatortest;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final float BALL_SIZE = 50f;
    public static final float FULL_TIME = 1000f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout container = findViewById(R.id.container);
        container.addView(new MyAnimationView(this));
    }
   static class MyAnimationView extends View implements ValueAnimator.AnimatorUpdateListener {
        List<ShapeHolder> balls = new ArrayList<>();
        public MyAnimationView(Context context){
            super(context);
            setBackgroundColor(Color.WHITE);
        }
       @Override
       public void onAnimationUpdate(ValueAnimator animation) {
            this.invalidate();
       }
       @Override
       public boolean onTouchEvent(MotionEvent event){
            if(event.getAction() != MotionEvent.ACTION_DOWN && event.getAction() != MotionEvent.ACTION_MOVE && event.getAction() != MotionEvent.ACTION_POINTER_DOWN){
                return false;
            }
            ShapeHolder newBall = addBall(event.getX(),event.getY());
            float startY = newBall.getY();
            float endY = getHeight() - BALL_SIZE;
            float h =getHeight();
            float eventY = event.getY();
            int duration = (int)(FULL_TIME * ((h - eventY) / h));
            ObjectAnimator fallAnim = ObjectAnimator.ofFloat(newBall,"y",startY,endY);
            fallAnim.setDuration(duration);
            fallAnim.setInterpolator(new AccelerateInterpolator());
            fallAnim.addUpdateListener(this);
            ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(newBall,"alpha",1f,0f);
            fadeAnim.setDuration(250);
            fadeAnim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    //super.onAnimationEnd(animation);
                    balls.remove(((ObjectAnimator) animation).getTarget());

                }
            });
            fadeAnim.addUpdateListener(this);
           AnimatorSet animatorSet = new AnimatorSet();
           animatorSet.play(fallAnim).before(fadeAnim);
           animatorSet.start();
           return true;
       }
       private ShapeHolder addBall(float x,float y){
           OvalShape circle = new OvalShape();
           circle.resize(BALL_SIZE,BALL_SIZE);
           ShapeDrawable drawable = new ShapeDrawable(circle);
           ShapeHolder shapeHolder = new ShapeHolder(drawable);
           shapeHolder.setX(x - BALL_SIZE/2);
           shapeHolder.setY(y - BALL_SIZE/2);
           int red = (int)(Math.random() * 255);
           int green = (int)(Math.random() * 255);
           int blue = (int)(Math.random()*255);
           int color = -0x1000000 + red << 16 |(green << 8) |blue;
           Paint paint = drawable.getPaint();
           int darkColor = (-0x1000000 | (red / 2 << 16)| (green / 2 <<8)| (blue / 2));
           RadialGradient gradient = new RadialGradient(37.5f,12.5f,BALL_SIZE,color,darkColor, Shader.TileMode.CLAMP);
           paint.setShader(gradient);
           shapeHolder.setPaint(paint);
           balls.add(shapeHolder);
           return shapeHolder;
       }
       @Override
       public void onDraw(Canvas canvas){
            for (ShapeHolder shapeHolder : balls){
                canvas.save();
                canvas.translate(shapeHolder.getX(),shapeHolder.getY());
                shapeHolder.getShape().draw(canvas);
                canvas.restore();
            }
       }
   }
}
class ShapeHolder {
    private float x=0,y=0;
    private ShapeDrawable shape;
    private int color;
    private RadialGradient gradient;
    private float alpha =1f;
    private Paint paint;
    public ShapeHolder(ShapeDrawable s){
        shape = s;
    }
    public void setX(float x){
        this.x=x;
    }
    public void setY(float y){
        this.y=y;
    }
    public void setColor(int color){
        this.color=color;
    }
    public void setGradient(RadialGradient gradient){
        this.gradient=gradient;
    }
    public void setAlpha(float alpha){
        this.alpha=alpha;
    }
    public void setPaint(Paint paint){
        this.paint=paint;
    }
    public void setShape(ShapeDrawable shape){
        this.shape=shape;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getColor() {
        return color;
    }

    public float getAlpha() {
        return alpha;
    }

    public Paint getPaint() {
        return paint;
    }

    public RadialGradient getGradient() {
        return gradient;
    }

    public ShapeDrawable getShape() {
        return shape;
    }
}


