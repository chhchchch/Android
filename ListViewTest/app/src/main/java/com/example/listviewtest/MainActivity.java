package com.example.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView l1,l2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l1 = findViewById(R.id.list1);
        l2 = findViewById(R.id.list2);
        String[] arr1 = new String[]{"孙悟空","猪八戒","牛魔王"};
        //将数组包装成ArrayAdapter
        ArrayAdapter adapter1 = new ArrayAdapter(this,R.layout.array_item,arr1);
        l1.setAdapter(adapter1);
        String[] arr2 = {"java","android","c++","python"};
        //设置Adapter.
        ArrayAdapter adapter2 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,arr2);
        l2.setAdapter(adapter2);
    }
}
