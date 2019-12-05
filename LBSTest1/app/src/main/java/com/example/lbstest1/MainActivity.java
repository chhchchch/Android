package com.example.lbstest1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MapView mapView;
    private AMap aMap;
    private LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);//实现求经纬度的操作.
        mapView = (MapView)findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        init();
        /*requestPermissions(new String[]{ android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION},1);*/
        requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},0x123);//活动中申请权限。
        /**
         * 加上android.;
         */
        Button bn = findViewById(R.id.loc);
        final TextView latTv = findViewById(R.id.lat);//纬度
        final TextView lngTv = findViewById(R.id.lng);//经度

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("info","111111");
                String lng = lngTv.getEditableText().toString().trim();
                String lat = latTv.getEditableText().toString().trim();
                if(lng.equals("")|| lat.equals("")){
                    Toast.makeText(MainActivity.this,"请输入有效的经度、纬度!",Toast.LENGTH_LONG).show();
                }
                else{
                    ((RadioButton)findViewById(R.id.manual)).setChecked(true);
                    double dLng = Double.parseDouble(lng);
                    double dLat = Double.parseDouble(lat);
                    LatLng pos = new LatLng(dLat,dLng);//封装经纬度
                    CameraUpdate cu = CameraUpdateFactory.changeLatLng(pos);//
                    aMap.moveCamera(cu);//更新地图显示区域

                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(pos);
                    markerOptions.title("疯狂369");//设置标题
                    markerOptions.snippet("里面个个都是人才，说话又好听");
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));//设置图标。
                    markerOptions.draggable(true);
                    Marker marker = aMap.addMarker(markerOptions);
                    marker.showInfoWindow();

                    /*MarkerOptions markerOptions1 = new MarkerOptions();
                    markerOptions1.position(new LatLng(dLat+0.001,dLng));
                    markerOptions1.title("疯狂小食堂");
                    markerOptions1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                    markerOptions1.draggable(true);*/
                }
            }
        });
    }

    /**
     * 单纯Gps定位
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        RadioButton rb = findViewById(R.id.gps);
        Log.d("info","333333");
        rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 300, 8f, new LocationListener()
                    {
                        @Override
                        public void onLocationChanged(Location location) {
                            Log.d("info","22222222");
                            updatePosition(location);
                        }
                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras)
                        { }
                        @Override
                        public void onProviderEnabled(String provider) {
                            Log.d("info","22222222");
                            updatePosition(locationManager.getLastKnownLocation(provider));
                        }

                        @Override
                        public void onProviderDisabled(String provider)
                        { }
                    });
                }
            }
        });
    }
    public void updatePosition(Location location){
        LatLng pos = new LatLng(location.getLatitude(),location.getLongitude());
        CameraUpdate cu = CameraUpdateFactory.changeLatLng(pos);
        aMap.moveCamera(cu);
        aMap.clear();
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(pos);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        markerOptions.title("i am here");
        markerOptions.draggable(true);
        aMap.addMarker(markerOptions);
    }
    public void init(){
        if(aMap == null){
            aMap = mapView.getMap();
            CameraUpdate cu  = CameraUpdateFactory.zoomTo(15f);//更改放大级别
            aMap.moveCamera(cu);
            CameraUpdate titleUpdata = CameraUpdateFactory.changeTilt(30f);//更改倾斜度
            aMap.moveCamera(titleUpdata);
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
