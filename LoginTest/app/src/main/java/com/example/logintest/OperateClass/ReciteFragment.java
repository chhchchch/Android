package com.example.logintest.OperateClass;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.logintest.EntityClass.Record;
import com.example.logintest.EntityClass.Word;
import com.example.logintest.R;
import com.example.logintest.ServiceClass.RecordDao;
import com.example.logintest.ServiceClass.WordDao;

import java.util.List;

import static com.example.logintest.Utils.Constants.USER_ID;


public class ReciteFragment extends Fragment {
    //按钮
    Button btn_known;
    Button btn_unknwn;
    Button btn_next;

    //显示单词信息
    TextView word_spell;
    TextView word_mean;
    //数据库操作对象
    WordDao wordDao;

    //记录当前数据。
    int count = 0;
    boolean isknow;
    Word w;
    RecordDao recordDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recite,container,false);
        btn_known = rootView.findViewById(R.id.known);
        btn_unknwn = rootView.findViewById(R.id.unknown);
        btn_next = rootView.findViewById(R.id.next);

        word_spell = rootView.findViewById(R.id.word_spell);
        word_mean = rootView.findViewById(R.id.word_mean);
        initView();


        return rootView;
    }
    public void initView(){

        //获取当前单词的记录
        recordDao = new RecordDao();
        Object object = recordDao.scalar("select max(wordId) from record where userId = ?",USER_ID);
        if(object !=  null){
            count = Integer.parseInt(object.toString()) + 1;
        }else {
            count = 1;
        }

        wordDao = new WordDao();
        String sql = "select * from vocabulary where id  = ?";
        w = wordDao.querySingle(sql, Word.class,count++);
        word_spell.setText(w.getWord());
        btn_next.setEnabled(false);



        btn_known.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isknow = true;
                word_mean.setText(w.getDesc());
                btn_known.setEnabled(false);
                btn_unknwn.setEnabled(false);
                btn_next.setEnabled(true);
                WriteRecord(isknow,USER_ID,w.getId(),w.getWord());

            }
        });

        btn_unknwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isknow = false;
                word_mean.setText(w.getDesc());
                btn_known.setEnabled(false);
                btn_unknwn.setEnabled(false);
                btn_next.setEnabled(true);
                WriteRecord(isknow,USER_ID,w.getId(),w.getWord());
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                w = wordDao.querySingle(sql,Word.class,count++);
                word_spell.setText(w.getWord());
                word_mean.setText("");
                btn_known.setEnabled(true);
                btn_unknwn.setEnabled(true);
                btn_next.setEnabled(false);
            }
        });

    }

    public void WriteRecord(boolean isknow,String userID,int wordId,String word){
        RecordDao recordDao = new RecordDao();
        String sql = "insert into record values (?,?,?,?);";
        recordDao.update(sql,userID,wordId,word,isknow);
    }



}
