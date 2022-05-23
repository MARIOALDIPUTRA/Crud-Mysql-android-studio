package com.example.mobile_crud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class MyAdapter extends ArrayAdapter<RecyclerViewData> {
    private Context context;
    private List<RecyclerViewData> arrayListEmployee;
    TextView txt_nama, txt_id;
    public MyAdapter( Context context, List<RecyclerViewData> arrayListEmployee) {
        super(context, R.layout.list_view_data, arrayListEmployee);
        this.context = context;
        this.arrayListEmployee = arrayListEmployee;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_data, null, true);

        txt_id = view.findViewById(R.id.tv_id);
        txt_nama = view.findViewById(R.id.tv_nama);

        txt_id.setText(arrayListEmployee.get(position).getId());
        txt_nama.setText(arrayListEmployee.get(position).getNama());

        return view;
    }
}
