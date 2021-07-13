package com.example.personalfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePw extends AppCompatActivity {

    EditText oldPass,newPass;
    ImageView exit;
    Button change;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepw);

        exit = findViewById(R.id.back);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //接收用户名数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        //修改密码
        oldPass = findViewById(R.id.oldPw);
        newPass = findViewById(R.id.newPw);


        change = findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOpenHelper myOpenHelper = new MyOpenHelper(ChangePw.this);
                db = myOpenHelper.getWritableDatabase();
                String oldPw;
                oldPw = oldPass.getText().toString();
                String newPw;
                newPw = newPass.getText().toString();
                if(oldPw.equals("")||newPw.equals("")){
                    Toast.makeText(getApplicationContext(),"未输入！",Toast.LENGTH_SHORT).show();
                }


                else {

                    Cursor cursor = db.query("user", null,
                            null, null, null, null, null);

                    cursor.moveToFirst();

                    while (1==1){
                        if (cursor.getString(cursor.getColumnIndex("user")).equals(bundle.getString("name"))){
                            if(cursor.getString(cursor.getColumnIndex("password")).equals(oldPw)){
                                if (newPw.equals(oldPw)==false){
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("password",newPw);
                                    db.update("user",contentValues,"user=?",new String[]{bundle.getString("name")});
                                    Toast.makeText(getApplicationContext(), "更改成功,请重新登录:)", Toast.LENGTH_SHORT).show();
                                    finish();
                                    Intent intent1 = new Intent(ChangePw.this,Login.class);
                                    startActivity(intent1);
                                    break;
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "原密码和新密码相同", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "原密码错误", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }

                        cursor.moveToNext();

                    }

                }
            }
        });


    }
}