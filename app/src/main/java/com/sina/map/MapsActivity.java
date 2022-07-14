package com.sina.map;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
            @SuppressLint("UseSwitchCompatOrMaterialCode")
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(MapsActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.bottomsheetlayout);

                TextView dark_textView = dialog.findViewById(R.id.dark_textView);                   ///////////////////////
                TextView light_textView = dialog.findViewById(R.id.light_textView);                 ///////////////////////
                TextView hybrid_textView = dialog.findViewById(R.id.hybrid_textView);               ///////////////////////
                TextView satelitte_textView = dialog.findViewById(R.id.satelitte_textView);         ///////////////////////

                ImageButton darkButton = dialog.findViewById(R.id.dark_button);
                ImageButton hybridButton = dialog.findViewById(R.id.hybrid_button);
                ImageButton lightButton = dialog.findViewById(R.id.ligth_button);
                ImageButton satelitteButton = dialog.findViewById(R.id.satellite_button);
                Switch swbutton = dialog.findViewById(R.id.switch_button);

                lightButton.setBackground(getResources().getDrawable(R.drawable.item_background));
                hybridButton.setBackground(getResources().getDrawable(R.drawable.item_background));
                satelitteButton.setBackground(getResources().getDrawable(R.drawable.item_background));
                darkButton.setBackground(getResources().getDrawable(R.drawable.item_background));

                //Switch Button SharedPreferences
                //SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
                //swbutton.setChecked(sharedPreferences.getBoolean("value",true));

                darkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dark_textView.setText("Dark");                                              ///////////////////////
                        dark_textView.setTextSize(14);                                              ///////////////////////
                        dark_textView.setTextColor(Color.parseColor("#2979FF"));           ///////////////////////

                        light_textView.setText("Light");                                            ///////////////////////
                        light_textView.setTextSize(14);                                             ///////////////////////
                        light_textView.setTextColor(Color.parseColor("#000000"));          ///////////////////////

                        hybrid_textView.setText("Hybrid");                                          ///////////////////////
                        hybrid_textView.setTextSize(14);                                            ///////////////////////
                        hybrid_textView.setTextColor(Color.parseColor("#000000"));         ///////////////////////

                        satelitte_textView.setText("Satelitte");                                    ///////////////////////
                        satelitte_textView.setTextSize(14);                                         ///////////////////////
                        satelitte_textView.setTextColor(Color.parseColor("#000000"));      ///////////////////////

                        //For Changing Border Color
                        lightButton.setBackground(getResources().getDrawable(R.drawable.item_background));
                        hybridButton.setBackground(getResources().getDrawable(R.drawable.item_background));
                        satelitteButton.setBackground(getResources().getDrawable(R.drawable.item_background));

                        darkButton.setBackground(getResources().getDrawable(R.drawable.on_item_select));
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(MapsActivity.this,R.raw.map_style_dark_no_label));

                        swbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                if(isChecked){

                                    //Switch Button SharedPreferences
                                    //SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                                    //editor.putBoolean("value",true);
                                    //editor.apply();
                                    //swbutton.setChecked(true);

                                    mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(MapsActivity.this,R.raw.map_style_dark_with_labels));
                                }else{

                                    // Switch Button SharedPreferences
                                    //SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                                    //editor.putBoolean("value",false);
                                    //editor.apply();
                                    //swbutton.setChecked(false);

                                    mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(MapsActivity.this,R.raw.map_style_dark_no_label));
                                }
                            }
                        });
                    }
                });

                lightButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dark_textView.setText("Dark");                                              ///////////////////////
                        dark_textView.setTextSize(14);                                              ///////////////////////
                        dark_textView.setTextColor(Color.parseColor("#000000"));           ///////////////////////

                        light_textView.setText("Light");                                            ///////////////////////
                        light_textView.setTextSize(14);                                             ///////////////////////
                        light_textView.setTextColor(Color.parseColor("#2979FF"));          ///////////////////////

                        hybrid_textView.setText("Hybrid");                                          ///////////////////////
                        hybrid_textView.setTextSize(14);                                            ///////////////////////
                        hybrid_textView.setTextColor(Color.parseColor("#000000"));         ///////////////////////

                        satelitte_textView.setText("Satelitte");                                    ///////////////////////
                        satelitte_textView.setTextSize(14);                                         ///////////////////////
                        satelitte_textView.setTextColor(Color.parseColor("#000000"));      ///////////////////////

                        //For Changing Border Color
                        darkButton.setBackground(getResources().getDrawable(R.drawable.item_background));
                        hybridButton.setBackground(getResources().getDrawable(R.drawable.item_background));
                        satelitteButton.setBackground(getResources().getDrawable(R.drawable.item_background));

                        lightButton.setBackground(getResources().getDrawable(R.drawable.on_item_select));
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(MapsActivity.this,R.raw.map_style_light_no_label));

                        swbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked){
                                    mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(MapsActivity.this,R.raw.map_style_light_with_labels));
                                }else{
                                    mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(MapsActivity.this,R.raw.map_style_light_no_label));
                                }
                            }
                        });
                    }
                });

                hybridButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dark_textView.setText("Dark");                                              ///////////////////////
                        dark_textView.setTextSize(14);                                              ///////////////////////
                        dark_textView.setTextColor(Color.parseColor("#000000"));           ///////////////////////

                        light_textView.setText("Light");                                            ///////////////////////
                        light_textView.setTextSize(14);                                             ///////////////////////
                        light_textView.setTextColor(Color.parseColor("#000000"));          ///////////////////////

                        hybrid_textView.setText("Hybrid");                                          ///////////////////////
                        hybrid_textView.setTextSize(14);                                            ///////////////////////
                        hybrid_textView.setTextColor(Color.parseColor("#2979FF"));         ///////////////////////

                        satelitte_textView.setText("Satelitte");                                    ///////////////////////
                        satelitte_textView.setTextSize(14);                                         ///////////////////////
                        satelitte_textView.setTextColor(Color.parseColor("#000000"));      ///////////////////////

                        //For Changing Border Color
                        darkButton.setBackground(getResources().getDrawable(R.drawable.item_background));
                        lightButton.setBackground(getResources().getDrawable(R.drawable.item_background));
                        satelitteButton.setBackground(getResources().getDrawable(R.drawable.item_background));

                        hybridButton.setBackground(getResources().getDrawable(R.drawable.on_item_select));
                        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        //Toast.makeText(MapsActivity.this,"Hybrid is Clicked",Toast.LENGTH_SHORT).show();
                    }
                });

                satelitteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dark_textView.setText("Dark");                                              ///////////////////////
                        dark_textView.setTextSize(14);                                              ///////////////////////
                        dark_textView.setTextColor(Color.parseColor("#000000"));           ///////////////////////

                        light_textView.setText("Light");                                            ///////////////////////
                        light_textView.setTextSize(14);                                             ///////////////////////
                        light_textView.setTextColor(Color.parseColor("#000000"));          ///////////////////////

                        hybrid_textView.setText("Hybrid");                                          ///////////////////////
                        hybrid_textView.setTextSize(14);                                            ///////////////////////
                        hybrid_textView.setTextColor(Color.parseColor("#000000"));         ///////////////////////

                        satelitte_textView.setText("Satelitte");                                    ///////////////////////
                        satelitte_textView.setTextSize(14);                                         ///////////////////////
                        satelitte_textView.setTextColor(Color.parseColor("#2979FF"));      ///////////////////////

                        //For Changing Border Color
                        darkButton.setBackground(getResources().getDrawable(R.drawable.item_background));
                        hybridButton.setBackground(getResources().getDrawable(R.drawable.item_background));
                        lightButton.setBackground(getResources().getDrawable(R.drawable.item_background));

                        satelitteButton.setBackground(getResources().getDrawable(R.drawable.on_item_select));
                        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        //Toast.makeText(MapsActivity.this,"Satelitte is Clicked",Toast.LENGTH_SHORT).show();
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
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Current Position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,12));
    }
}