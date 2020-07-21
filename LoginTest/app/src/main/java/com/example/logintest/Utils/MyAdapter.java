package com.example.logintest.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.logintest.EntityClass.Word;
import com.example.logintest.R;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<Word> wordList;
    private Context context;
    private LayoutInflater inflater;
    public MyAdapter(List<Word> wordListt,Context context){
        this.wordList = wordListt;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return wordList.size();
    }

    @Override
    public Object getItem(int position) {
        return wordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Word w = wordList.get(position);
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.word_item_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView)convertView.findViewById(R.id.word_name);
            viewHolder.desc = (TextView)convertView.findViewById(R.id.word_desc);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.name.setText(w.getWord());
        viewHolder.desc.setText(w.getDescription());

        return convertView;
    }
    static class ViewHolder{
        TextView name;
        TextView desc;
    }
}