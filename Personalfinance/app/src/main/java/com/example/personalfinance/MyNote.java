package com.example.personalfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Ringtone;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyNote extends AppCompatActivity {

    List<AllNoteBean> noteList;
    SQLiteDatabase db;
    ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allnote);

        noteList = new ArrayList<AllNoteBean>();

        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        db = myOpenHelper.getWritableDatabase();

        Cursor cursor = db.query("note",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String _id = cursor.getString(0);
            String note = cursor.getString(1);

            AllNoteBean all = new AllNoteBean(_id,note);
            noteList.add(all);
        }

        //设置list布局
        ListView listView ;
        listView= findViewById(R.id.allList);
        if (listView != null){
            listView.setAdapter(new NoteAdapter(noteList,this));
        }

        //设置List中的触发事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                View view1 = listView.getChildAt(position);
                TextView t1,t2;
                t1 = view1.findViewById(R.id.tv1);
                t2 = view1.findViewById(R.id.tv2);

                //打包获取的数据
                Bundle bundle = new Bundle();
                bundle.putStringArray("note",new String[]{t1.getText().toString(),t2.getText().toString()});

                Intent intent = new Intent(MyNote.this,SetNote.class);
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