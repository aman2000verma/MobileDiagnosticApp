package sd.aman.tool;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

;

public class GravityTest extends AppCompatActivity implements SensorEventListener {

    //set instances for the sensorManager, accelerometer, and textViews
    private SensorManager sensorManager;
    private Sensor gravity;
    private TextView xValue;
    private TextView yValue;
    private TextView zValue;

    public void onSensorChanged(SensorEvent sensorEvent) {

        //get the current values of the accelerometer for each axis
        setTitle("Accelerometer");
        float current_xValue = sensorEvent.values[0];
        float current_yValue = sensorEvent.values[1];
        float current_zValue = sensorEvent.values[2];

        //display the current values of the  accelerometer for each axis onto the
        //textView widgets
        xValue.setText(String.valueOf("X: " + current_xValue + " m/s²"));
        yValue.setText(String.valueOf("Y: " + current_yValue + " m/s²"));
        zValue.setText(String.valueOf("Z: " + current_zValue + " m/s²"));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

        //accelerometer does not report accuracy changes
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accelerometer_test);

        //retrieve widgets
        xValue = findViewById(R.id.text1);
        yValue = findViewById(R.id.text2);
        zValue = findViewById(R.id.text3);

        //define instances
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
    }

    //register the listener once the activity starts
    @Override
    protected void onStart() {
        super.onStart();

        if (gravity != null) {

            sensorManager.registerListener(this, gravity, sensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    //stop the sensor when the activity stops to reduce battery usage
    @Override
    protected void onStop() {
        super.onStop();

        sensorManager.unregisterListener(this);
    }
}