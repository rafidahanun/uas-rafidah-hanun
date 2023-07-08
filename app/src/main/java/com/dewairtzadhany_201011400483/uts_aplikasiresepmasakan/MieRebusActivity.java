package com.dewairtzadhany_201011400483.uts_aplikasiresepmasakan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MieRebusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mie_rebus);
    }
    public void openCameraActivity(View view) {
        startActivity(new Intent(this, CameraActivity.class));
    }
    public void openMapsActivity(View view) {
        startActivity(new Intent(this, MapsActivity.class));
    }

}