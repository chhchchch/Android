package com.example.mylisttest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_select_all;
    Button btn_detele;
    TextView tv_add;
    ListView listview;
    ListAdapter adapter;
    List<Bean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }
    private void initView() {
        adapter = new ListAdapter(this);
        tv_add = (TextView) findViewById(R.id.tv_add);
        tv_add.setOnClickListener(this);
        btn_detele = (Button) findViewById(R.id.btn_detele);
        btn_detele.setOnClickListener(this);
        btn_select_all = (Button) findViewById(R.id.btn_select_all);
        btn_select_all.setOnClickListener(this);
        listview = (ListView) findViewById(R.id.listview);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            // 加入数据
            case R.id.tv_add:
                adapter.addData(new Bean());
            // 通知刷新适配器
            adapter.notifyDataSetChanged();
            break;
            case R.id.btn_select_all:
                // 全选——全不选
                Map<Integer, Boolean> isCheck = adapter.getMap();
                if (btn_select_all.getText().equals("全选")) {
                    adapter.initCheck(true);
                    // 通知刷新适配器
                    adapter.notifyDataSetChanged();
                    btn_select_all.setText("全不选");
                    btn_select_all.setTextColor(Color.YELLOW);
                } else if (btn_select_all.getText().equals("全不选")) {
                    adapter.initCheck(false);
                    // 通知刷新适配器
                    adapter.notifyDataSetChanged();
                    btn_select_all.setText("全选");
                    btn_select_all.setTextColor(Color.YELLOW);
                }
                break;
            // 删除数据
            case R.id.btn_detele:
                // 拿到全部数据
                Map<Integer, Boolean> isCheck_delete = adapter.getMap();
                // 获取到条目数量。map.size = list.size,所以
                int count = adapter.getCount();
                // 遍历
                for (int i = 0; i < count; i++) {
                    // 删除有两个map和list都要删除 ,计算方式
                    int position = i - (count - adapter.getCount());
                    // 推断状态 true为删除
                    if (isCheck_delete.get(i) != null && isCheck_delete.get(i)) {
                        // listview删除数据
                        isCheck_delete.remove(i);
                        adapter.removeData(position);
                    }
                }
                btn_select_all.setText("全选");
                btn_select_all.setTextColor(Color.WHITE);
                adapter.notifyDataSetChanged();
                break;
        }
    }
    private void initData(){          // 默认显示的数据

        list = new ArrayList<Bean>();
        list.add(new Bean());
        adapter = new ListAdapter(this);
        adapter.setData(list);
        listview.setAdapter(adapter);

    }
}
