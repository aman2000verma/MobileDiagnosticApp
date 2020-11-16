package sd.aman.tool;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Battery extends AppCompatActivity {

    TextView output;
    ProgressBar bar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battery);
        output = (TextView) findViewById(R.id.textBattery);
        bar = (ProgressBar) findViewById(R.id.progressBar);
        setBatteryInfo();
    }

    private void setBatteryInfo() {
        output.setText("");
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null, ifilter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        output.append("Battery Level: " + level + "%\n");
        bar.setProgress(level);

        int temp = batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
        output.append("Temperature: " + temp / 10 + "C\n");

        int plugged = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        if (plugged == 0) {
            if (level == 100)
                output.append("Status: Fully Charged | Not Plugged\n");
            else
                output.append("Status: Discharging | Not Plugged\n");
        } else {
            if (level == 100)
                output.append("Status: Fully Charged | Plugged\n");
            else
                output.append("Status: Charging | Plugged\n");
        }

        int health = batteryStatus.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
        output.append("Battery Health: ");
        if (health == BatteryManager.BATTERY_HEALTH_COLD) {
            output.append("Cold");
        }

        if (health == BatteryManager.BATTERY_HEALTH_DEAD) {
            output.append("Dead");
        }

        if (health == BatteryManager.BATTERY_HEALTH_GOOD) {
            output.append("Good");
        }

        if (health == BatteryManager.BATTERY_HEALTH_OVERHEAT) {
            output.append("Overheat");
        }

        if (health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
            output.append("Over Voltage");
        }

        if (health == BatteryManager.BATTERY_HEALTH_UNKNOWN) {
            output.append("Unknown");
        }
        if (health == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE) {
            output.append("Unspecified Failure");
        }
        output.append("\n");


    }


}
