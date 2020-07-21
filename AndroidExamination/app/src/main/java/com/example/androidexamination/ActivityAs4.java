package com.example.androidexamination;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityAs4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as4);

        Intent intent = getIntent();
        TextView tv = findViewById(R.id.as4tv1);
        tv.setText(intent.getStringExtra("SX"));
    }
}
