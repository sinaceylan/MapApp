package com.sina.map;

import android.graphics.Color;
import android.widget.TextView;

public class ModeTexts {

    public void darkButton_Attributes(TextView dark_textView, TextView light_textView, TextView satelitte_textView){
        dark_textView.setText("Dark");
        dark_textView.setTextSize(14);
        dark_textView.setTextColor(Color.parseColor("#2979FF"));

        light_textView.setText("Light");
        light_textView.setTextSize(14);
        light_textView.setTextColor(Color.parseColor("#000000"));

        satelitte_textView.setText("Satelitte");
        satelitte_textView.setTextSize(14);
        satelitte_textView.setTextColor(Color.parseColor("#000000"));

    }
    public void lightButton_Attributes(TextView dark_textView,TextView light_textView,TextView satelitte_textView){
        dark_textView.setText("Dark");
        dark_textView.setTextSize(14);
        dark_textView.setTextColor(Color.parseColor("#000000"));

        light_textView.setText("Light");
        light_textView.setTextSize(14);
        light_textView.setTextColor(Color.parseColor("#2979FF"));

        satelitte_textView.setText("Satelitte");
        satelitte_textView.setTextSize(14);
        satelitte_textView.setTextColor(Color.parseColor("#000000"));
    }
    static void satelitteButton_Attributes(TextView dark_textView, TextView light_textView, TextView satelitte_textView){
        dark_textView.setText("Dark");
        dark_textView.setTextSize(14);
        dark_textView.setTextColor(Color.parseColor("#000000"));

        light_textView.setText("Light");
        light_textView.setTextSize(14);
        light_textView.setTextColor(Color.parseColor("#000000"));

        satelitte_textView.setText("Satelitte");
        satelitte_textView.setTextSize(14);
        satelitte_textView.setTextColor(Color.parseColor("#2979FF"));
    }
}
