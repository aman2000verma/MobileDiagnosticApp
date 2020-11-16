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

public class GyroscopeTest extends AppCompatActivity implements SensorEventListener {

    //set instances for the sensorManager, accelerometer, and textViews
    private SensorManager sensorManager;
    private Sensor gyroscope;
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
        xValue.setText(String.valueOf("X: " + current_xValue + " rad/s"));
        yValue.setText(String.valueOf("Y: " + current_yValue + " rad/s"));
        zValue.setText(String.valueOf("Z: " + current_zValue + " rad/s"));
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
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    //register the listener once the activity starts
    @Override
    protected void onStart() {
        super.onStart();

        if (gyroscope != null) {

            sensorManager.registerListener(this, gyroscope, sensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    //stop the sensor when the activity stops to reduce battery usage
    @Override
    protected void onStop() {
        super.onStop();

        sensorManager.unregisterListener(this);
    }
}