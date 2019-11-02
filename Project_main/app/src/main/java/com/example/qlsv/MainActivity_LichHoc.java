package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity_LichHoc extends AppCompatActivity {
    TextView txtdate;
    final Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lich_hoc);
        txtdate = (TextView) findViewById(R.id.textdate);
        SetDate();
        txtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgay();
            }
        });
    }
    public void SetDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtdate.setText(simpleDateFormat.format(calendar.getTime()));
    }
    private void ChonNgay(){

        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SetDate();
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
    public void Next(View view){
        calendar.add(Calendar.DATE,+1);
        SetDate();
    }
    public void Pre(View view){
        calendar.add(Calendar.DATE,-1);
        SetDate();
    }

}
