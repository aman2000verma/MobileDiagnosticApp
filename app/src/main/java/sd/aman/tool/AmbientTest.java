package sd.aman.tool;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AmbientTest extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor ambientSensor;
    private TextView ambientSensorText;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        //retrieve the current value of the pressure sensor
        float currentValue = sensorEvent.values[0];
        setTitle("Pressure Sensor");

        //display the retrieved value onto the textView
        ambientSensorText.setText("Air Temperature = " + String.valueOf(currentValue) + " °C");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

        //pressure sensor does not report accuracy changes
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ambient_test);

        //retrieve widget
        ambientSensorText = findViewById(R.id.text1);

        //define instances
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        ambientSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
    }

    //register the listener once the activity starts
    @Override
    protected void onStart() {
        super.onStart();
        if (ambientSensor != null) {
            sensorManager.registerListener(this, ambientSensor, sensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    //stop the sensor when the activity stops to reduce battery usage
    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }
}