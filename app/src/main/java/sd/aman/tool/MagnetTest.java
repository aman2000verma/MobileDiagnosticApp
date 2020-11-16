package sd.aman.tool;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MagnetTest extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor magnetometer;
    private TextView xValue;
    private TextView yValue;
    private TextView zValue;
    private ImageView image;

    // record the compass picture angle turned
    private float currentDegree = 0f;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        //retrieve the current values of the magnetometer for each axis
        float current_xValue = sensorEvent.values[0];
        float current_yValue = sensorEvent.values[1];
        float current_zValue = sensorEvent.values[2];
        setTitle("Magnetic Field");

        //display each value onto its corresponding textView
        xValue.setText("X = " + String.valueOf(current_xValue) + " μT");
        yValue.setText("Y = " + String.valueOf(current_yValue) + " μT");
        zValue.setText("Z = " + String.valueOf(current_zValue) + " μT");

        // create a rotation animation (reverse turn degree degrees)
        RotateAnimation ra = new RotateAnimation(
                currentDegree,
                -current_xValue,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        // how long the animation will take place
        ra.setDuration(210);

        // set the animation after the end of the reservation status
        ra.setFillAfter(true);

        // Start the animation
        image.startAnimation(ra);
        currentDegree = -current_xValue;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

        //magnetometer does not report accuracy changes
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.magnet_test);

        //retrieve widgets
        xValue = findViewById(R.id.text1);
        yValue = findViewById(R.id.text2);
        zValue = findViewById(R.id.text3);
        image = (ImageView) findViewById(R.id.imageView);

        //define instances
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    //register the listener once the activity starts
    @Override
    protected void onStart() {
        super.onStart();
        if (magnetometer != null) {
            sensorManager.registerListener(this, magnetometer, sensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    //stop the sensor when the activity stops to reduce battery usage
    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }
}