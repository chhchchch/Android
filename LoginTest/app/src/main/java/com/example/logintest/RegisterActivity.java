package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.logintest.ServiceClass.CustomerDao;

import static com.example.logintest.Utils.ActivityCollectorUtil.addActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText id;
    EditText name;
    RadioGroup sex;
    EditText paw;
    EditText phone;

    Button btn_res;

    String ID,NAME,SEX,PAW,PHONE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addActivity(this);
        initView();
    }
    public void initView(){
        id = findViewById(R.id.res_input_id);
        name = findViewById(R.id.res_input_name);
        sex = findViewById(R.id.res_input_sex);
        paw = findViewById(R.id.res_input_paw);
        phone = findViewById(R.id.res_input_phone);
        btn_res = findViewById(R.id.btn_register);

        ID = "";
        NAME = "";
        SEX = "男";
        PAW = "";
        PHONE = "";


        sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.res_input_man:
                        SEX = "男";
                        break;
                    case R.id.res_input_woman:
                        SEX = "女";
                        break;
                        default:
                            SEX="";
                }
            }
        });

        btn_res.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getInfo();
                switch (judgeInfo()){
                    case 0:
                        Toast.makeText(RegisterActivity.this,"信息不能为空",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(RegisterActivity.this,"电话号码格式错误",Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(RegisterActivity.this,"号码首位不能为0",Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(RegisterActivity.this,"用户ID重复",Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        CustomerDao customerDao = new CustomerDao();
                        String sql = "insert into customer values (?,?,?,?,?);";
                        customerDao.update(sql,ID,NAME,SEX,PHONE,PAW);
                        Toast.makeText(RegisterActivity.this,"请注册完后重新登陆",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(intent);
                        break;
                        default:
                            break;

                }

            }
        });
    }

    public void getInfo(){
        ID = id.getText().toString();
        NAME = name.getText().toString();
        PAW = paw.getText().toString();
        PHONE = phone.getText().toString();
    }
    public int judgeInfo(){
        if(ID.isEmpty() || NAME.isEmpty() || SEX.isEmpty() ||  PAW.isEmpty() || PHONE.isEmpty()){
            return 0;
        }
        else if (PHONE.length() != 11){
            return 1;
        }else if(PHONE.charAt(0) == '0'){
            return 2;
        }else{
            String sql = "select count(*) from customer where id = ?;";
            CustomerDao customerDao = new CustomerDao();
            Object object = customerDao.scalar(sql,ID);
            int n = Integer.parseInt(object.toString());
            if(n != 0 ){
                return 3;
            }
            else{
                return 4;
            }
        }
    }
}
