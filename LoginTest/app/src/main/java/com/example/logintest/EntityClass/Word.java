package com.example.logintest;

public class Word {
    public int id;
    public String word;
    public String description;
    public Word(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public int getId(){
        return id;

    }
    public String getWord(){
        return word;
    }
    public String getDesc(){
        return description;
    }
    public String toString(){
        return "id:" + id + " word:" + word +" desc:" + description;
    }
}
