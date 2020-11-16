package sd.aman.tool;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StepsTest extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepsSensor;
    private TextView stepsSensorText;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float currentValue = sensorEvent.values[0];
        setTitle("Steps Sensor");
        //display the retrieved value onto the textView
        stepsSensorText.setText("Steps = " + String.valueOf(currentValue));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_test);

        //retrieve widget
        stepsSensorText = findViewById(R.id.text1);

        //define instances
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepsSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
    }

    //register the listener once the activity starts
    @Override
    protected void onStart() {
        super.onStart();
        if (stepsSensor != null) {
            sensorManager.registerListener(this, stepsSensor, sensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    //stop the sensor when the activity stops to reduce battery usage
    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }
}