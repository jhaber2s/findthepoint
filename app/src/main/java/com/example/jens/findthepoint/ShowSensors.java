package com.example.jens.findthepoint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShowSensors extends AppCompatActivity {

    Connector conn = new Connector("00:16:53:07:1D:00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sensors);

    }

    public void goToMain(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
