package com.example.androidexamination;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class ActivityAs3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as3);

        Button btn1=findViewById(R.id.as3btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker dp1=findViewById(R.id.as3dp1);
                Intent intent = new Intent(ActivityAs3.this, ActivityAs4.class);
                intent.putExtra("SX",getYear(dp1.getYear()));
                startActivity(intent);
            }
        });
    }
    public static String getYear(int year) {
        int start = 1900;
        String[] years = new String[]{"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
        return years[(year - start) % years.length];
    }
}
