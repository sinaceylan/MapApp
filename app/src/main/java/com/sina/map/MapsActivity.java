package com.sina.map;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sina.map.databinding.ActivityMapsBinding;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private enum MapMode{
        dark,light,satellite
    }

    ModeTexts ModeText = new ModeTexts();
    Dialog dialog;
    MapMode mode;

    private void updateMapStyle() {
        this.mode = loadMapMode();
        Boolean isLabelsEnabled = loadMapLabelsEnabled();
        Log.d("MODES",mode.toString());
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

    private void updateDialogSelection(){
        this.mode = loadMapMode();
        Boolean isLabelsEnabled = loadMapLabelsEnabled();

        TextView dark_textView = dialog.findViewById(R.id.dark_textView);
        TextView light_textView = dialog.findViewById(R.id.light_textView);
        TextView satelitte_textView = dialog.findViewById(R.id.satelitte_textView);

        ImageButton darkButton = dialog.findViewById(R.id.dark_button);
        ImageButton lightButton = dialog.findViewById(R.id.ligth_button);
        ImageButton satelitteButton = dialog.findViewById(R.id.satellite_button);
        Switch swbutton = dialog.findViewById(R.id.switch_button);

        swbutton.setChecked(isLabelsEnabled);

        switch (mode) {

            case dark:
                ModeText.darkButton_Attributes(dark_textView,light_textView,satelitte_textView);
                lightButton.setBackground(getResources().getDrawable(R.drawable.buttons_default_background));
                satelitteButton.setBackground(getResources().getDrawable(R.drawable.buttons_default_background));
                darkButton.setBackground(getResources().getDrawable(R.drawable.selected_mode_background));
                break;

            case light:
                ModeText.lightButton_Attributes(dark_textView,light_textView,satelitte_textView);
                darkButton.setBackground(getResources().getDrawable(R.drawable.buttons_default_background));
                satelitteButton.setBackground(getResources().getDrawable(R.drawable.buttons_default_background));
                lightButton.setBackground(getResources().getDrawable(R.drawable.selected_mode_background));
                break;

            case satellite:
                ModeText.satelitteButton_Attributes(dark_textView,light_textView,satelitte_textView);
                darkButton.setBackground(getResources().getDrawable(R.drawable.buttons_default_background));
                lightButton.setBackground(getResources().getDrawable(R.drawable.buttons_default_background));
                satelitteButton.setBackground(getResources().getDrawable(R.drawable.selected_mode_background));
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
        String modeString = pref.getString("map_mode", null);
        if (modeString != null) {
            try {
                MapMode mode = MapMode.valueOf(modeString);
                return mode;
            } catch (Exception e) {
                return MapMode.satellite;
            }
        } else {
            return MapMode.satellite;
        }

    }

    private void saveMapLabelsEnabled(Boolean isLabelsEnabled) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("map_labels_enabled", isLabelsEnabled);
        editor.apply();

        Log.d("LOGG",isLabelsEnabled.toString());
    }

    private Boolean loadMapLabelsEnabled() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        Boolean isEnabled = pref.getBoolean("map_labels_enabled",true);

        Log.d("ENABLED",isEnabled.toString());
        return isEnabled;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        bottomsheet = findViewById(R.id.botttom_sheet);
        bottomsheet.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseSwitchCompatOrMaterialCode")
            @Override
            public void onClick(View v) {

                dialog = new Dialog(MapsActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.bottomsheetlayout);

                ImageButton darkButton = dialog.findViewById(R.id.dark_button);
                ImageButton lightButton = dialog.findViewById(R.id.ligth_button);
                ImageButton satelitteButton = dialog.findViewById(R.id.satellite_button);
                Switch swbutton = dialog.findViewById(R.id.switch_button);

                updateDialogSelection();

                swbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        saveMapLabelsEnabled(isChecked);
                        updateMapStyle();
                    }
                });
                darkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveMapMode(MapMode.dark);
                        updateMapStyle();
                        updateDialogSelection();
                    }
                });
                lightButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveMapMode(MapMode.light);
                        updateMapStyle();
                        updateDialogSelection();
                    }
                });
                satelitteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveMapMode(MapMode.satellite);
                        updateMapStyle();
                        updateDialogSelection();
                    }
                });

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);

                WindowManager.LayoutParams dimSetting = dialog.getWindow().getAttributes();
                dimSetting.dimAmount = 0.1f;
                dialog.getWindow().setAttributes(dimSetting);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        LatLng location = new LatLng(36.74757, 28.94087);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 14.7f));
        updateMapStyle();
    }
}