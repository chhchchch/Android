//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.dxtt.coolmenu;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.PagerAdapter;

import com.dxtt.coolmenu.TranslateLayout.OnMenuClickListener;
import com.example.newcoolmenu.MainActivity;
import com.example.newcoolmenu.R;
import com.example.newcoolmenu.Utils.Colors;

import java.util.ArrayList;
import java.util.List;

public final class CoolMenuFrameLayout extends FrameLayout {
    private Context mContext;
    private int num = 3;
    private int[] ids;
    private Interpolator mInterpolator;
    private ObjectAnimator[] mOpenAnimators;
    private ObjectAnimator[] mChosenAnimators;
    private ObjectAnimator[] mMenuOpenAnimators;
    private PagerAdapter mAdapter;
    private CoolMenuFrameLayout.MenuObserver mObserver;
    private CoolMenuFrameLayout.MenuChooser mMenuChooser;
    private OnMenuClickListener menuListener;
    private boolean opening;
    private List<Object> objects;
    private int chosen;
    public static int chosenNum;

    public CoolMenuFrameLayout(Context context) {
        super(context);
        this.ids = new int[]{R.id.view0, R.id.view1, R.id.view2, R.id.view3, R.id.view4};
        this.mInterpolator = new AccelerateDecelerateInterpolator();
        this.mMenuChooser = new CoolMenuFrameLayout.MenuChooser();
        this.menuListener = new CoolMenuFrameLayout.MenuListener();
        this.opening = false;
        this.objects = new ArrayList();
        this.mContext = context;
        this.init();
    }

