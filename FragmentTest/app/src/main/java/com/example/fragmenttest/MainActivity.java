package com.example.fragmenttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

public class MainActivity extends FragmentActivity implements BookListFragment.Callbacks{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onItemSelected(int id){
        Bundle arguements = new Bundle();
        arguements.putInt(BookDetailFragment.ITEM_ID,id);
        //创建BookDetailFragment对象
        BookDetailFragment fragment = new BookDetailFragment();
        //向Fragment传入参数
        fragment.setArguments(arguements);
        //使用fragment替换book_detail_container容器当前显示的Fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.book_detail_container,fragment).commit();
    }
}
