package com.example.androidexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class ActivityAs3 extends AppCompatActivity {
    CalendarView cal;
    Button btn;
    int y;
    int m;
    int d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as3);
        cal = findViewById(R.id.calendarView);
        btn = findViewById(R.id.button2);
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                y = year;
                m = month;
                d = dayOfMonth;
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(ActivityAs3.this,ActivityAs4.class);
                intent.putExtra("year",y);
                intent.putExtra("month",m);
                intent.putExtra("day",d);
                startActivity(intent);
            }
        });
    }
}
