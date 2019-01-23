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

    }

    public void btnConnect (View v){
        TextView anzeige = findViewById(R.id.textView);
        if (conn.isconn) {
            anzeige.setText("Verbunden!!!!");
        }
        else{
            if (conn.connect()) {
                anzeige.setText("Verbunden");
            }
            else{
                anzeige.setText("Fehler bei der Verbindung");
            }
        }





    }

    public void findPoint(View view) {
        Thread FindingThread = new Thread(finding);
        FindingThread.start();
    }

    Runnable finding = new Runnable() {
        int status;
        boolean found = false;

        public void run() {

            while (!found) {
                conn.sendMessage(100);
                status = conn.readMessage();
                switch (status) {
                    /** found green point and finished **/
                    case 1:
                        found = true;
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
        Intent i = new Intent(this, FindActivity.class);
        startActivity(i);
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
        Intent i = new Intent(this,ShowSensors.class);
        startActivity(i);
    }
}



