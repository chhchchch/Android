package com.example.bottombartest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    First_Fragment first_fragment;
    Second_Fragment second_fragment;
    Third_Fragment third_fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomBar);
        first_fragment = new First_Fragment();
        second_fragment = new Second_Fragment();
        third_fragment = new Third_Fragment();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.first:
                        getSupportFragmentManager().beginTransaction().replace(R.id.Container,first_fragment).commit();
                        break;
                    case R.id.second:
                        //Toast.makeText(MainActivity.this,"second",Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.Container,second_fragment).commit();
                        break;
                    case R.id.third:
                        //Toast.makeText(MainActivity.this,"third",Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.Container,third_fragment).commit();
                        break;
                }
                return true;
            }
        });

    }
}
