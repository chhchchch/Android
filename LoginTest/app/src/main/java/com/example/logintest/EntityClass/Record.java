package com.example.logintest.EntityClass;

public class Record {
    public String userId;
    public int wordId;
    public String word;
    public boolean know;
    public Record(){

    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setKnow(boolean know) {
        this.know = know;
    }

    public int getWordId() {
        return wordId;
    }

    public String getUserId() {
        return userId;
    }

    public String getWord() {
        return word;
    }

    public boolean isKnow() {
        return know;
    }

    public boolean getKnow(){
        return know;
    }
}
