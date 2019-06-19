package com.example.scannertest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.RatingBar;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final int ZXING_CAMERA_PERMISSION = 1;
    private Class<?> mClss;

    RatingBar ratingBar;

    String name, license_number, license_plate, description;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setNumStars(5);
        ratingBar.setRating((float) 3.5);

        //rv = findViewById(R.id.rv);


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //launchActivity(ScannerTest.class);

                /*int totalNum = ratingBar.getNumStars();
                float ratedValue = ratingBar.getRating();
                Toast.makeText(MainActivity.this, ratedValue + "/ " + totalNum , Toast.LENGTH_SHORT).show();*/

                JsonObjectRequest test = new JsonObjectRequest(Request.Method.GET,
                        "https://api.myjson.com/bins/169fuh",
                        null,
                        new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONObject car = response.getJSONObject("car");
                            license_plate = car.getString("license_plate");
                            description = car.getString("description");

                            ArrayList<Driver> driverList = new ArrayList<>();

                            JSONArray res_drivers = response.getJSONArray("drivers");
                            JSONObject d;
                            for(int i = 0; i < res_drivers.length(); i++){
                                JSONObject driver = res_drivers.getJSONObject(i);
                                d = driver.getJSONObject("drivers");
                                id = d.getInt("id");
                                name = d.getString("name");
                                license_number = d.getString("license_number");
                                driverList.add(new Driver(id, name, license_number));
                            }

                            Intent intent = new Intent(MainActivity.this, pruebaActivity.class);
                            intent.putExtra("description", description);
                            intent.putExtra("license_plate", license_plate);
                            intent.putParcelableArrayListExtra("lista", driverList);
                            startActivity(intent);


                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                VolleyS.getInstance(getApplicationContext()).getQueue().add(test);
            }
        });

    }

    public void launchActivity(Class<?> clss) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            mClss = clss;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            Intent intent = new Intent(this, clss);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(mClss != null) {
                        Intent intent = new Intent(this, mClss);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(this, "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }
}
