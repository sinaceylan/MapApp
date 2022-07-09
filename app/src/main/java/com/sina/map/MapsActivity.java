package com.sina.map;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sina.map.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    FloatingActionButton bottomsheet;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(this);

        bottomsheet = findViewById(R.id.botttom_sheet);

        bottomsheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(MapsActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.bottomsheetlayout);

                Button darkButton = dialog.findViewById(R.id.dark_button);
                Button hybridButton = dialog.findViewById(R.id.hybrid_button);
                Button lightButton = dialog.findViewById(R.id.ligth_button);
                Button satelitteButton = dialog.findViewById(R.id.satellite_button);

                darkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(MapsActivity.this,R.raw.map_style_dark_no_label));
                        Toast.makeText(MapsActivity.this,"Dark is Clicked",Toast.LENGTH_SHORT).show();

                    }
                });

                lightButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(MapsActivity.this,R.raw.map_style_light_no_label));
                        Toast.makeText(MapsActivity.this,"Light is Clicked",Toast.LENGTH_SHORT).show();
                    }
                });

                hybridButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        Toast.makeText(MapsActivity.this,"Hybrid is Clicked",Toast.LENGTH_SHORT).show();

                    }
                });

                satelitteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        Toast.makeText(MapsActivity.this,"Satelitte is Clicked",Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);
            }
        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setRotateGesturesEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,12));
    }
}