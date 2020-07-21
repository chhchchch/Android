package com.example.logintest.OperateClass;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.logintest.EntityClass.Customer;
import com.example.logintest.EntityClass.Record;
import com.example.logintest.OperationActivity;
import com.example.logintest.PersonInfoActivity;
import com.example.logintest.R;
import com.example.logintest.ServiceClass.CustomerDao;
import com.example.logintest.ServiceClass.RecordDao;
import com.example.logintest.Utils.Constants;

import static com.example.logintest.Utils.ActivityCollectorUtil.finishAllActivity;
import static com.example.logintest.Utils.Constants.USER_ID;

public class MineFragment extends Fragment {

    TextView nickname;

    TextView stu_id;

    TextView stu_words;

    RecordDao recordDao;

    TextView person_info;

    Button cancel,logout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mine,container,false);
        nickname = rootView.findViewById(R.id.nickname);
        stu_id = rootView.findViewById(R.id.stu_id);
        stu_words = rootView.findViewById(R.id.stu_words);
        person_info = rootView.findViewById(R.id.person_info);
        cancel = rootView.findViewById(R.id.cancel);
        logout = rootView.findViewById(R.id.logout);

        recordDao = new RecordDao();
        String sql = "select max(wordId) from record where userId = ? ";


        Object o = recordDao.scalar(sql, USER_ID);
        if(o != null){
            stu_words.setText(o.toString());
        }else {
            stu_words.setText("0");
        }

        nickname.setText(Constants.USER.getUsername());
        stu_id.setText("StdID: "+ USER_ID);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordDao recordDao = new RecordDao();
                CustomerDao customerDao = new CustomerDao();

                recordDao.update("delete from record where userId = ?",USER_ID);
                customerDao.update("delete from customer where id = ?",USER_ID);
                finishAllActivity();

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAllActivity();
            }
        });
        return rootView;
    }
}
