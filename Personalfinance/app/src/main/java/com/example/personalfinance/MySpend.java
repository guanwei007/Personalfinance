package com.example.personalfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MySpend extends AppCompatActivity {

    List<AllBean> spendList;
    SQLiteDatabase db;
    ImageView exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myspend);

        spendList = new ArrayList<AllBean>();

        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        db = myOpenHelper.getWritableDatabase();

        Cursor cursor = db.query("addout",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String _id = cursor.getString(0);
            String spend = cursor.getString(1);
            String spendDate = cursor.getString(2);
            String spendType = cursor.getString(3);
            String spendPlace = cursor.getString(4);
            String spendTips = cursor.getString(5);

            AllBean allBean = new AllBean(_id,spend,spendDate,spendType,spendPlace,spendTips);
            spendList.add(allBean);
        }

        //设置list布局
        ListView listView ;
        listView= findViewById(R.id.allList);
        if (listView != null){
            listView.setAdapter(new MyAdapter(spendList,this));
        }

        //设置List中的触发事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                View view1 = listView.getChildAt(position);

                TextView t1,t2,t3,t4,t5,t6;
                t1 = view1.findViewById(R.id.tv1);
                t2 = view1.findViewById(R.id.tv2);
                t3 = view1.findViewById(R.id.tv3);
                t4 = view1.findViewById(R.id.tv4);
                t5 = view1.findViewById(R.id.tv5);
                t6 = view1.findViewById(R.id.tv6);

                //打包获取的数据
                Bundle bundle = new Bundle();
                bundle.putStringArray("spend",new String[]{t1.getText().toString(),
                        t2.getText().toString(),t3.getText().toString(),t4.getText().toString(),
                        t5.getText().toString(),t6.getText().toString()});

                Intent intent = new Intent(MySpend.this,SetSpend.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}