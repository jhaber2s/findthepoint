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





        farbe.setOnClickListener(new View.OnClickListener() {


            int farbe = 0;
            public void onClick(View v) {


                    conn.sendMessage(4);


                    farbe = conn.readMessage();

                    anzeige.setText(Integer.toString(farbe));







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

}



