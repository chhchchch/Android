package com.example.campus;

public class Node {
    public String name;
    public String number;
    public String about;
    public double lat;//纬度
    public double lng;//经度
    public Node(String name,double lat,double lng){
        this.name=name;
        this.lat=lat;
        this.lng=lng;
    }
}
