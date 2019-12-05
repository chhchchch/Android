package com.example.campus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static java.lang.Double.NaN;

public class info_modify extends AppCompatActivity {

    EditText lan1,lng1;
    EditText view_info;
    EditText spot_name;
    Button add_spot;

    Spinner delete_spot;
    Button delete;

    Spinner Start,End;
    EditText length;
    Button add_way;

    Spinner Start1,End1;
    Button delete_way;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_modify);
        lan1 = findViewById(R.id.lan1);
        lng1 = findViewById(R.id.lng1);
        view_info = findViewById(R.id.view_info);
        spot_name = findViewById(R.id.spot_name);
        add_spot = findViewById(R.id.add_spot);

        delete_spot = findViewById(R.id.delete_spot);
        delete = findViewById(R.id.delete);

        Start = findViewById(R.id.Start);
        End = findViewById(R.id.End);
        length = findViewById(R.id.length);
        add_way = findViewById(R.id.add_way);

        Start1 = findViewById(R.id.Start1);
        End1 = findViewById(R.id.End1);
        delete_way = findViewById(R.id.delete_way);

        spinner_use();

        add_spot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x = lan1.getText().toString();
                String y = lng1.getText().toString();
                String info = view_info.getText().toString();
                String name = spot_name.getText().toString();
                if(x.equals("") || y.equals("") || info.equals("")){
                    Toast.makeText(info_modify.this,"请把信息填完整！！！",Toast.LENGTH_LONG).show();
                }
                else{
                    try {
                        String n[] = new String[Data.Nodes.length+1];
                        double spot[][] = new double[Data.Nodes.length+1][2];
                        String Info[] = new String[Data.info.length+1];
                        double MAP[][] = new double[Data_Graph.Nodes.length+1][Data_Graph.Nodes.length+1];
                        for(int i=0;i<Data.Nodes.length;i++){
                            n[i] = Data.Nodes[i];
                            spot[i][0] = Data_Graph.loc[i][0];
                            spot[i][1] = Data_Graph.loc[i][1];
                            Info[i]= Data.info[i];
                            for(int j =0;j<Data_Graph.Nodes.length;j++){
                                MAP[i][j] = Data_Graph.map[i][j];
                            }
                        }
                        n[Data.Nodes.length]=name;
                        spot[Data_Graph.Nodes.length][0]=Double.parseDouble(x);
                        spot[Data_Graph.Nodes.length][1]=Double.parseDouble(y);
                        Info[Data.info.length] = info;

                        for(int i = 0;i<Data_Graph.Nodes.length+1;i++){
                            MAP[Data_Graph.Nodes.length][i]=NaN;
                            MAP[i][Data_Graph.Nodes.length]=NaN;
                        }
                        Data.Nodes = n;

                        Data.info = Info;

                        Data_Graph.Nodes = n;

                        Data_Graph.loc = spot;

                        Data_Graph.map=MAP;
                        Toast.makeText(info_modify.this,"t添加完成",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(info_modify.this,Navigation.class);
                        startActivity(intent);
                    }catch(Exception ex){
                        Toast.makeText(info_modify.this,"请正确输入结点信息",Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = delete_spot.getSelectedItem().toString();
                Log.d("1123",name);
                String n[] = new String[Data.Nodes.length-1];//结点
                double spot[][] = new double[Data.Nodes.length-1][2];//路径
                String Info[] = new String[Data.info.length-1];//结点信息
                double map[][] = new double[Data_Graph.Nodes.length-1][Data_Graph.Nodes.length-1];
                int j=0;
                int d=0;
                for (int i=0;i<Data_Graph.Nodes.length;i++){
                    if(Data_Graph.Nodes[i].equals(name)){
                        d=i;
                    }
                    else{
                        n[j] = Data_Graph.Nodes[i];
                        spot[j][0] = Data_Graph.loc[i][0];
                        spot[j][1] = Data_Graph.loc[i][1];
                        Info[j] = Data.info[i];
                        j++;
                    }
                }
                int h;
                j=h=0;
                for(int i=0;i<Data_Graph.Nodes.length;i++)
                {
                    for(int k=0;k<Data_Graph.Nodes.length;k++){
                        if(i!=d&&k!=d){
                            map[j][h]=Data_Graph.map[i][k];
                            h++;
                        }
                    }
                    if(i!=d){
                        j++;

                    }h=0;

                }
                Data.Nodes = n;
                Data.info = Info;

                Data_Graph.Nodes = n;
                Data_Graph.loc = spot;
                Data_Graph.map=map;
                Toast.makeText(info_modify.this,"删除完成",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(info_modify.this,Navigation.class);
                startActivity(intent);
            }
        });
        add_way.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_start = Start.getSelectedItem().toString();
                String name_end = End.getSelectedItem().toString();
                String len = length.getText().toString();
                if(name_start.equals(name_end)){
                    Toast.makeText(info_modify.this,"起点与终点相同，请更换！！",Toast.LENGTH_LONG).show();
                }
                else{
                    try{
                        double l = Double.parseDouble(len);
                        if(l<0){
                            throw new Exception();
                        }
                        else{
                            for(int i=0;i<Data_Graph.Nodes.length;i++){
                                for(int j=0;j<Data_Graph.Nodes.length;j++){
                                    if(Data_Graph.Nodes[i].equals(name_start) && Data_Graph.Nodes[j].equals(name_end)){
                                        Data_Graph.map[i][j]=l;
                                        Data_Graph.map[j][i]=l;
                                        break;
                                    }
                                }
                            }
                        }
                        Toast.makeText(info_modify.this,"操作完成！！！",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(info_modify.this,Navigation.class);
                        startActivity(intent);
                    }catch (Exception ex){
                        Toast.makeText(info_modify.this,"请正确输入数据！！！",Toast.LENGTH_LONG).show();
                    }
                }


            }

        });

        delete_way.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_start = Start1.getSelectedItem().toString();
                String name_end = End1.getSelectedItem().toString();
                if(name_start.equals(name_end)){
                    Toast.makeText(info_modify.this,"起点与终点相同，请更换",Toast.LENGTH_LONG).show();
                }
                else{
                    try{

                         for(int i=0;i<Data_Graph.Nodes.length;i++){
                                for(int j=0;j<Data_Graph.Nodes.length;j++){
                                    if(Data_Graph.Nodes[i].equals(name_start) && Data_Graph.Nodes[j].equals(name_end)){
                                        Data_Graph.map[i][j]=NaN;
                                        Data_Graph.map[j][i]=NaN;
                                        break;
                                    }
                                }
                            }

                    }catch (Exception ex){
                        Toast.makeText(info_modify.this,"请正确输入数据！！",Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(info_modify.this,"操作完成！！！",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(info_modify.this,Navigation.class);
                    startActivity(intent);
                }


            }
        });

    }
    public void spinner_use(){
        ArrayAdapter<String> spinner = new ArrayAdapter<>(info_modify.this,R.layout.item_select,Data_Graph.Nodes);
        delete_spot.setAdapter(spinner);
        Start.setAdapter(spinner);
        End.setAdapter(spinner);
        Start1.setAdapter(spinner);
        End1.setAdapter(spinner);
    }
    @Override
    public void onResume(){
        super.onResume();
        spinner_use();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info_modify_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.spot_info:
                Intent intent =new Intent(info_modify.this,sopt_infoModify.class);
                startActivity(intent);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

}
