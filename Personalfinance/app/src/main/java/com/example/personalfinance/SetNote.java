package com.example.personalfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SetNote extends AppCompatActivity {

    EditText note;
    ImageView exit;
    Button save,dele;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setnote);

        //退出标签
        exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //接收数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String[] noteArray = bundle.getStringArray("note");

        note = findViewById(R.id.noteInput);
        note.setText(noteArray[1]);


        //保存按钮
        save = findViewById(R.id.saveNote);
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MyOpenHelper myOpenHelper = new MyOpenHelper(SetNote.this);
                db = myOpenHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("note",note.getText().toString());
                db.update("note",contentValues,"_id=?",new String[]{noteArray[0]});
                Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        //删除按钮
        dele = findViewById(R.id.deleNote);
        dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOpenHelper myOpenHelper = new MyOpenHelper(SetNote.this);
                db = myOpenHelper.getWritableDatabase();
                db.delete("note","_id="+noteArray[0],null);
                Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(SetNote.this,MyNote.class);
                startActivity(intent1);
                finish();
            }
        });

    }
}