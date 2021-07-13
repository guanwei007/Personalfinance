package com.example.personalfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class SetIncome extends AppCompatActivity {

    EditText spend,spendDate,spendPlace,spendTips;
    Spinner spendType;
    Button save,dele;
    ImageView exit;
    String selectedItem;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setincome);
        //接受数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String[] spendArray = bundle.getStringArray("key_id");


        spend = findViewById(R.id.income);
        spendDate = findViewById(R.id.incomeDate);
        spendType = findViewById(R.id.incomeType);
        spendPlace = findViewById(R.id.incomePlace);
        spendTips = findViewById(R.id.noteInput);

        spend.setText(spendArray[1]);
        spendDate.setText(spendArray[2]);
        spendType.setPrompt(spendArray[3]);
        spendPlace.setText(spendArray[4]);
        spendTips.setText(spendArray[5]);

        //时间输入框弹出选择日期
        spendDate = findViewById(R.id.incomeDate);
        spendDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(SetIncome.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(year, monthOfYear, dayOfMonth);
                        spendDate.setText(DateFormat.format("yyyy-MM-dd", calendar));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        //设置类型标签
        String[] item = getResources().getStringArray(R.array.addinType);//建立数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,item);//建立Adapter并绑定数据源
        spendType.setAdapter(adapter);
        spendType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedItem = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //删除
        dele = findViewById(R.id.deleIncome);
        dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOpenHelper myOpenHelper = new MyOpenHelper(SetIncome.this);
                db = myOpenHelper.getWritableDatabase();
                db.delete("addin","_id="+spendArray[0],null);
                Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(SetIncome.this,MyIncome.class);
                startActivity(intent1);
                finish();
            }
        });


        //保存
        save = findViewById(R.id.saveAddin);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOpenHelper myOpenHelper = new MyOpenHelper(SetIncome.this);
                db = myOpenHelper.getWritableDatabase();
                String et1,et2,et3,et4,et5;
                et1 = spend.getText().toString();
                et2 = spendDate.getText().toString();
                et3 = selectedItem;
                et4 = spendPlace.getText().toString();
                et5 = spendTips.getText().toString();
                db = myOpenHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("income",et1);
                contentValues.put("incomeDate",et2);
                contentValues.put("incomeType",et3);
                contentValues.put("incomePlace",et4);
                contentValues.put("incomeTips",et5);
                db.update("addin",contentValues,"_id=?",new String[]{spendArray[0]});
                Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(SetIncome.this,MyIncome.class);
                startActivity(intent1);
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