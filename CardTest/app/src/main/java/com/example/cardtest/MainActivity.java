package com.example.cardtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.loopeer.cardstack.AllMoveDownAnimatorAdapter;
import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.UpDownAnimatorAdapter;
import com.loopeer.cardstack.UpDownStackAnimatorAdapter;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements CardStackView.ItemExpendListener{
    public static Integer[] TEST_DATAS = new Integer[]{
            R.color.color_1,
            R.color.color_2,
            R.color.color_3,
            R.color.color_4,
            R.color.color_5,
            R.color.color_6,
            R.color.color_7,
            R.color.color_8,
            R.color.color_9,
            R.color.color_10,
            R.color.color_11,
            R.color.color_12,
            R.color.color_13,
            R.color.color_14,
            R.color.color_15,
            R.color.color_16,
            R.color.color_17,
            R.color.color_18,
            R.color.color_19,
            R.color.color_20,
            R.color.color_21,
            R.color.color_22,
            R.color.color_23,
            R.color.color_24,
            R.color.color_25,
            R.color.color_26
    };
    private CardStackView mStackView;
    private LinearLayout mActionButtonContainer;
    private TestStackAdapter mTestStackAdapter;
    Button search;
    Spinner spinner;//景点下拉菜单
    Integer data[];//个数设置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("View information");
        spinner = (Spinner)findViewById(R.id.spin_nodes) ;
        search = (Button)findViewById(R.id.search_btn1);
        mStackView = (CardStackView) findViewById(R.id.stackview_main);
        mActionButtonContainer = (LinearLayout) findViewById(R.id.button_container);
        mStackView.setItemExpendListener(this);
        mTestStackAdapter = new TestStackAdapter(this);
        mStackView.setAdapter(mTestStackAdapter);
        /**
       * 设置卡片个数
        */
        data  = new Integer[Data.Nodes.length];
        for(int i=0;i<Data.Nodes.length;i++){
            data[i] = TEST_DATAS[i];
        }
        /**
         * 设置下拉菜单
         */
        ArrayAdapter<String> nodes  = new ArrayAdapter<String>(this,R.layout.item_select,Data.Nodes);
        spinner.setAdapter(nodes);
        spinner.setDropDownVerticalOffset(100);
        spinner.setDropDownHorizontalOffset(100);
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        mTestStackAdapter.updateData(Arrays.asList(data));
                    }
                }
                , 200
        );
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = spinner.getSelectedItem().toString();
                for (int i=0;i<Data.Nodes.length;i++){
                    if(Data.Nodes[i].equals(name)){
                        //name = mTestStackAdapter.getItem(i).toString();
                        //Log.d("haha",mTestStackAdapter.getItem(i).toString());
                        //Log.d("haha",name);
                        //mStackView.next();
                        //mStackView.performItemClick(mTestStackAdapter.getItem(i));
                        //mStackView.performItemClick();
                        mStackView.performItemClick(Data.viewHolder[i]);//模拟点击效果，成功了！！！！
                        /**
                         * 添加地点。
                         *  n[Data.Nodes.length]="home";
                         *                         Data.Nodes = n;
                         *                         ArrayAdapter<String> nodes  = new ArrayAdapter<String>(MainActivity.this,R.layout.item_select,Data.Nodes);
                         *                         spinner.setAdapter(nodes);
                         */

                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actions, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_all_down:
                mStackView.setAnimatorAdapter(new AllMoveDownAnimatorAdapter(mStackView));
                break;
            case R.id.menu_up_down:
                mStackView.setAnimatorAdapter(new UpDownAnimatorAdapter(mStackView));
                break;
            case R.id.menu_up_down_stack:
                mStackView.setAnimatorAdapter(new UpDownStackAnimatorAdapter(mStackView));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void onPreClick(View view) {
        mStackView.pre();
    }

    public void onNextClick(View view) {
        mStackView.next();
    }

    @Override
    public void onItemExpend(boolean expend) {
        mActionButtonContainer.setVisibility(expend ? View.VISIBLE : View.GONE);
    }
}
