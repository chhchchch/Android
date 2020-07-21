package com.example.litepaltest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.litepaltest.LitePalSupport.Note;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyDataBase {
    public static long ID = 1;
    Context context;
    /*
     * 别的类实例化这个类的同时，创建数据库
     */

    public  MyDataBase(Context con){
        this.context=con;
    }
    /*
     * 返回可能要修改的数据
     */
    public static Note getTiandCon(long id){
       Note n = LitePal.find(Note.class,id);
       return n;

    }

    /*
     * 用来修改日记
     */
    public static void toUpdate(long id,String title,String content){
       Note newNote = new Note();
       newNote.setTitle(title);
       newNote.setContent(content);
       newNote.updateAll("id = ?",id+"");
    }

    /*
     * 用来增加新的日记
     */
    public static void toInsert(String title,String content){
        Note note = new Note();
        note.setId(ID++);
        note.setContent(content);
        note.setTitle(title);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        note.setTime(""+simpleDateFormat.format(date));
        note.save();
    }

    /*
     * 长按点击后选择删除日记
     */
    public static void toDelete(long id){
        LitePal.deleteAll(Note.class,"id = ?",id+"");
    }

    /**
     * 返回一个List，关于数据库Note的
     */
    public static List<Note> getArray()
    {
        return LitePal.findAll(Note.class);
    }
}