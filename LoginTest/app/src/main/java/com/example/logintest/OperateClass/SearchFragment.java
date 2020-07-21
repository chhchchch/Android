package com.example.logintest.OperateClass;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.logintest.EntityClass.Word;
import com.example.logintest.R;
import com.example.logintest.ServiceClass.WordDao;
import com.example.logintest.Utils.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private SearchView searchView;
    ListView listView;
    MyAdapter myAdapter;
    WordDao dao ;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d("x1ao", getActivity().getDatabasePath(Constants.DATABASE_NAME).toString());
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search,container,false);
        dao = new WordDao();
        searchView = (SearchView) rootView.findViewById(R.id.view_search);
        // 默认展开
//        searchView.onActionViewExpanded();
        searchView.setIconifiedByDefault(false);
        searchView.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        listView = rootView.findViewById(R.id.garbage_list);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
//                if(textView.getText()=="") return true;

                try {
                    String sql = "select * from vocabulary where word like 'a%';";
                    List<Word> words = dao.queryMulti(sql,Word.class,s);
                    myAdapter =  new MyAdapter(words,getActivity());
                    listView.setAdapter(myAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.equals("")) return true;
                try {
                    String sql = "select * from vocabulary where word like ?;";
                    s = s + '%';
                    List<Word> words = dao.queryMulti(sql,Word.class,s);
                    myAdapter =  new MyAdapter(words,getActivity());
                    listView.setAdapter(myAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        });

        return rootView;
    }
}
