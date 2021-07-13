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

public class                                            Register extends AppCompatActivity {

    ImageView back;
    EditText setUser,setPassword,confirmPassword;
    Button create;
    SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //注册功能
        setUser = findViewById(R.id.setUser);
        setPassword = findViewById(R.id.setPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        create = findViewById(R.id.create);

        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        db = myOpenHelper.getWritableDatabase();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //输入的数据
                String user = null;
                user = setUser.getText().toString();
                String password = null;
                password = setPassword.getText().toString();
                String confirmpw = null;
                confirmpw = confirmPassword.getText().toString();

                //判定用户名是否存在
                Cursor cursor =db.query("user", null,
                        null, null, null, null, null);
                cursor.moveToFirst();
                if(cursor.getCount()==0){
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("user", user);
                    contentValues.put("password", password);

                    db.insert("user", null, contentValues);

                    //注册成功提示
                    Toast.makeText(getApplicationContext(), "注册成功:)", Toast.LENGTH_SHORT).show();

                    finish();
                }
                else {
                    while (1 == 1) {
                        if (cursor.getString(cursor.getColumnIndex("user")).equals(user)) {
                            Toast.makeText(getApplicationContext(), "用户名存在", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        if (cursor.isLast()) {
                            //判断条件不能用!=   要用.equals
                            if (password.equals(confirmpw) == false) {
                                Toast.makeText(getApplicationContext(), "输入的密码和确认密码不同，请重新输入", Toast.LENGTH_SHORT).show();
                                break;
                            }

                            if (user.equals(null) || password.equals(null) || confirmpw.equals(null)) {
                                Toast.makeText(getApplicationContext(), "未输入或输入的信息不完全", Toast.LENGTH_SHORT).show();
                                break;
                            } else {
                                //向数据库存数据
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("user", user);
                                contentValues.put("password", password);

                                db.insert("user", null, contentValues);

                                //注册成功提示
                                Toast.makeText(getApplicationContext(), "注册成功:)", Toast.LENGTH_SHORT).show();

                                finish();
                                break;
                            }
                        }
                        cursor.moveToNext();

                    }
                }
            }
        });




        //返回图片
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}