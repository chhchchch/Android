package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Person> personList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        //设置保持固定大小，这样可以优化性能。
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置滚动方向
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //为RecyclerView设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        initData();
       RecyclerView.Adapter adapter = new RecyclerView.Adapter<PersonViewHolder>()
       {
           @NonNull
           @Override
           public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.simple_item,null);
               return new PersonViewHolder(view,this);
           }

           @Override
           public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
               holder.nameTv.setText(personList.get(position).name);
               holder.descTv.setText(personList.get(position).desc);
               holder.headerIv.setImageResource(personList.get(position).imageId);
           }

           @Override
           public int getItemCount() {
               return personList.size();
           }
       };
       recyclerView.setAdapter(adapter);
    }
    private void initData() {
         String[] names = new String[]{"薇尔莉特","千反田爱瑠","黑雪姬","椎名真白","亚丝娜"};
         String[] desc = new String[]{"盘成了髻的柔顺头发上系着深红色的丝带，有缎带装饰的雪白布拉吉连衣裙包裹着纤细的身躯。丝制的裙摆随着步伐有节奏地晃动，胸口缀着的祖母绿胸针闪烁着光芒。",
                "外表清秀，一头披肩黑发，身材也相当好，个性认真坦率，记忆力过人，善于记忆他人姓名，但特征是与整体印象不相符的紫色大眼睛",
                "校内虚拟角色为“黑凤蝶”，与本人相像，有着纯白的肌肤与漆黑的瞳眸，穿着漆黑中镶上银色的长裙礼服，手握伪装成洋伞的一把剑 。",
                "白皙的肌肤，微微的凤眼看起来有些成熟，迈步走起来像西表山猫，根据三鹰仁的推断，因为腰身纤细，胸部看起来会比数字上大些。",
                "中分的栗子色长直发下，那张小小鹅蛋脸以及散发出炫目光芒的大大淡褐色瞳孔。小巧又直挺的鼻梁下方，樱花色嘴唇为她的美丽又添加了几分风采。"};
         int[] imageIds = new int[]{R.drawable.weierlite,R.drawable.qianfantian,R.drawable.heixueji,R.drawable.zhenbai,R.drawable.yasina};
         for(int i=0;i<names.length;i++){
             this.personList.add(new Person(names[i],desc[i],imageIds[i]));

         }
    }
    class PersonViewHolder extends RecyclerView.ViewHolder{
        View rootView;
        TextView nameTv;
        TextView descTv;
        ImageView headerIv;
        private RecyclerView.Adapter adapter;
        public PersonViewHolder(View itemView, final RecyclerView.Adapter adapter){
            super((itemView));
            this.nameTv = itemView.findViewById(R.id.name);
            this.descTv = itemView.findViewById(R.id.desc);
            this.headerIv = itemView.findViewById(R.id.header);
            this.rootView = itemView.findViewById(R.id.item_root);
            this.adapter = adapter;

            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = (int)(Math.random() * (personList.size()+1))%5;
                     Person person = new Person(personList.get(i).name,personList.get(i).desc,personList.get(i).imageId);
                     adapter.notifyItemInserted(i);
                     personList.add(i,person);
                     adapter.notifyItemChanged(i,adapter.getItemCount());
                }
            });

            rootView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = PersonViewHolder.this.getAdapterPosition();
                    adapter.notifyItemRemoved(position);
                    MainActivity.this.personList.remove(position);
                    adapter.notifyItemRangeChanged(position,adapter.getItemCount());
                    return false;
                }
            });
        }
    }
}

