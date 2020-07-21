package com.example.mylisttest;

public class Bean {

    private String title;

    // 构造方法
    public Bean(String title) {
        super();
        this.title = title;
    }
    public Bean()
    {
        super();
        this.title = "";
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}