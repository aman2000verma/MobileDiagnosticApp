package sd.aman.tool;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LightTest extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor lightSensor;
    private TextView lightSensorText;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float currentValue = sensorEvent.values[0];
        setTitle("Light Sensor");

        lightSensorText.setText("Illuminance = " + String.valueOf(currentValue) + " lx");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pressure_test);

        //retrieve widget
        lightSensorText = findViewById(R.id.text1);

        //define instances
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    //register the listener once the activity starts
    @Override
    protected void onStart() {
        super.onStart();
        if (lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, sensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    //stop the sensor when the activity stops to reduce battery usage
    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }
}