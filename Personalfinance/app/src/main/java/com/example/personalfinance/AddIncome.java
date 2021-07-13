package com.example.personalfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class AddIncome extends AppCompatActivity {

    EditText income,incomeDate,incomePlace,incomeTips;
    Button save;
    Spinner type;
    SQLiteDatabase db;
    String selectedItem;
    ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addin);
        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        db = myOpenHelper.getWritableDatabase();

        //时间输入框弹出选择日期
        incomeDate = findViewById(R.id.incomeDate);
        incomeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(AddIncome.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(year, monthOfYear, dayOfMonth);
                        incomeDate.setText(DateFormat.format("yyyy-MM-dd", calendar));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        //spinner组件实现类型的选择
        type = findViewById(R.id.incomeType);

        String[] item = getResources().getStringArray(R.array.addinType);//建立数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,item);//建立Adapter并绑定数据源
        type.setAdapter(adapter);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedItem = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //数据向数据库的存入
        save = findViewById(R.id.saveAddin);
        income = findViewById(R.id.income);
        incomePlace = findViewById(R.id.incomePlace);
        incomeTips = findViewById(R.id.noteInput);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String et1,et2,et3,et4,et5;
                et1 = income.getText().toString();
                et2 = incomeDate.getText().toString();
                et3 = selectedItem;
                et4 = incomePlace.getText().toString();
                et5 = incomeTips.getText().toString();

                ContentValues contentValues = new ContentValues();
                contentValues.put("income",et1);
                contentValues.put("incomeDate",et2);
                contentValues.put("incomeType",et3);
                contentValues.put("incomePlace",et4);
                contentValues.put("incomeTips",et5);

                db.insert("addin",null,contentValues);

                Toast.makeText(getApplicationContext(),"保存成功:)",Toast.LENGTH_SHORT).show();
                finish();

            }
        });


        //退出
        exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}