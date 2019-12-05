package com.example.helloworld;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.StackAdapter;

public class TestStackAdapter extends StackAdapter<Integer> {
    public TestStackAdapter(Context context){
        super(context);
    }

    @Override
    protected CardStackView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scores_card_item,parent,false);  //通过LayoutInflater加载布局
        CardViewHolder holder = new CardViewHolder(view);
        System.out.println("onCreateView");
        return holder;

    }

    @Override
    public void bindView(Integer data, int position, CardStackView.ViewHolder holder) {

    }

}
