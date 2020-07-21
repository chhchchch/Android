package com.example.newcoolmenu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.newcoolmenu.Utils.Garbage;
import com.example.newcoolmenu.Utils.NorthernScrollView;
import com.example.newcoolmenu.Utils.TagCloudAdapter;
import com.moxun.tagcloudlib.view.TagCloudView;

import java.util.ArrayList;
import java.util.List;

public class IntroductionFragment extends Fragment {
    ImageButton searchButton;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_introduction, container, false);
        initView();
        return view;
    }
    private void initView() {
        Log.d("chh","initView");
        searchButton = view.findViewById(R.id.searches);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("chh","点击了");
            }
        });
        Log.d("chh","initViewEnd");
    }

}