package com.example.mobile_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class Update_Activity extends AppCompatActivity {
    private EditText edtId;
    private EditText edtNama;
    private EditText edtNim;
    private EditText edtNotelp;
    private EditText edtEmail;

    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edtId = findViewById(R.id.upd_id);
        edtNama = findViewById(R.id.upd_nama);
        edtNim = findViewById(R.id.upd_nim);
        edtNotelp= findViewById(R.id.upd_no_telp);
        edtEmail= findViewById(R.id.upd_email);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        edtId.setText(MainActivity.employeeArrayList.get(position).getId());
        edtNama.setText(MainActivity.employeeArrayList.get(position).getNama());
        edtNim.setText(MainActivity.employeeArrayList.get(position).getNim());
        edtNotelp.setText(MainActivity.employeeArrayList.get(position).getNo_telp());
        edtEmail.setText(MainActivity.employeeArrayList.get(position).getEmail());

    }
    public void btnUpdate(View view) {

        final String id = edtId.getText().toString();
        final String nama = edtNama.getText().toString();
        final String nim = edtNim.getText().toString();
        final String no_telp = edtNotelp.getText().toString();
        final String email = edtEmail.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating Data....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "http://10.0.2.2/api_crud_mobile/update.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Update_Activity.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Update_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();

                params.put("id",id);
                params.put("nama",nama);
                params.put("nim",nim);
                params.put("no_telp",no_telp);
                params.put("email",email);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Update_Activity.this);
        requestQueue.add(request);

    }

}