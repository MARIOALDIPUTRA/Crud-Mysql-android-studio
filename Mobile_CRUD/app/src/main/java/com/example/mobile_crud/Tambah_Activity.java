package com.example.mobile_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Tambah_Activity extends AppCompatActivity {
    private EditText edt_nama,edt_nim,edt_no_telp,edt_email;

    private Button btnTambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        edt_nama     = findViewById(R.id.ins_nama);
        edt_nim = findViewById(R.id.ins_nim);
        edt_no_telp  = findViewById(R.id.ins_no_telp);
        edt_email  = findViewById(R.id.ins_email);
        btnTambah = findViewById(R.id.btnTambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tambahData();
            }
        });
    }

    private void tambahData() {

        final String nama = edt_nama.getText().toString().trim();
        final String nim = edt_nim.getText().toString().trim();
        final String no_telp = edt_no_telp.getText().toString().trim();
        final String email = edt_email.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        if(nama.isEmpty()){
            Toast.makeText(this, "Masukan Nama ", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(nim.isEmpty()){
            Toast.makeText(this, "Masukan Nim", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(no_telp.isEmpty()){
            Toast.makeText(this, "Masukan No.Hp", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(email.isEmpty()){
            Toast.makeText(this, "Masukan Email", Toast.LENGTH_SHORT).show();
            return;
        }

        else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST,"http://10.0.2.2/api_crud_mobile/tambah.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("Data Input")){
                                Toast.makeText(Tambah_Activity.this, "Data Input", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                            else{
                                Toast.makeText(Tambah_Activity.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Tambah_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("nama",nama);
                    params.put("nim",nim);
                    params.put("no_telp",no_telp);
                    params.put("email",email);

                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(Tambah_Activity.this);
            requestQueue.add(request);



        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public void btn_add_activity(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}