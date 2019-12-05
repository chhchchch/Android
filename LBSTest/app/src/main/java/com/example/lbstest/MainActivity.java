package com.example.lbstest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

public class MainActivity extends AppCompatActivity {
    private MapView mapView;
    private AMap aMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = (MapView)findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        init();
        ToggleButton tb = (ToggleButton)findViewById(R.id.tb);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
                }
                else{
                    aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                }
            }
        });

    }
    private void init(){
        if(aMap==null){
            aMap = mapView.getMap();
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onPause(){
        super.onPause();
        mapView.onPause();
    }
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }
}
