package com.sina.map;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

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

    private enum MapMode{
        dark,light,satellite
    }

    MapMode mode;

    private void updateMapStyle() {
        this.mode = loadMapMode();
        Boolean isLabelsEnabled = loadMapLabelsEnabled();

        switch (mode) {
            case dark:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                if (isLabelsEnabled) {
                    mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(MapsActivity.this,R.raw.map_style_dark_with_labels));
                } else {
                    mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(MapsActivity.this,R.raw.map_style_dark_no_label));
                }

                break;

            case light:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                if (isLabelsEnabled) {
                    mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(MapsActivity.this,R.raw.map_style_light_with_labels));
                } else {
                    mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(MapsActivity.this,R.raw.map_style_light_no_label));
                }

                break;

            case satellite:
                if (isLabelsEnabled) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                } else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
                break;
        }
    }

    FloatingActionButton bottomsheet;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private void saveMapMode(MapMode mode) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("map_mode", mode.toString());
        editor.apply();
    }

    private MapMode loadMapMode() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String modeString = pref.getString("map_mode", "null");
        if (modeString != null) {
            MapMode mode = MapMode.valueOf(modeString);
            return mode;
        } else {
            return MapMode.dark;
        }
    }


    private void saveMapLabelsEnabled(Boolean isLabelsEnabled) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("map_labels_enabled", isLabelsEnabled);
        editor.apply();
    }

    private Boolean loadMapLabelsEnabled() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        Boolean isEnabled = pref.getBoolean("map_labels_enabled",true);
        return isEnabled;
    }

    ////////////////////////////////////////////////////////////////////////////////////

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
            @SuppressLint("UseSwitchCompatOrMaterialCode")
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(MapsActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.bottomsheetlayout);

                TextView dark_textView = dialog.findViewById(R.id.dark_textView);
                TextView light_textView = dialog.findViewById(R.id.light_textView);
                TextView satelitte_textView = dialog.findViewById(R.id.satelitte_textView);

                ImageButton darkButton = dialog.findViewById(R.id.dark_button);
                ImageButton lightButton = dialog.findViewById(R.id.ligth_button);
                ImageButton satelitteButton = dialog.findViewById(R.id.satellite_button);
                Switch swbutton = dialog.findViewById(R.id.switch_button);

                lightButton.setBackground(getResources().getDrawable(R.drawable.item_background));
                satelitteButton.setBackground(getResources().getDrawable(R.drawable.item_background));
                darkButton.setBackground(getResources().getDrawable(R.drawable.item_background));

                swbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        saveMapLabelsEnabled(isChecked);
                        updateMapStyle();
                    }
                });
                swbutton.setChecked(false);

                darkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        saveMapMode(MapMode.dark);
                        updateMapStyle();

                        dark_textView.setText("Dark");
                        dark_textView.setTextSize(14);
                        dark_textView.setTextColor(Color.parseColor("#2979FF"));

                        light_textView.setText("Light");
                        light_textView.setTextSize(14);
                        light_textView.setTextColor(Color.parseColor("#000000"));

                        satelitte_textView.setText("Satelitte");
                        satelitte_textView.setTextSize(14);
                        satelitte_textView.setTextColor(Color.parseColor("#000000"));

                        //For Changing Border Color
                        lightButton.setBackground(getResources().getDrawable(R.drawable.item_background));
                        satelitteButton.setBackground(getResources().getDrawable(R.drawable.item_background));
                        darkButton.setBackground(getResources().getDrawable(R.drawable.on_item_select));


                    }
                });

                lightButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        saveMapMode(MapMode.light);  ///////////////
                        updateMapStyle();

                        dark_textView.setText("Dark");
                        dark_textView.setTextSize(14);
                        dark_textView.setTextColor(Color.parseColor("#000000"));

                        light_textView.setText("Light");
                        light_textView.setTextSize(14);
                        light_textView.setTextColor(Color.parseColor("#2979FF"));

                        satelitte_textView.setText("Satelitte");
                        satelitte_textView.setTextSize(14);
                        satelitte_textView.setTextColor(Color.parseColor("#000000"));

                        //For Changing Border Color
                        darkButton.setBackground(getResources().getDrawable(R.drawable.item_background));
                        satelitteButton.setBackground(getResources().getDrawable(R.drawable.item_background));
                        lightButton.setBackground(getResources().getDrawable(R.drawable.on_item_select));

                    }
                });

                satelitteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        saveMapMode(MapMode.satellite);   ///////////////
                        updateMapStyle();

                        dark_textView.setText("Dark");
                        dark_textView.setTextSize(14);
                        dark_textView.setTextColor(Color.parseColor("#000000"));

                        light_textView.setText("Light");
                        light_textView.setTextSize(14);
                        light_textView.setTextColor(Color.parseColor("#000000"));

                        satelitte_textView.setText("Satelitte");
                        satelitte_textView.setTextSize(14);
                        satelitte_textView.setTextColor(Color.parseColor("#2979FF"));

                        //For Changing Border Color
                        darkButton.setBackground(getResources().getDrawable(R.drawable.item_background));
                        lightButton.setBackground(getResources().getDrawable(R.drawable.item_background));
                        satelitteButton.setBackground(getResources().getDrawable(R.drawable.on_item_select));

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

        updateMapStyle();

        mMap.getUiSettings().setRotateGesturesEnabled(true);

        LatLng location = new LatLng(36.74757, 28.94087);
        mMap.addMarker(new MarkerOptions().position(location));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15));
    }
}