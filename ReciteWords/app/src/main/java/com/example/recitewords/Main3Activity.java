package com.example.recitewords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    TextView correct;
    TextView wrong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        correct = findViewById(R.id.correct);
        wrong = findViewById(R.id.wrong);
        Intent intent = getIntent();
        int c = intent.getIntExtra("correct",0);
        int w = intent.getIntExtra("wrong",0);
        correct.setText(String.valueOf(c));
        wrong.setText(String.valueOf(w));
    }
}