    public CoolMenuFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ids = new int[]{R.id.view0, R.id.view1, R.id.view2, R.id.view3, R.id.view4};
        this.mInterpolator = new AccelerateDecelerateInterpolator();
        this.mMenuChooser = new CoolMenuFrameLayout.MenuChooser();
        this.menuListener = new CoolMenuFrameLayout.MenuListener();
        this.opening = false;
        this.objects = new ArrayList();
        this.mContext = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CoolMenuFrameLayout);
        this.num = array.getInteger(R.styleable.CoolMenuFrameLayout_num, 3);
        array.recycle();
        this.init();
    }

    public CoolMenuFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.ids = new int[]{R.id.view0, R.id.view1, R.id.view2, R.id.view3, R.id.view4};
        this.mInterpolator = new AccelerateDecelerateInterpolator();
        this.mMenuChooser = new CoolMenuFrameLayout.MenuChooser();
        this.menuListener = new CoolMenuFrameLayout.MenuListener();
        this.opening = false;
        this.objects = new ArrayList();
        this.mContext = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CoolMenuFrameLayout);
        this.num = array.getInteger(R.styleable.CoolMenuFrameLayout_num, 3);
        array.recycle();
        this.init();
    }

    private void init() {
        if (!this.isInEditMode()) {
            this.setWillNotDraw(true);
            this.chosen = this.num - 1;

            for (int i = 0; i < this.num; ++i) {
                TranslateLayout frameLayout = new TranslateLayout(this.mContext);
                frameLayout.setId(this.ids[i]);
                frameLayout.setTag(i);
                frameLayout.setOnClickListener(this.mMenuChooser);
                frameLayout.setBackgroundColor(Color.parseColor("#00000000"));
                //frameLayout.setBackgroundColor(Color.rgb(i * 0, i * 0, i * 0));
                frameLayout.setOnMenuClickListener(this.menuListener);
                frameLayout.setTitle("Menu " + i);
                if (i == this.num - 1) {
                    frameLayout.setMenuAlpha(1.0F);
                }

                LayoutParams layoutParams = new LayoutParams(-1, -1);
                frameLayout.setLayoutParams(layoutParams);
                this.addView(frameLayout);
                this.mOpenAnimators = new ObjectAnimator[this.num];
                this.mChosenAnimators = new ObjectAnimator[this.num];
                this.mMenuOpenAnimators = new ObjectAnimator[this.num];
            }

            this.initAnim();
        }

    }

    private void initAnim() {
        for (int i = 0; i < this.getChildCount(); ++i) {
            View child = this.getChildAt(i);
            float transX = (float) ((double) (i + 1) * 0.06D);
            float transY = (float) ((double) (i + 1) * 0.12D);
            PropertyValuesHolder valuesHolderX = PropertyValuesHolder.ofFloat("XFraction", new float[]{0.0F, transX});
            PropertyValuesHolder valuesHolderY = PropertyValuesHolder.ofFloat("YFraction", new float[]{0.0F, transY});
            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(child, new PropertyValuesHolder[]{valuesHolderX, valuesHolderY});
            animator.setInterpolator(this.mInterpolator);
            animator.setDuration(300L);
            this.mOpenAnimators[i] = animator;
            PropertyValuesHolder valuesHolderXReverse = PropertyValuesHolder.ofFloat("XFraction", new float[]{transX, 1.0F});
            animator = ObjectAnimator.ofPropertyValuesHolder(child, new PropertyValuesHolder[]{valuesHolderXReverse});
            animator.setInterpolator(this.mInterpolator);
            animator.setDuration(300L);
            this.mChosenAnimators[i] = animator;
            PropertyValuesHolder valuesHolderAlpha = PropertyValuesHolder.ofFloat("menuAlpha", new float[]{1.0F, 0.0F});
            animator = ObjectAnimator.ofPropertyValuesHolder(child, new PropertyValuesHolder[]{valuesHolderAlpha});
            animator.setInterpolator(this.mInterpolator);
            animator.setDuration(300L);
            this.mMenuOpenAnimators[i] = animator;
        }

    }

    public void setAdapter(PagerAdapter adapter) {
        int count;
        if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mObserver);
            this.mAdapter.startUpdate(this);

            for (count = 0; count < this.num; ++count) {
                this.mAdapter.destroyItem((ViewGroup) this.getChildAt(count), count, this.objects.get(count));
            }

            this.mAdapter.finishUpdate(this);
        }

        this.mAdapter = adapter;
        if (this.mAdapter != null) {
            if (this.mObserver == null) {
                this.mObserver = new CoolMenuFrameLayout.MenuObserver();
            }

            this.mAdapter.registerDataSetObserver(this.mObserver);
            count = this.mAdapter.getCount();
            if (count != this.num) {
                throw new RuntimeException("适配器中碎片的数量必须等于布局中设置的数量");
            } else {
                for (int i = 0; i < count; ++i) {
                    Object object = this.mAdapter.instantiateItem((ViewGroup) this.getChildAt(i), i);
                    this.objects.add(object);
                }

                this.mAdapter.finishUpdate(this);
            }
        }
    }

    @UiThread
    public void setTitles(@NonNull List<String> titles) {
        for (int i = 0; i < this.num; ++i) {
            ((TranslateLayout) this.getChildAt(i)).setTitle((CharSequence) titles.get(i));
        }

    }

    @UiThread
    public void setTitleByIndex(@NonNull String title, int index) {
        if (index > this.num - 1) {
            throw new IndexOutOfBoundsException("目标已超出范围");
        } else {
            ((TranslateLayout) this.getChildAt(index)).setTitle(title);
        }
    }

    @UiThread
    public void setMenuIcon(@NonNull int resId) {
        for (int i = 0; i < this.num; ++i) {
            ((TranslateLayout) this.getChildAt(i)).setMenuIcon(resId);
        }

    }

    public void toggle() {
        if (this.opening) {
            this.close();
        } else {
            //Log.d("CHh","关闭中，我要打开了");

            MainActivity.coolMenuFrameLayout.setBackgroundColor(getResources().getColor(R.color.colorPageBackground));
            this.open();
        }

    }

    private void open() {
        this.opening = true;

        for (int i = 0; i < this.num; ++i) {
            if (i == this.chosen) {
                this.mMenuOpenAnimators[i].start();
                this.mOpenAnimators[i].start();
            } else if (i > this.chosen) {
                this.mChosenAnimators[i].reverse();
            } else {
                this.mOpenAnimators[i].start();
            }
        }

        this.chosen = this.num - 1;
    }

    public void SWITCH() {
        this.opening = true;
        for (int i = 0; i < this.num; ++i) {
            if (i == this.chosen) {
                this.mMenuOpenAnimators[i].start();
                this.mOpenAnimators[i].start();
            } else if (i > this.chosen) {
                this.mChosenAnimators[i].reverse();
            } else {
                this.mOpenAnimators[i].start();
            }
        }
        this.chosen = this.num - 1;


//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }


        if (CoolMenuFrameLayout.this.opening) {
            CoolMenuFrameLayout.this.chosen =0;
            chosenNum = 0;
//                Log.d("CHh",String.valueOf((Integer)v.getTag()));
            for (int i = 0; i < CoolMenuFrameLayout.this.num; ++i) {
                if (i <= CoolMenuFrameLayout.this.chosen) {
                    CoolMenuFrameLayout.this.mOpenAnimators[i].reverse();
                } else {
                    CoolMenuFrameLayout.this.mChosenAnimators[i].start();
                }
            }
            CoolMenuFrameLayout.this.mMenuOpenAnimators[CoolMenuFrameLayout.this.chosen].reverse();
        }

        CoolMenuFrameLayout.this.opening = false;
    }

    private void close() {
        this.opening = false;
        ObjectAnimator[] var1 = this.mOpenAnimators;
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            ObjectAnimator mAnimator = var1[var3];
            mAnimator.reverse();
        }

    }

    private void dataSetChanged() {
        if (this.opening) {
            this.close();
        }

        if (this.mAdapter != null) {
            this.mAdapter.startUpdate(this);

            int i;
            for (i = 0; i < this.num; ++i) {
                this.mAdapter.destroyItem((ViewGroup) this.getChildAt(i), i, this.objects.get(i));
            }

            for (i = 0; i < this.num; ++i) {
                this.mAdapter.instantiateItem((ViewGroup) this.getChildAt(i), i);
            }

            this.mAdapter.finishUpdate(this);
        }

    }

    boolean isOpening() {
        return this.opening;
    }

    private class MenuListener implements OnMenuClickListener {
        private MenuListener() {
        }

        public void onMenuClick() {
            CoolMenuFrameLayout.this.toggle();
        }
    }

    private class MenuChooser implements OnClickListener {
        private MenuChooser() {
        }

        public void onClick(View v) {
            if (CoolMenuFrameLayout.this.opening) {
                CoolMenuFrameLayout.this.chosen = (Integer) v.getTag();
                chosenNum = (Integer) v.getTag();
//                Log.d("CHh",String.valueOf((Integer)v.getTag()));
                for (int i = 0; i < CoolMenuFrameLayout.this.num; ++i) {
                    if (i <= CoolMenuFrameLayout.this.chosen) {
                        CoolMenuFrameLayout.this.mOpenAnimators[i].reverse();
                    } else {
                        CoolMenuFrameLayout.this.mChosenAnimators[i].start();
                    }
                }
                CoolMenuFrameLayout.this.mMenuOpenAnimators[CoolMenuFrameLayout.this.chosen].reverse();
            }

            CoolMenuFrameLayout.this.opening = false;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    MainActivity.coolMenuFrameLayout.setBackgroundColor(Colors.colors[chosenNum]);
                }
            }).start();

        }
    }

    private class MenuObserver extends DataSetObserver {
        private MenuObserver() {
        }

        public void onChanged() {
            CoolMenuFrameLayout.this.dataSetChanged();
        }

        public void onInvalidated() {
            CoolMenuFrameLayout.this.dataSetChanged();
        }
    }
}
