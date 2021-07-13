package com.example.personalfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView name;
    ImageView exit , addout ,addin ,note ,myspend ,myincome ,data ,set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //接受用户名数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        name =findViewById(R.id.userName);
        name.setText("用户名："+bundle.getString("name"));


        //退出图片
        exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //新增支出
        addout = findViewById(R.id.addout);
        addout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddSpend.class);
                startActivity(intent);
            }
        });

        //新增收入
        addin = findViewById(R.id.addin);
        addin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddIncome.class);
                startActivity(intent);
            }
        });

        //收支标签
        note = findViewById(R.id.note);
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNote.class);
                startActivity(intent);
            }
        });

        //我的支出
        myspend = findViewById(R.id.myspend);
        myspend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MySpend.class);
                startActivity(intent);
            }
        });

        //我的收入
        myincome = findViewById(R.id.myincome);
        myincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyIncome.class);
                startActivity(intent);
            }
        });

        //数据管理
        data = findViewById(R.id.data);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Data.class);
                startActivity(intent);
            }
        });

        //设置按钮
        set = findViewById(R.id.setting);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this,set);
                popupMenu.getMenuInflater().inflate(R.menu.setmenu,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int a  = item.getItemId();
                        if (a == R.id.changePw){

                            Bundle bundle1 = new Bundle();
                            bundle1.putString("name",bundle.getString("name"));
                            Intent intent = new Intent(MainActivity.this,ChangePw.class);
                            intent.putExtras(bundle1);
                            startActivity(intent);
                        }
                        return true;
                    }
                });
            }
        });
    }
}