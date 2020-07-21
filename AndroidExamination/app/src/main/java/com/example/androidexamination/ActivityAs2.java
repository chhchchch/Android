package com.example.androidexamination;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityAs2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as2);
        final TextView tv1 = findViewById(R.id.as2tv1);
        final EditText ed1 = findViewById(R.id.as2et1);
        final Button btn1 = findViewById(R.id.as2btn1);
        final String[] english = new String[]{"cow", "crack", "craft", "crane", "crash", "crawl", "crazy", "cream", "create", "creative", "creature", "credit", "creep", "crew", "crime", "criminal", "crisis", "critic", "critical", "criticism", "criticize", "crop", "cross", "crowd", "crown", "crude", "cruel", "crush", "crust", "cry"};
        final String[] chinese = new String[]{"n.母牛，奶牛；母兽", "n.裂缝，裂纹vi.爆裂", "n.工艺；手艺，行业", "n.起重机，摄影升降机", "vi.碰撞，坠落n.碰撞", "vi.爬，爬行", "a.疯狂的，荒唐的", "n.奶油，乳脂；奶油色", "vt.创造；引起，产生", "a.创造性的，创作的", "n.生物，动物，家畜", "n.信用贷款；信用", "vi.爬行；缓慢地行进", "n.全体船员", "n.罪，罪行；犯罪", "n.犯人，罪犯，刑事犯", "n.危机；存亡之际", "n.批评家，爱挑剔的人", "a.决定性的；批评的", "n.批评；批判；评论", "vt.批评；评论；非难", "n.农作物，庄稼；一熟", "vt.穿过；使交叉", "n.群；大众；一伙人", "n.王冠，冕；花冠", "a.简陋的；天然的", "a.残忍的，残酷的", "vt.压碎，碾碎；镇压", "n.面包皮；硬外皮", "vi.哭，哭泣；叫喊"};

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = ed1.getText().toString();
                boolean flag = true;
                for (int i = 0; i < english.length; i++) {
                    if (s.equals(english[i])) {
                        tv1.setText(chinese[i]);
                        flag = false;
                    }
                }
                if (flag) {
                    tv1.setText("没找到");
                }
            }
        });
    }
}
