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

public class AddNote extends AppCompatActivity {

    EditText note;
    Button save;
    SQLiteDatabase db;
    ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        db = myOpenHelper.getWritableDatabase();

        note = findViewById(R.id.noteInput);
        save = findViewById(R.id.saveNote);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = note.getText().toString();

                ContentValues contentValues = new ContentValues();
                contentValues.put("note",input);

                db.insert("note",null,contentValues);
                Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();
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