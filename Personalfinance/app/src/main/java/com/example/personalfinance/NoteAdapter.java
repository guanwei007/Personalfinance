package com.example.personalfinance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NoteAdapter extends BaseAdapter {

    List<AllNoteBean> list;
    Context context;
    public NoteAdapter(List<AllNoteBean> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.activity_list,parent,false);
        }
        TextView t1,t2;
        t1 = convertView.findViewById(R.id.tv1);
        t2 = convertView.findViewById(R.id.tv2);
        t1.setText(list.get(position).get_id());
        t2.setText(list.get(position).getNote());
        return convertView;
    }
}
