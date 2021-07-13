package com.example.personalfinance;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    TextView tips,register;
    EditText user,password;
    ImageView login;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //调用数据库方法
        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        db = myOpenHelper.getWritableDatabase();

        //登录功能
        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //用户输入的数据
                String name = user.getText().toString();
                String pass = password.getText().toString();

                //判断不能使用null   只能使用""
                if(name.equals("")||pass.equals("")){
                    Toast.makeText(getApplicationContext(),"没有输入用户名或密码",Toast.LENGTH_SHORT).show();
                }


                else {

                    Cursor cursor = db.query("user", null,
                            null, null, null, null, null);

                    cursor.moveToFirst();

                    if (cursor.getCount()==0){
                        Toast.makeText(getApplicationContext(),"无此用户，请注册",Toast.LENGTH_SHORT).show();
                    }else {
                        while (1 == 1) {
                            if (cursor.getString(cursor.getColumnIndex("user")).equals(name)) {
                                if (cursor.getString(cursor.getColumnIndex("password")).equals(pass)) {

                                    //传输用户名数据
                                    Bundle bundle = new Bundle();
                                    bundle.putString("name", name);

                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(), "欢迎使用:)", Toast.LENGTH_SHORT).show();
                                    break;
                                } else {
                                    Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                            if (cursor.isLast()) {
                                Toast.makeText(getApplicationContext(), "无此用户，请注册", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            cursor.moveToNext();

                        }

                    }
                }
            }
        }
        );


        //注册
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });


        //使用说明
        tips = findViewById(R.id.tips);
        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Login.this)
                        .setIcon(R.drawable.logo)
                        .setTitle("本软件使用说明")
                        .setMessage("修改密码:选择\"系统设置”模块可以修改登录\n" +
                                "密码，项目运行时，默认没有密码。\n" +
                                "支出管理:选择“新增支出\"模块可以添加支出\n" +
                                "信息;选择“我的支出”模块可以查看、修改或\n" +
                                "删除支出信息。\n" +
                                "收入管理:选择“新增收入”模块可以添加收入\n" +
                                "信息;选择'我的收入”模块可以查看、修改或\n" +
                                "删除收入信息。\n" +
                                "便签管理:选择\"收支便签\"模块可以添加便签\n" +
                                "信息;选择\"数据管理”模块中的\"便签信息\"按\n" +
                                "钮可以查看、修改或删除便签信息。\n" +
                                "退出系统:选择\"退出\"模块可以退出《个人理\n" +
                                "财通》项目。\n" +
                                "祝您使用愉快  :)")
                        .setPositiveButton("了解", null)
                        .create()
                        .show();

            }
        });

    }
}