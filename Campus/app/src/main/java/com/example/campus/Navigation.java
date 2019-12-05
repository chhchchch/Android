package com.example.campus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.NaN;

public class Navigation extends AppCompatActivity {
    private MapView mapView;
    private AMap aMap;
    private LocationManager locationManager;
    private Spinner sp_start;
    private Spinner sp_end;
    public Map map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);//实现求经纬度的操作.
        mapView = (MapView)findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        init();
        requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},0x123);//活动中申请权限。
        LatLng pos = new LatLng(38.886396,115.571228);//封装经纬度
        CameraUpdate cu = CameraUpdateFactory.changeLatLng(pos);//
        aMap.moveCamera(cu);//更新地图显示区域
        StartEnd();//设置起点和终点的下拉菜单栏
        /**
         * 查询设置
         */
        drawGraph();
        Button search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s,e;
                s=e=0;
                String Start = sp_start.getSelectedItem().toString();
                String End = sp_end.getSelectedItem().toString();
                Node []n = new Node[Data_Graph.Nodes.length];
                for (int i=0;i<Data_Graph.Nodes.length;i++){
                    n[i] = new Node(Data_Graph.Nodes[i],Data_Graph.loc[i][0],Data_Graph.loc[i][1]);
                    if(Data_Graph.Nodes[i]==Start){
                        s=i;
                    }
                    if(Data_Graph.Nodes[i]==End){
                        e=i;
                    }
                }
                map = new Map();
                map.Floyd();
                if(Double.isNaN(map.edge[s][e])==false){
                    Toast.makeText(Navigation.this,Start+"到"+End+"的距离为"+(map.edge[s][e])+"m",Toast.LENGTH_LONG).show();
                    aMap.clear();
                    maker_view();
                    drawGraph();//绘制所有路径
                    drawPath(map,s,e);//绘制最短路径
                }
                else
                {
                    Toast.makeText(Navigation.this,"此路不通，到不了。",Toast.LENGTH_LONG).show();
                    aMap.clear();
                    maker_view();
                    drawGraph();//绘制所有路径
                }

            }
        });
        /**
         * 设置完成
         */

        /**
         * 0先自行定位
         */
       /* markerOptions_me =new MarkerOptions();
        markerOptions_me.position(pos);
        markerOptions_me.title("我");//设置标题
        markerOptions_me.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));//设置图标。
        markerOptions_me.draggable(true);
        Marker marker = aMap.addMarker(markerOptions_me);
        marker.showInfoWindow();*/
        /**
         * 定位模块
         */

        /**
         * 显示预设位置
         */
        //ArrayList<MarkerOptions> optionList = new ArrayList<>();
       maker_view();
        //aMap.addMarkers(optionList,true);
        /**
         * 结束
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
                    Toast.makeText(Navigation.this,"请输入有效的经度、纬度!",Toast.LENGTH_LONG).show();
                }
                else{
                    ((RadioButton)findViewById(R.id.manual)).setChecked(true);
                    double dLng = Double.parseDouble(lng);
                    double dLat = Double.parseDouble(lat);
                    LatLng pos = new LatLng(dLat,dLng);//封装经纬度
                    CameraUpdate cu = CameraUpdateFactory.changeLatLng(pos);//
                    aMap.moveCamera(cu);//更新地图显示区域

                    MarkerOptions markerOptions = new MarkerOptions();
                    aMap.clear();
                    markerOptions.position(pos);
                    markerOptions.title("我");//设置标题
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));//设置图标。
                    markerOptions.draggable(true);
                    Marker marker = aMap.addMarker(markerOptions);
                    marker.showInfoWindow();
                    maker_view();
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
        markerOptions.title("我");
        markerOptions.draggable(true);
        aMap.addMarker(markerOptions).showInfoWindow();
        maker_view();
    }

    /**
     * Gps模块结束
     */

    /**
     * 初始化aMap
     */
    public void init(){
        if(aMap == null){
            aMap = mapView.getMap();
            CameraUpdate cu  = CameraUpdateFactory.zoomTo(15f);//更改放大级别
            aMap.moveCamera(cu);
            CameraUpdate titleUpdata = CameraUpdateFactory.changeTilt(30f);//更改倾斜度
            aMap.moveCamera(titleUpdata);
        }
    }
    /**
     * 结束
     */
    //画出图
    public void drawGraph(){
        Node []n = new Node[Data_Graph.Nodes.length];
        for (int i=0;i<Data_Graph.Nodes.length;i++){
            n[i] = new Node(Data_Graph.Nodes[i],Data_Graph.loc[i][0],Data_Graph.loc[i][1]);
        }
        map = new Map();
        map.Floyd();
        for (int i =0;i<Data_Graph.Nodes.length;i++){
            for (int j=0;j<Data_Graph.Nodes.length;j++){
                if(Double.isNaN(map.map[i][j])==false){

                    LatLng l1 =new LatLng(Data_Graph.loc[i][0],Data_Graph.loc[i][1]);
                    LatLng l2 =new LatLng(Data_Graph.loc[j][0],Data_Graph.loc[j][1]);
                    aMap.addPolyline(new PolylineOptions().
                            add(l1,l2).width(10).color(Color.argb(255, 1, 1, 1)));
                }
            }
        }
    }
    //画出最短路径
    public void drawPath(Map map,int start,int end){
        int i,j;
        i=start;
        j=end;
        map.Floyd();
        while(i!=end){
            Log.d("info",Data_Graph.Nodes[i]);
            LatLng l1 =new LatLng(Data_Graph.loc[i][0],Data_Graph.loc[i][1]);
            LatLng l2 =new LatLng(Data_Graph.loc[(int)map.p[i][j]][0],Data_Graph.loc[(int)map.p[i][j]][1]);
            aMap.addPolyline(new PolylineOptions().
                    add(l1,l2).width(10).color(Color.argb(255, 255, 1, 1)));
            i=(int)map.p[i][j];
        }
    }
    //创建下拉菜单。
    public void StartEnd(){
        sp_start = findViewById(R.id.start);
        sp_end = findViewById(R.id.end);
        sp_start.setPrompt("起点");
        sp_end.setPrompt("终点");
        ArrayAdapter<String> spinner_start = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,Data_Graph.Nodes);
        ArrayAdapter<String> spinner_end = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,Data_Graph.Nodes);
        sp_start.setAdapter(spinner_start);
        sp_end.setAdapter(spinner_end);
    }
    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume();
        map.Floyd();
       /* Map map = new Map(Data_Graph.Nodes);
        drawGraph();
        maker_view();*/
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
    //画出地标
    public void maker_view(){
        for (int i=0;i<Data_Graph.Nodes.length;i++){
            //optionList.add(new Maker_Map(Data_Graph.Nodes[i],Data_Graph.loc[i][1],Data_Graph.loc[i][0]).getMarkerOptions());
            //Log.d("lei",Data_Graph.Nodes[i]);
            aMap.addMarker(new Maker_Map(Data_Graph.Nodes[i],Data_Graph.loc[i][0],Data_Graph.loc[i][1]).getMarkerOptions()).showInfoWindow();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.information:
                Intent intent =new Intent(Navigation.this,view_information.class);
                startActivity(intent);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
