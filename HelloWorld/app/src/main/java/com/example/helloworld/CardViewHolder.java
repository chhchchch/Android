package com.example.helloworld;

import android.graphics.PorterDuff;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertController;
import androidx.core.content.ContextCompat;

import com.loopeer.cardstack.CardStackView;

import static java.security.AccessController.getContext;

public static class CardViewHolder extends CardStackView.ViewHolder
{
    View root;
    FrameLayout cardTitle;          //布局头部
    AlertController.RecycleListView scoreList;         //布局头部下方的RecyclerView
    TextView titleText;             //布局头部中的标题
    public CardViewHolder(View view)
    {
            /*
                在创建ViewHolder是 对控件进行绑定
            */
        super(view);
        root = view;
        cardTitle = (FrameLayout)view.findViewById(R.id.card_title);
        titleText = (TextView)view.findViewById(R.id.card_title_text);
        scoreList = (RecyclerView)view.findViewById(R.id.scores_list);
        System.out.println("CardViewHolder constructor");
    }

    public void onBind(Integer backgroundColorId,int position,List<List<LessonData>> dataList)
    {
            /*
                该方法是在bindView 调用时被调用的，因为可能有不同的布局，因而有不同的ViewHolder，将bindView实现的操作放在了ViewHolder中的onBind方法中，会使代码看来起更简洁，易懂。
            */
        cardTitle.getBackground().setColorFilter(ContextCompat.getColor(getContext(),backgroundColorId), PorterDuff.Mode.SRC_IN);//传入的int值，其实是一个颜色，在这里改变头部的颜色
        ScoresListAdapter adapter = new ScoresListAdapter(dataList.get(position));
        scoreList.setLayoutManager(new LinearLayoutManager(getContext()));
        scoreList.setAdapter(adapter);
        System.out.println("holder onBind");
    }

    @Override
    public void onItemExpand(boolean b) {

            /*
            该方法是在，其他Item被展开时，自动隐藏和显示的。
            */
        scoreList.setVisibility(b ? View.VISIBLE : View.GONE);
        System.out.println("holder onItemExpand");
    }
}
