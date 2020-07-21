package com.example.logintest.OperateClass;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.logintest.EntityClass.Record;
import com.example.logintest.R;
import com.example.logintest.ServiceClass.RecordDao;
import com.example.logintest.Utils.RecordAdapter;

import java.util.List;

import static com.example.logintest.Utils.Constants.USER_ID;


public class RecordFragment extends Fragment {

    ListView listView;

    RecordDao recordDao;

    RecordAdapter recordAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_record,container,false);
        listView = (ListView) rootView.findViewById(R.id.record_list);
        recordDao = new RecordDao();

        String sql = "select * from record where userId = ?  order by wordId desc limit 1,20";
        List<Record> records = recordDao.queryMulti(sql,Record.class,USER_ID);

        recordAdapter = new RecordAdapter(records,getActivity());
        listView.setAdapter(recordAdapter);
        return rootView;
    }
}
