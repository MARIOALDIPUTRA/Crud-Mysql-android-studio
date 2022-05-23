package com.example.mobile_crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText edt_nama, edt_id, edt_nim;
    private MyAdapter adapter;
    public static ArrayList<RecyclerViewData> employeeArrayList = new ArrayList<>();
    private String url = "http://10.0.2.2/api_crud_mobile/read.php";
    private RecyclerViewData employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.my_List_View);
        adapter = new MyAdapter(this, employeeArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"Detail Data", "Update Data", "Delete Data"};
                builder.setTitle(employeeArrayList.get(position).getNama());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i) {

                            case 0:

                                startActivity(new Intent(getApplicationContext(), Detail_Activity.class)
                                        .putExtra("position", position));

                                break;

                            case 1:
                                startActivity(new Intent(getApplicationContext(),Update_Activity.class)
                                        .putExtra("position",position));

                                break;

                            case 2:

                                hapusData(employeeArrayList.get(position).getId());

                                break;

                        }


                    }
                });

                builder.create().show();

            }
        });

        readData();

    }

    private void hapusData(final String id) {

        StringRequest request = new StringRequest(Request.Method.POST, "http://10.0.2.2/api_crud_mobile/hapus.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equalsIgnoreCase("Menghapus Data")) {
                            Toast.makeText(MainActivity.this, "Berhasil Dihapus", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Gagall", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void readData() {

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        employeeArrayList.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("tb_mobile");

                            if (sucess.equals("1")) {


                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id = object.getString("id");
                                    String nama = object.getString("nama");
                                    String nim = object.getString("nim");
                                    String no_telp = object.getString("no_telp");
                                    String email = object.getString("email");

                                    employee = new RecyclerViewData(id, nama, nim, no_telp, email);
                                    employeeArrayList.add(employee);
                                    adapter.notifyDataSetChanged();

                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        if (id == R.id.tambah){
            Intent i = new Intent(MainActivity.this,Tambah_Activity.class);
            startActivity(i);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void btn_add_activity(View view) {
        startActivity(new Intent(getApplicationContext(), Tambah_Activity.class));
    }
}
