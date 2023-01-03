package com.example.finalproject_sp.activites;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject_sp.R;
import com.example.finalproject_sp.databinding.ActivityMapBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    ActivityMapBinding binding;
    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupMap();
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {

        double lat = Double.parseDouble(getIntent().getStringExtra("lat"));
        double longitude = Double.parseDouble(getIntent().getStringExtra("long"));
        Toast.makeText(this, ""+lat +","+longitude, Toast.LENGTH_SHORT).show();

        LatLng myLocation = new LatLng(lat, longitude);

        map.addMarker(new MarkerOptions().position(myLocation).title("MY location"));
        map.moveCamera(CameraUpdateFactory.newLatLng(myLocation));

        googleMap = map;

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {

                map.addMarker(new MarkerOptions().position(myLocation).title("MY location"));
                map.moveCamera(CameraUpdateFactory.newLatLng(myLocation));



            }
        });

    }

    private void setupMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);

        Objects.requireNonNull(mapFragment).getMapAsync(this);
    }


}