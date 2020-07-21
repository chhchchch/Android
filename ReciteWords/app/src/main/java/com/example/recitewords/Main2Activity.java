package com.example.recitewords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    Button yes;
    Button no;
    TextView words, ps;
    SQLiteDatabase db;
    String Words[] ;
    String Translate[];
    String Ps[];
    double Score[];
    int c;
    int True;
    int False;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        yes = findViewById(R.id.yes);//认识
        no = findViewById(R.id.no);//不认识
        words = findViewById(R.id.words);//单词显示
        ps = findViewById(R.id.PhoneticSymbol);//音标显示
        Words = new String[140];//单词英文
        Translate = new String[140];//中文
        Ps = new String[140];//音标
        Score = new double[140];//分数

        True = 0;
        False = 0;
        try {
             db = SQLiteDatabase.openOrCreateDatabase(getFilesDir().getAbsolutePath() + "/四级单词.db", null);
            String sql1 = "select * from vocabulary ";
            Cursor cursor = db.rawQuery(sql1, null);

            if (cursor.moveToFirst()) {         //移到第1项数据（若有数据才继续）
                //String str = "总共有  " + cursor.getCount() + "项数据\n";
                //str += "-------\n";
                int i=0;
                do {              //逐项读出数据，并串接成信息字符串
                    /*String str1 = "";
                    str1 += "序号:" + cursor.getString(0) + "\n";
                    str1 += "汉语:" + cursor.getString(1) + "\n";
                    str1 += "英文:" + cursor.getString(2) + "\n";
                    str1 += "音标:" + cursor.getString(3) + "\n";
                    str1 += "分数:" + cursor.getString(4) + "\n";
                    str1 += "------\n";*/

                    Words[i] =  cursor.getString(1);
                    Translate[i] =  cursor.getString(2);
                    Ps[i] =  cursor.getString(3);
                    Score[i] =  Double.parseDouble(cursor.getString(4));
                    //Log.d("chh",Words[i]);
                    i++;
                } while (cursor.moveToNext());   //有下一项就继续循环
                //Log.d("chh", str);
            }
        } catch (Exception e) {
            Log.d("chh", e.getMessage());
        }
        c=0;
        while(Score[c]>=5)
        {
            c++;
        }
        words.setText(Words[c]);
        ps.setText(Ps[c]);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                True++;
                Toast.makeText(Main2Activity.this,Translate[c],Toast.LENGTH_LONG).show();
                Score[5]++;
                if(Score[c]>=5)
                {
                    while(Score[c]>=5){
                        c++;
                    }
                }
                else{
                    c++;
                }
                words.setText(Words[c]);
                ps.setText(Ps[c]);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                False++;
                Toast.makeText(Main2Activity.this,Translate[c],Toast.LENGTH_LONG).show();
                Score[c]--;
                if(Score[c]>=5)
                {
                    while(Score[c]>=5){
                        c++;
                    }
                }
                else{
                    c++;
                }
                words.setText(Words[c]);
                ps.setText(Ps[c]);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main2,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.close:
                Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
                //ContentValues values = new ContentValues();
                //values.put("score",Score[c]);
                //db.update("vocabulary",values,"id = ?",new String[]{String.valueOf(c)});
               // db.close();
                intent.putExtra("correct",True);
                intent.putExtra("wrong", False);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
/*
package com.example.recitewords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(getFilesDir().getAbsolutePath() + "/四级单词.db", null);
            String sql1 = "select * from vocabulary ";
            Cursor cursor = db.rawQuery(sql1, null);


            if (cursor.moveToFirst()) {         //移到第1项数据（若有数据才继续）
                String str = "总共有  " + cursor.getCount() + "项数据\n";
                str += "-------\n";

                do {              //逐项读出数据，并串接成信息字符串
                    String str1 = "";
                    str1 += "序号:" + cursor.getString(0) + "\n";
                    str1 += "汉语:" + cursor.getString(1) + "\n";
                    str1 += "英文:" + cursor.getString(2) + "\n";
                    str1 += "音标:" + cursor.getString(3) + "\n";
                    str1 += "分数:" + cursor.getString(4) + "\n";
                    str1 += "------\n";
                    Log.d("chh",str1);
                } while (cursor.moveToNext());   //有下一项就继续循环
                Log.d("x1aolata", str);
            }

//            db.execSQL(sql1);
//            sql1 = " create table log(id integer primary key autoincrement, rightinteger, wrong integer, date date)";
//            db.execSQL(sql1);



        } catch (Exception e) {
            Log.d("x1aolata", e.getMessage());

        }
*/
/*
        Log.d("x1aolata",getFilesDir().getAbsolutePath() + "/words.db");
        try {
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(getFilesDir().getAbsolutePath() + "/words.db", null);
            String sql1 = "create table vocabulary(id integer primary key autoincrement, english text, chinese text, phonetic text, score integer)";
            db.execSQL(sql1);
            sql1 = " create table log(id integer primary key autoincrement, rightinteger, wrong integer, date date)";
            db.execSQL(sql1);

            InputStream fileInputStream = this.getAssets().open("四级单词.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = "";
            while ((str = bufferedReader.readLine()) != null) {
                Log.d("x1aolata", str);

                String english = str.substring(0, str.indexOf("/"));
                Log.d("x1aolataenglish", english);
                String chinese = str.substring(str.lastIndexOf("/") + 1);
                Log.d("x1aolatachinese", chinese);
                String phonetic = str.substring(str.indexOf("/"), str.lastIndexOf("/") + 1);
                Log.d("x1aolataphonetic", phonetic);
                sql1 = " insert into vocabulary (english, chinese, phonetic, score) values('" + english + "','" + chinese + "','" + phonetic + "',0)";
                Log.d("x1aolata", sql1);
                db.execSQL(sql1);
            }
            fileInputStream.close();
            db.close();


        } catch (Exception e) {
            Log.d("x1aolata", e.getMessage());

        }

        Log.d("x1aolata", "创建成功");

*//*


    }
}
*/



