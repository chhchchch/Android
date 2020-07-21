//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.dxtt.coolmenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newcoolmenu.R;


@SuppressLint({"all"})
class TranslateLayout extends FrameLayout implements OnClickListener {
    private TranslateLayout.OnMenuClickListener mOnMenuClickListener;
    private ImageView mMenu;
    private TextView mTitle;
    private float mTitleTrans;
    private View view;

    public TranslateLayout(Context context) {
        super(context);
        this.init();
    }

    public TranslateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public TranslateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    private void init() {
        if (!this.isInEditMode()) {
            this.setWillNotDraw(true);
            inflate(this.getContext(), R.layout.layout_title, this);
            this.view = this.getChildAt(0);
            this.view.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
            this.mTitleTrans = this.getResources().getDimension(R.dimen.cl_title_trans);
            this.mMenu = (ImageView)this.view.findViewById(R.id.cl_menu);
            this.mTitle = (TextView)this.view.findViewById(R.id.cl_title);
            this.mMenu.setOnClickListener(this);
            this.setMenuAlpha(0.0F);
        }

    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return ((CoolMenuFrameLayout)this.getParent()).isOpening();
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (this.indexOfChild(this.view) != this.getChildCount() - 1) {
            this.bringChildToFront(this.view);
        }

        super.onLayout(changed, left, top, right, bottom);
    }

    public float getXFraction() {
        int width = Utils.getScreenWidth(this.getContext());
        return width == 0 ? 0.0F : this.getX() / (float)width;
    }

    public void setXFraction(float xFraction) {
        int width = Utils.getScreenWidth(this.getContext());
        this.setX(width > 0 ? xFraction * (float)width : 0.0F);
    }

    public float getYFraction() {
        int height = Utils.getScreenHeight(this.getContext());
        return height == 0 ? 0.0F : this.getY() / (float)height;
    }

    public void setYFraction(float yFraction) {
        int height = Utils.getScreenHeight(this.getContext());
        this.setY(height > 0 ? yFraction * (float)height : 0.0F);
    }

    public void setOnMenuClickListener(TranslateLayout.OnMenuClickListener mOnMenuClickListener) {
        this.mOnMenuClickListener = mOnMenuClickListener;
    }

    public void setTitle(CharSequence title) {
        this.mTitle.setText(title);
    }

    public void setMenuIcon(Drawable drawable) {
        this.mMenu.setImageDrawable(drawable);
    }

    public void setMenuIcon(int resId) {
        this.mMenu.setImageResource(resId);
    }

    public void setMenuAlpha(float fraction) {
        this.mMenu.setScaleX(fraction);
        this.mMenu.setScaleY(fraction);
        this.mTitle.setTranslationX((1.0F - fraction) * -this.mTitleTrans);
    }

    public void onClick(View v) {
        if (R.id.cl_menu == v.getId() && this.mOnMenuClickListener != null) {
            this.mOnMenuClickListener.onMenuClick();
        }

    }

    interface OnMenuClickListener {
        void onMenuClick();
    }
}
