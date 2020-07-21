package com.example.logintest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.logintest.OperateClass.MineFragment;
import com.example.logintest.OperateClass.ReciteFragment;
import com.example.logintest.OperateClass.RecordFragment;
import com.example.logintest.OperateClass.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.logintest.Utils.ActivityCollectorUtil.addActivity;

public class OperationActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    SearchFragment searchFragment = new SearchFragment();
    ReciteFragment reciteFragment = new ReciteFragment();
    RecordFragment recordFragment = new RecordFragment();
    MineFragment mineFragment = new MineFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
        addActivity(this);
        initView();
        getSupportFragmentManager().beginTransaction().replace(R.id.Container,reciteFragment).commit();

    }

    public void initView(){
        bottomNavigationView = findViewById(R.id.bottomBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.first:
                        getSupportFragmentManager().beginTransaction().replace(R.id.Container,reciteFragment).commit();
                        break;
                    case R.id.second:

                        getSupportFragmentManager().beginTransaction().replace(R.id.Container,searchFragment ).commit();
                        break;
                    case R.id.third:
                        getSupportFragmentManager().beginTransaction().replace(R.id.Container, recordFragment).commit();
                        break;
                    case R.id.fouth:
                        getSupportFragmentManager().beginTransaction().replace(R.id.Container,mineFragment).commit();
                        break;
                        default:
                            break;
                }
                return true;
            }
        });
    }

}
