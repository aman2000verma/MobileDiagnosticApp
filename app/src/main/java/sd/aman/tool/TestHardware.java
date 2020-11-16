package sd.aman.tool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class TestHardware extends AppCompatActivity {

    CardView bluetooth, flash, gps, camera, mic, speaker, vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_hardware);

        bluetooth = (CardView) findViewById(R.id.card_Bluetooth);
        flash = (CardView) findViewById(R.id.card_Flash);
        gps = (CardView) findViewById(R.id.card_GPS);
        camera = (CardView) findViewById(R.id.card_Camera);
        mic = (CardView) findViewById(R.id.card_Microphone);
        speaker = (CardView) findViewById(R.id.card_Speaker);
        vibrator = (CardView) findViewById(R.id.card_Vibrator);


        bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btIntent = new Intent(getApplicationContext(), Bluetooth.class);
                startActivity(btIntent);
            }
        });

        flash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent flashIntent = new Intent(getApplicationContext(), Flash.class);
                startActivity(flashIntent);
            }
        });

        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gpsIntent = new Intent(getApplicationContext(), GPS.class);
                startActivity(gpsIntent);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camIntent = new Intent(getApplicationContext(), Camera.class);
                startActivity(camIntent);
            }
        });

        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camIntent = new Intent(getApplicationContext(), Microphone.class);
                startActivity(camIntent);
            }
        });

        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camIntent = new Intent(getApplicationContext(), Speaker.class);
                startActivity(camIntent);
            }
        });

        vibrator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camIntent = new Intent(getApplicationContext(), Vibrate.class);
                startActivity(camIntent);
            }
        });


    }

}