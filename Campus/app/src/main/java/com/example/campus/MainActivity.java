package com.example.campus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button customer = (Button)findViewById(R.id.custom);
        Button admin = (Button)findViewById(R.id.admin);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String pass= password.getText().toString();
                if(name.equals("customer")){
                    if(pass.equals("123456")){
                        Intent intent = new Intent(MainActivity.this,Navigation.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
                }
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String pass= password.getText().toString();
                if(name.equals("admin")){
                    if(pass.equals("123456")){
                        Intent intent = new Intent(MainActivity.this,info_modify.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
