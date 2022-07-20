package com.sina.map;

import android.graphics.Color;
import android.widget.TextView;

public class ModeTexts {

    public void darkButton_Attributes(TextView dark_textView, TextView light_textView, TextView satelitte_textView){
        dark_textView.setText("Dark");
        dark_textView.setTextSize(14);
        dark_textView.setTextColor(Color.parseColor("#32ace5"));

        light_textView.setText("Light");
        light_textView.setTextSize(14);
        light_textView.setTextColor(Color.parseColor("#4a4a4a"));

        satelitte_textView.setText("Satelitte");
        satelitte_textView.setTextSize(14);
        satelitte_textView.setTextColor(Color.parseColor("#4a4a4a"));

    }
    public void lightButton_Attributes(TextView dark_textView,TextView light_textView,TextView satelitte_textView){
        dark_textView.setText("Dark");
        dark_textView.setTextSize(14);
        dark_textView.setTextColor(Color.parseColor("#4a4a4a"));

        light_textView.setText("Light");
        light_textView.setTextSize(14);
        light_textView.setTextColor(Color.parseColor("#32ace5"));

        satelitte_textView.setText("Satelitte");
        satelitte_textView.setTextSize(14);
        satelitte_textView.setTextColor(Color.parseColor("#4a4a4a"));
    }
    static void satelitteButton_Attributes(TextView dark_textView, TextView light_textView, TextView satelitte_textView){
        dark_textView.setText("Dark");
        dark_textView.setTextSize(14);
        dark_textView.setTextColor(Color.parseColor("#4a4a4a"));

        light_textView.setText("Light");
        light_textView.setTextSize(14);
        light_textView.setTextColor(Color.parseColor("#4a4a4a"));

        satelitte_textView.setText("Satelitte");
        satelitte_textView.setTextSize(14);
        satelitte_textView.setTextColor(Color.parseColor("#32ace5"));
    }
}
