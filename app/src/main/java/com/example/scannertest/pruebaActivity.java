package com.example.scannertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class pruebaActivity extends AppCompatActivity {

    TextView txtMarca, txtPlacas, txtNombre, txtLicencia;
    RecyclerView rv;
    AdapterDriver adapterDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);
        this.boot();
    }

    public void boot(){
        this.initializedComponents();
        this.getCar();
        this.getDriver();
    }

    public void initializedComponents(){
        txtMarca = findViewById(R.id.txtMarca);
        txtPlacas = findViewById(R.id.txtPlacas);
        txtNombre = findViewById(R.id.nombre_driver);
        txtLicencia = findViewById(R.id.noLicencia);
        rv = findViewById(R.id.rv);
    }

    public void getCar(){
        String[] t = { getIntent().getStringExtra("description"),  getIntent().getStringExtra("license_plate")};
        txtMarca.setText(t[0]);
        txtPlacas.setText(t[1]);
    }

    public void getDriver(){
        ArrayList<Driver> driverList = getIntent().getParcelableArrayListExtra("lista");
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapterDriver = new AdapterDriver(driverList);
        rv.setAdapter(adapterDriver);

        for (int i = 0; i < driverList.size(); i++){
            Toast.makeText(this, "Success: " + driverList.get(i).getName() + " " + driverList.get(i).getLicense_number(), Toast.LENGTH_SHORT).show();
        }


    }

}
