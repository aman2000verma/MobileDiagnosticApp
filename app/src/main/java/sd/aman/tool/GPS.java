package sd.aman.tool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class GPS extends AppCompatActivity {

    TextView out;
    Button testBtn;
    LocationManager gps;
    boolean GpsStatus;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        //Request Permission
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);

        out = (TextView) findViewById(R.id.output);
        testBtn = (Button) findViewById(R.id.button);

        CheckGpsStatus();
        testBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!GpsStatus) {
                    Intent enableGPS = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(enableGPS);
                } else {
                    CheckGpsStatus();

                    @SuppressLint("MissingPermission")
                    Location loc = gps.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    double lat = loc.getLatitude();
                    double lon = loc.getLongitude();
                    String provider = loc.getProvider();
                    Float accuracy = loc.getAccuracy();

                    out.append("\nProvider: " + provider +
                            "\nAccuracy: " + accuracy +
                            "\nLatitude: " + lat +
                            "\nLongitude: " + lon
                    );

                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        CheckGpsStatus();
    }

    public void CheckGpsStatus() {
        gps = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        assert gps != null;
        GpsStatus = gps.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (GpsStatus == true) {
            out.setText("Status: GPS Enabled");
        } else {
            out.setText("Status: GPS Disabled");
        }
    }

}
