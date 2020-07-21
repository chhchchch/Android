package com.example.newcoolmenu.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class NorthernScrollView extends ScrollView {
    private NorthernScrollViewListener scrollViewListener = null;

    public void setScrollViewListener(NorthernScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }
    public NorthernScrollView(Context context) {
        super(context);
    }

    public NorthernScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NorthernScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }
    public interface NorthernScrollViewListener {
        void onScrollChanged(NorthernScrollView scrollView, int x, int y, int oldx, int oldy);
    }
}