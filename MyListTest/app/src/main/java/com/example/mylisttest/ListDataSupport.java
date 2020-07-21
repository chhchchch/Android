package com.example.mylisttest;

import org.litepal.crud.LitePalSupport;

import java.util.List;

public class ListDataSupport extends LitePalSupport {
    private long id;
    private String title,time;
    List<Bean> beanList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Bean> getContent() {
        return beanList;
    }

    public void setContent(List<Bean> content) {
        this.beanList = content;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
