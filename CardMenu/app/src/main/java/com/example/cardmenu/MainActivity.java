package com.example.cardmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dxtt.coolmenu.CoolMenuFrameLayout;

public class MainActivity extends AppCompatActivity {
    CoolMenuFrameLayout coolMenuFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coolMenuFrameLayout = findViewById(R.id.menu_card);
    }

}
