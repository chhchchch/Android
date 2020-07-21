package com.example.fragmenttest;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import javax.security.auth.callback.Callback;

public class BookListFragment extends ListFragment {
    private Callbacks mCallbacks;
    interface Callbacks
    {
        void onItemSelected(int id);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //为该ListView设置Adapter
        setListAdapter(new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_activated_1,android.R.id.text1,BookManager.ITEMS));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(!(context instanceof Callbacks)){
            throw new IllegalStateException("BookListFragment所在的Activity必须实现Callbacks接口！");
        }
        mCallbacks = (Callbacks) context;

    }
    //当Fragment被删除时调用
    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }
    //当用户点击某列表项是调用此回调方法

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mCallbacks.onItemSelected(BookManager.ITEMS.get(position).id);

    }
    public void setActivateOnItemCilck(boolean activateOnItemCilck)
    {
        getListView().setChoiceMode(activateOnItemCilck ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_NONE);
    }
}
