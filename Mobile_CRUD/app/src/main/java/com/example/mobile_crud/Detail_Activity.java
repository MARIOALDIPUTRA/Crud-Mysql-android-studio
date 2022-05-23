package com.example.mobile_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Detail_Activity extends AppCompatActivity {
    private TextView tvid,tvnama,tvnim,tvnotelp,tvemail;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvid = findViewById(R.id.tv_id);
        tvnama = findViewById(R.id.tv_nama);
        tvnim = findViewById(R.id.tv_nim);
        tvnotelp = findViewById(R.id.tv_no_telp);
        tvemail = findViewById(R.id.tv_email);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvid.setText("ID: "+MainActivity.employeeArrayList.get(position).getId());
        tvnama.setText("Nama: "+MainActivity.employeeArrayList.get(position).getNama());
        tvnim.setText("Nim: "+MainActivity.employeeArrayList.get(position).getNim());
        tvnotelp.setText("Jurusan: "+MainActivity.employeeArrayList.get(position).getNo_telp());
        tvemail.setText("Alamat: "+MainActivity.employeeArrayList.get(position).getEmail());


    }
}