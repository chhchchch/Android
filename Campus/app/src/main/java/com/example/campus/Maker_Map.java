package com.example.campus;

import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

public class Maker_Map {
    public MarkerOptions markerOptions;
    public Maker_Map(String title,double lat,double lng){
        markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(lat,lng));
        markerOptions.title(title);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        markerOptions.visible(true);
        markerOptions.draggable(true);
    }
    public MarkerOptions getMarkerOptions(){
        return markerOptions;
    }
}
