package sd.aman.tool;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProximityTest extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private TextView proximitySensorText;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        //retrieve the current value of the proximity sensor
        float currentValue = sensorEvent.values[0];
        setTitle("Proximity Sensor");
        //display the retrieved value onto the textView
        proximitySensorText.setText("Distance = " + String.valueOf(currentValue) + " cm");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //proximity sensor does not report accuracy changes
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proximity_test);

        //retrieve widget
        proximitySensorText = findViewById(R.id.text1);

        //define instances
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    //register the listener once the activity starts
    @Override
    protected void onStart() {
        super.onStart();
        if (proximitySensor != null) {
            sensorManager.registerListener(this, proximitySensor, sensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    //stop the sensor when the activity stops to reduce battery usage
    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }
}
