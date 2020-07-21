package com.example.mysqlconnecttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.txt);
        try{
            Connection connection = JDBCUtilsByDruid.getConnection();
            if(connection.equals(null) == false){
                textView.setText("连接成功");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
