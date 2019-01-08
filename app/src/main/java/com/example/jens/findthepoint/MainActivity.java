package com.example.jens.findthepoint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Connector conn = new Connector("00:16:53:07:1D:00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button connbtn = findViewById(R.id.btnVerbinden);
        final Button farbe = findViewById(R.id.btnFarbeanzeigen);
        final TextView anzeige = findViewById(R.id.textView);
        final Button algostartbtn = findViewById(R.id.btnalgostart);


        algostartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        farbe.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                farbeanzeigen(anzeige);

            }
        });



        connbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(conn.connect()){

                    anzeige.setText("Verbunden");
                }
            }
        });




    }

    public void farbeanzeigen(TextView anzeige){
        int farbe = 0;






            conn.sendMessage(5);

            
                farbe = conn.readMessage();

                if (farbe >= 43 && farbe <= 45) {
                    anzeige.setText("Blau/Boden");

                }
                if (farbe >= 48 && farbe <= 49) {
                    anzeige.setText("Rot");

                }
                if (farbe >= 54) {
                    anzeige.setText("Gruen");

                }





    }





}



