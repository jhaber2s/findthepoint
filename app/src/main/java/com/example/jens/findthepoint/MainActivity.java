package com.example.jens.findthepoint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Connector conn = new Connector("00:16:53:07:1D:00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button connbtn = findViewById(R.id.btnVerbinden);
        final TextView anzeige = findViewById(R.id.textView);

        connbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conn.connect()) {

                    anzeige.setText("Verbunden");
                }
            }
        });
    }



    public void findPoint(View view) {
        Thread FindingThread = new Thread(finding);
        FindingThread.start();
    }

    Runnable finding = new Runnable() {
        int status;

        public void run() {

            while (true) {
                conn.sendMessage(100);
                status = conn.readMessage();
                switch (status) {
                    /** found green point and finished **/
                    case 1:
                        finished();
                        break;
                    /** found red line and change direction **/
                    case 50:
                        changeDirection();
                        break;
                    default:
                        break;
                }
            }
        }
    };

    public void finished() {
        Intent found = new Intent(this, FindActivity.class);
        startActivity(found);
    }

    public void changeDirection() {
        /**
         * 60	changeDirectionRight90
         * 61	changeDirectionRight75
         * 62	changeDirectionRight60
         * 63	changeDirectionRight45
         * 64	changeDirectionRight30
         * 65	changeDirectionRight15
         * 66	changeDirectionLeft90
         * 67	changeDirectionLeft75
         * 68	changeDirectionLeft60
         * 69	changeDirectionLeft45
         * 70	changeDirectionLeft30
         * 71	changeDirectionLeft15
         */
        Random rnd = new Random();
        int random = rnd.nextInt((72 - 60) )+60;
        conn.sendMessage(random);
    }

    public void showSensors(View view) {
        Intent Sensoren = new Intent(this,ShowSensors.class);
        startActivity(Sensoren);
    }
}



