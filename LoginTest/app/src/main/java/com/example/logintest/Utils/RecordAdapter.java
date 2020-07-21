package com.example.logintest.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.logintest.EntityClass.Record;
import com.example.logintest.EntityClass.Word;
import com.example.logintest.R;

import java.util.List;

public class RecordAdapter  extends BaseAdapter {
    private List<Record> recordList;
    private Context context;
    private LayoutInflater inflater;
    public RecordAdapter(List<Record> recordList,Context context){
        this.recordList = recordList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int position) {
        return recordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Record r = recordList.get(position);
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.record_item_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.record_word= (TextView)convertView.findViewById(R.id.record_word);
            viewHolder.t_or_f = (ImageView)convertView.findViewById(R.id.t_ot_f);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.record_word.setText(r.getWord());
        if(r.getKnow() == true){
            viewHolder.t_or_f.setBackgroundResource(R.drawable.record_true);
        }else{
            viewHolder.t_or_f.setBackgroundResource(R.drawable.record_false);
        }

        return convertView;
    }
    static class ViewHolder{
        TextView record_word;
        ImageView t_or_f;
    }
}
