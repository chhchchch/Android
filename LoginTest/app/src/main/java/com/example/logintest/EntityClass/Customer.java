package com.example.logintest;


public class Customer {
    String id;
    String username;
    String password;

    String sex;
    String phone;


    public Customer(){
        super();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSex() {
        return sex;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "id:"+id+" name: "+username+" password:"+password+" sex:"+sex+" phone:"+phone;
    }
}
