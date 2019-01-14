package com.example.jens.findthepoint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShowSensors extends AppCompatActivity {

    Connector conn = new Connector("00:16:53:07:1D:00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sensors);
    }
}
