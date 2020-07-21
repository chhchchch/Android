package com.example.androidexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class ActivityAs1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as1);
        String phone = "13888888888";
        Uri data = Uri.parse("smsto:"+phone);
        Intent intent = new Intent(Intent.ACTION_SEND,data);
        intent.putExtra("hello","world");
        startActivity(intent);
    }
}
