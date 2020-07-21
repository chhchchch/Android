package com.example.newcoolmenu.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newcoolmenu.R;
import com.example.newcoolmenu.SearchFragment;
import com.labo.kaji.swipeawaydialog.SwipeAwayDialogFragment;
import com.moxun.tagcloudlib.view.TagsAdapter;

import java.util.List;

public class TagCloudAdapter extends TagsAdapter {
    public List<Garbage> garbageList;

    public TagCloudAdapter(List<Garbage> garbageList) {
        this.garbageList = garbageList;
    }

    @Override
    public int getCount() {
        return garbageList.size();
    }

    @Override
    public View getView(final Context context, final int position, ViewGroup parent) {
        TextView tag_text = (TextView) View.inflate(context, R.layout.item_tag, null);
        tag_text.setText(garbageList.get(position).getName());
        tag_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,garbageList.get(position).getCategory(),Toast.LENGTH_LONG).show();
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("类别");
                dialog.setMessage(garbageList.get(position).getCategory());
                dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
        return tag_text;
    }

    @Override
    public Object getItem(int position) {
        return garbageList.get(position);
    }

    @Override
    public int getPopularity(int position) {
        return 1;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {

    }
}
