package com.dewairtzadhany_201011400483.uts_aplikasiresepmasakan;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText searchEditText;
    private Button searchButton;
    private SupportMapFragment mapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.mapFragment, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = searchEditText.getText().toString();
                searchLocation(location);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    private void searchLocation(String location) {
        Geocoder geocoder = new Geocoder(this);
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocationName(location, 1);
            if (addressList != null && !addressList.isEmpty()) {
                Address address = addressList.get(0);
                double latitude = address.getLatitude();
                double longitude = address.getLongitude();

                LatLng latLng = new LatLng(latitude, longitude);

                // Clear existing markers
                mMap.clear();

                // Add a marker at the searched location
                mMap.addMarker(new MarkerOptions().position(latLng).title(location));

                // Move the camera to the searched location
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));
            } else {
                Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error searching for location", Toast.LENGTH_SHORT).show();
        }
    }
}
