package sd.aman.tool;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HumidityTest extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor humiditySensor;
    private TextView humiditySensorText;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        //retrieve the current value of the pressure sensor
        float currentValue = sensorEvent.values[0];
        setTitle("Relative Humidity Sensor");

        //display the retrieved value onto the textView
        humiditySensorText.setText("Relative Humidity = " + String.valueOf(currentValue) + " %");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

        //pressure sensor does not report accuracy changes
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.humidity_test);

        //retrieve widget
        humiditySensorText = findViewById(R.id.text1);

        //define instances
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
    }

    //register the listener once the activity starts
    @Override
    protected void onStart() {
        super.onStart();
        if (humiditySensor != null) {
            sensorManager.registerListener(this, humiditySensor, sensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    //stop the sensor when the activity stops to reduce battery usage
    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }
}