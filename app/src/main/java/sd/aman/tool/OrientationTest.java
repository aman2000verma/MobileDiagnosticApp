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

public class OrientationTest extends AppCompatActivity implements SensorEventListener {

    //set instances for the sensorManager, accelerometer, and textViews
    private SensorManager sensorManager;
    private Sensor orientation;
    private TextView xValue;
    private TextView yValue;
    private TextView zValue;

    public void onSensorChanged(SensorEvent sensorEvent) {
        setTitle("Orientation");
        float current_xValue = sensorEvent.values[0];
        float current_yValue = sensorEvent.values[1];
        float current_zValue = sensorEvent.values[2];

        xValue.setText(String.valueOf("X: " + current_xValue + "°"));
        yValue.setText(String.valueOf("Y: " + current_yValue + "°"));
        zValue.setText(String.valueOf("Z: " + current_zValue + "°"));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orientation_test);

        //retrieve widgets
        xValue = findViewById(R.id.text1);
        yValue = findViewById(R.id.text2);
        zValue = findViewById(R.id.text3);

        //define instances
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        orientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }

    //register the listener once the activity starts
    @Override
    protected void onStart() {
        super.onStart();
        if (orientation != null) {
            sensorManager.registerListener(this, orientation, sensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    //stop the sensor when the activity stops to reduce battery usage
    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }
}