package com.example.androidexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityAs5 extends AppCompatActivity {
    EditText ed;
    TextView txt6;
    Button btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as5);
        ed = findViewById(R.id.editText);
        txt6 = findViewById(R.id.textView6);
        btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = ed.getText().toString();
                if(data.equals("")){
                    Toast.makeText(ActivityAs5.this,"请输入信息",Toast.LENGTH_LONG).show();
                }
                else
                {
                    try {
                        double ele = Double.parseDouble(data);
                        double money=0;
                        if(ele<2160){
                            money = money+ele*0.52;
                        }
                        else if(ele>2160&&ele<=3360){
                            money = money + (ele - 2160)*0.57 + 2160*0.52;
                        }
                        else
                        {
                            money = money + 2160*0.52+ (3360 - 2160)*0.57 + (ele - 3360)*0.82;
                        }
                        txt6.setText(String.valueOf(money));
                    }catch (Exception ex){
                        Toast.makeText(ActivityAs5.this,"请正确输入信息！！！",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
