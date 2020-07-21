package com.example.litepaltest.LitePalSupport;

import org.litepal.crud.LitePalSupport;

public class Note extends LitePalSupport {
    private long id;
    private String title,content,time;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}