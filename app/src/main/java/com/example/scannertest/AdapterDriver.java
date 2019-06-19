package com.example.scannertest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterDriver extends RecyclerView.Adapter<AdapterDriver.ViewHolder> {

    private List<Driver> listDriver;
    public AdapterDriver(List<Driver> listDriver){
        this.listDriver = listDriver;
    }
    @NonNull
    @Override
    public AdapterDriver.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_info_driver, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDriver.ViewHolder viewHolder, int i) {
        viewHolder.txtNombre.setText(listDriver.get(i).getName());
        viewHolder.txtLicencia.setText(listDriver.get(i).getLicense_number());
    }

    @Override
    public int getItemCount() {
        return listDriver.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtNombre, txtLicencia;

        public ViewHolder(View itemView)
        {
            super(itemView);

            txtNombre = itemView.findViewById(R.id.nombre_driver);
            txtLicencia = itemView.findViewById(R.id.noLicencia);
        }
    }
}
