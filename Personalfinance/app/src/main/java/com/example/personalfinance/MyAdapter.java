package com.example.personalfinance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    List<AllBean> list;
    Context context;
    public MyAdapter(List<AllBean> list, Context context){
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
        TextView t1,t2,t3,t4,t5,t6;
        t1 = convertView.findViewById(R.id.tv1);
        t2 = convertView.findViewById(R.id.tv2);
        t3 = convertView.findViewById(R.id.tv3);
        t4 = convertView.findViewById(R.id.tv4);
        t5 = convertView.findViewById(R.id.tv5);
        t6 = convertView.findViewById(R.id.tv6);

        t1.setText(list.get(position).get_id());
        t2.setText(list.get(position).getSpend());
        t3.setText(list.get(position).getSpendDate());
        t4.setText(list.get(position).getSpendType());
        t5.setText(list.get(position).getSpendPlace());
        t6.setText(list.get(position).getSpendTips());
        return convertView;
    }
}
