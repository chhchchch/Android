package com.example.newcoolmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.newcoolmenu.Utils.Garbage;
import com.example.newcoolmenu.Utils.TagCloudAdapter;
import com.moxun.tagcloudlib.view.TagCloudView;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    TagCloudView tagCloudView;
    Button refresh;
    static List<Garbage> list = new ArrayList<>();

    static {
        for (int i = 0; i <6; i++) {
            list.add(new Garbage("卫生纸", "其他垃圾"));
            list.add(new Garbage("蓄电池", "有害垃圾"));
            list.add(new Garbage("个人计算机", "可回收物"));
            list.add(new Garbage("香蕉皮", "厨余垃圾"));
            list.add(new Garbage("温度计", "有害垃圾"));
        }
    }

    static List<Garbage> listChanged = new ArrayList<>();
    static {
        for (int i = 0; i <6; i++) {
            listChanged.add(new Garbage("火柴", "其他垃圾"));
            listChanged.add(new Garbage("电池", "有害垃圾"));
            listChanged.add(new Garbage("水瓶", "可回收物"));
            listChanged.add(new Garbage("啦啦啦", "厨余垃圾"));
            listChanged.add(new Garbage("温度计", "有害垃圾"));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_search, container, false);
        tagCloudView = view.findViewById(R.id.tag);
        refresh = view.findViewById(R.id.refresh);
        init();
        TagCloudAdapter tagCloudAdapter = new TagCloudAdapter(list);
        tagCloudView.setAdapter(tagCloudAdapter);
        return view;
    }
    public void init(){
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TagCloudAdapter tagCloudAdapter = new TagCloudAdapter(listChanged);
                tagCloudView.setAdapter(tagCloudAdapter);
                MainActivity.coolMenuFrameLayout.SWITCH();
            }
        });
    }
}
