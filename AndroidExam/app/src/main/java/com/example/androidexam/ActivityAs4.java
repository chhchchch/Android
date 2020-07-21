package com.example.androidexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityAs4 extends AppCompatActivity {
    TextView txt2,txt3;
    int y,m,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as4);
        txt3 = findViewById(R.id.textView3);
        Intent intent = getIntent();
        y = intent.getIntExtra("year",0);
        m = intent.getIntExtra("month",0)+1;
        d = intent.getIntExtra("day",0);
        txt3.setText(y+"年"+m+"月"+d+"日");
    }
}
