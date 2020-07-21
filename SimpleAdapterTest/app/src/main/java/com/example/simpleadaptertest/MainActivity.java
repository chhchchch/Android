package com.example.simpleadaptertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String[] names = new String[]{"薇尔莉特","千反田爱瑠","黑雪姬","椎名真白","亚丝娜"};
    private String[] desc = new String[]{"盘成了髻的柔顺头发上系着深红色的丝带，有缎带装饰的雪白布拉吉连衣裙包裹着纤细的身躯。丝制的裙摆随着步伐有节奏地晃动，胸口缀着的祖母绿胸针闪烁着光芒。",
    "外表清秀，一头披肩黑发，身材也相当好，个性认真坦率，记忆力过人，善于记忆他人姓名，但特征是与整体印象不相符的紫色大眼睛",
    "校内虚拟角色为“黑凤蝶”，与本人相像，有着纯白的肌肤与漆黑的瞳眸，穿着漆黑中镶上银色的长裙礼服，手握伪装成洋伞的一把剑 。",
    "白皙的肌肤，微微的凤眼看起来有些成熟，迈步走起来像西表山猫，根据三鹰仁的推断，因为腰身纤细，胸部看起来会比数字上大些。",
    "中分的栗子色长直发下，那张小小鹅蛋脸以及散发出炫目光芒的大大淡褐色瞳孔。小巧又直挺的鼻梁下方，樱花色嘴唇为她的美丽又添加了几分风采。"};
    private int[] imageIds = new int[]{R.drawable.weierlite,R.drawable.qianfantian,R.drawable.heixueji,R.drawable.zhenbai,R.drawable.yasina};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Map<String,Object>> listItems = new ArrayList<>();
        for (int i=0;i < names.length; i++){
            Map<String,Object> listItem = new HashMap<>();
            listItem.put("header",imageIds[i]);
            listItem.put("personName",names[i]);
            listItem.put("desc",desc[i]);
            listItems.add(listItem);
        }
        //创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.simple_item,new String[]{"personName","header","desc"},new int[]{R.id.name,R.id.header,R.id.desc});
        ListView list = findViewById(R.id.list);
        list.setAdapter(simpleAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,names[position]+"是我老婆",Toast.LENGTH_LONG).show();
            }
        });
    }

}
