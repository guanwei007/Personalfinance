package com.example.personalfinance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyOpenHelper extends SQLiteOpenHelper {

    static final String name = "personal_finance.db";

    public MyOpenHelper(@Nullable Context context) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //注册用户
        db.execSQL("create table user ("
                +"_id integer primary key autoincrement,"
                +"user varchar(10),"
                +"password varchar(10))");


        //新增支出
        db.execSQL("create table addout ("
                    +"_id integer primary key autoincrement,"
                    +"spend varchar(10),"
                    +"spendDate date,"
                    +"spendType varchar(10),"
                    +"spendPlace varchat(20),"
                    +"spendTips varchat(100))");

        //新增支出
        db.execSQL("create table addin ("
                +"_id integer primary key autoincrement,"
                +"income varchar(10),"
                +"incomeDate date,"
                +"incomeType varchar(10),"
                +"incomePlace varchat(20),"
                +"incomeTips varchat(100))");

        //收支标签
        db.execSQL("create table note ("
                +"_id integer primary key autoincrement,"
                +"note varchar(200))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
