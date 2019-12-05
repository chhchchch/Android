package com.example.campus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class sopt_infoModify extends AppCompatActivity {
    Spinner spin_spot;
    EditText name_spot;
    EditText Lat;
    EditText Lng;
    EditText Info;
    Button modify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sopt_info_modify);
        spin_spot = (Spinner)findViewById(R.id.spin_spot);
        name_spot = (EditText)findViewById(R.id.name_spot);
        Lat = (EditText)findViewById(R.id.Lat);
        Lng = (EditText)findViewById(R.id.Lng);
        Info = (EditText)findViewById(R.id.Info);
        modify = (Button)findViewById(R.id.modify);
        ArrayAdapter<String> spinner = new ArrayAdapter<>(sopt_infoModify.this,R.layout.item_select,Data_Graph.Nodes);
        spin_spot.setAdapter(spinner);
        /*spin_spot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = spin_spot.getSelectedItem().toString();
                for(int i=0;i<Data_Graph.Nodes.length;i++){
                    if(Data_Graph.Nodes[i].equals(name)){
                        name_spot.setText(name);
                        Lat.setText(String.valueOf(Data_Graph.loc[i][0]));
                        Lng.setText(String.valueOf(Data_Graph.loc[i][1]));
                        Info.setText(Data.info[i]);
                    }
                }
            }
        });*/
        spin_spot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = spin_spot.getSelectedItem().toString();
                for(int i=0;i<Data_Graph.Nodes.length;i++){
                    if(Data_Graph.Nodes[i].equals(name)){
                        name_spot.setText(name);
                        Lat.setText(String.valueOf(Data_Graph.loc[i][0]));
                        Lng.setText(String.valueOf(Data_Graph.loc[i][1]));
                        Info.setText(Data.info[i]);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = name_spot.getText().toString();
                String LAT = Lat.getText().toString();
                String LNG = Lng.getText().toString();
                String info = Info.getText().toString();
                if(name.equals("")||LAT.equals("")||LNG.equals("")||info.equals("")){
                    Toast.makeText(sopt_infoModify.this,"请输入完整信息",Toast.LENGTH_LONG).show();
                }
                else{
                    try {
                        double x =Double.parseDouble(LAT);
                        double y =Double.parseDouble(LNG);
                        if(x<0||y<0){
                            Toast.makeText(sopt_infoModify.this,"经纬度非法，请从新输入",Toast.LENGTH_LONG).show();
                        }
                        else{
                            for(int i=0;i<Data_Graph.Nodes.length;i++){
                                if(name.equals(Data_Graph.Nodes[i])){
                                    Data_Graph.Nodes[i]= name;
                                    Data.Nodes[i]=name;
                                    Data_Graph.loc[i][0]=x;
                                    Data_Graph.loc[i][1]=y;
                                    Data.info[i]=info;
                                    break;
                                }
                            }
                            Toast.makeText(sopt_infoModify.this,"修改完成",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(sopt_infoModify.this,Navigation.class);
                            startActivity(intent);
                        }
                    }catch (Exception ex){
                        Toast.makeText(sopt_infoModify.this,"经纬度非法，请从新输入",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.spot_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_Info:
                Intent intent =new Intent(sopt_infoModify.this,info_modify.class);
                startActivity(intent);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onResume(){
        super.onResume();
        ArrayAdapter<String> spinner = new ArrayAdapter<>(sopt_infoModify.this,R.layout.item_select,Data_Graph.Nodes);
        spin_spot.setAdapter(spinner);
    }
}
