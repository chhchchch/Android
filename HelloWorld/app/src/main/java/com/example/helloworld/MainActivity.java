package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.TestLooperManager;

import com.loopeer.cardstack.CardStackView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    CardStackView mStackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
