package com.example.androidexamination;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityAs5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as5);


        ListView listView = findViewById(R.id.as5list1);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "博美");
        map.put("img", R.drawable.bm);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "斗牛梗");
        map.put("img", R.drawable.dng);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "哈士奇");
        map.put("img", R.drawable.hsq);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "吉娃娃");
        map.put("img", R.drawable.jww);
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("name", "秋田犬");
        map.put("img", R.drawable.qt);
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("name", "萨摩耶");
        map.put("img", R.drawable.smy);
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("name", "松狮");
        map.put("img", R.drawable.ss);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "藏獒");
        map.put("img", R.drawable.za);
        list.add(map);


        final SimpleAdapter simpleAdapter = new SimpleAdapter(this, list, R.layout.item, new String[]{"img", "name"}, new int[]{R.id.as5iv1, R.id.as5tv1});
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = view.findViewById(R.id.as5tv1);
                Toast.makeText(ActivityAs5.this, "我喜欢"+tv.getText().toString(), Toast.LENGTH_SHORT).show();

        }
    });

}
}
