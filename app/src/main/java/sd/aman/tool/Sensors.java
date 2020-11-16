package sd.aman.tool;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Sensors extends AppCompatActivity {

    CardView accelerometer, ambient, magnetic, gyroscope, gravity, light, proximity, pressure, humidity, orientation, step;

    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_list);

        accelerometer = (CardView) findViewById(R.id.cardAccelerometer);
        ambient = (CardView) findViewById(R.id.cardAmbientTemp);
        magnetic = (CardView) findViewById(R.id.cardMagnetic);
        gyroscope = (CardView) findViewById(R.id.cardGyroscope);
        gravity = (CardView) findViewById(R.id.cardGravity);
        light = (CardView) findViewById(R.id.cardLight);
        proximity = (CardView) findViewById(R.id.cardProximity);
        pressure = (CardView) findViewById(R.id.cardPressure);
        humidity = (CardView) findViewById(R.id.cardHumidity);
        orientation = (CardView) findViewById(R.id.cardOrientation);
        step = (CardView) findViewById(R.id.cardSteps);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
                    Intent sensor = new Intent(getApplicationContext(), AccelerometerTest.class);
                    startActivity(sensor);
                } else {
                    Toast.makeText(getApplicationContext(), "Sensor Not Supported By Device.", Toast.LENGTH_LONG).show();
                }
            }
        });

        ambient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
                    Intent sensor = new Intent(getApplicationContext(), AmbientTest.class);
                    startActivity(sensor);
                } else {
                    Toast.makeText(getApplicationContext(), "Sensor Not Supported By Device.", Toast.LENGTH_LONG).show();
                }
            }
        });

        magnetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
                    Intent sensor = new Intent(getApplicationContext(), MagnetTest.class);
                    startActivity(sensor);
                } else {
                    Toast.makeText(getApplicationContext(), "Sensor Not Supported By Device.", Toast.LENGTH_LONG).show();
                }
            }
        });

        gyroscope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null) {
                    Intent sensor = new Intent(getApplicationContext(), GyroscopeTest.class);
                    startActivity(sensor);
                } else {
                    Toast.makeText(getApplicationContext(), "Sensor Not Supported By Device.", Toast.LENGTH_LONG).show();
                }
            }
        });

        gravity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null) {
                    Intent sensor = new Intent(getApplicationContext(), GravityTest.class);
                    startActivity(sensor);
                } else {
                    Toast.makeText(getApplicationContext(), "Sensor Not Supported By Device.", Toast.LENGTH_LONG).show();
                }
            }
        });

        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
                    Intent sensor = new Intent(getApplicationContext(), LightTest.class);
                    startActivity(sensor);
                } else {
                    Toast.makeText(getApplicationContext(), "Sensor Not Supported By Device.", Toast.LENGTH_LONG).show();
                }
            }
        });

        proximity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {
                    Intent sensor = new Intent(getApplicationContext(), ProximityTest.class);
                    startActivity(sensor);
                } else {
                    Toast.makeText(getApplicationContext(), "Sensor Not Supported By Device.", Toast.LENGTH_LONG).show();
                }
            }
        });

        pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) != null) {
                    Intent sensor = new Intent(getApplicationContext(), PressureTest.class);
                    startActivity(sensor);
                } else {
                    Toast.makeText(getApplicationContext(), "Sensor Not Supported By Device.", Toast.LENGTH_LONG).show();
                }
            }
        });

        humidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null) {
                    Intent sensor = new Intent(getApplicationContext(), HumidityTest.class);
                    startActivity(sensor);
                } else {
                    Toast.makeText(getApplicationContext(), "Sensor Not Supported By Device.", Toast.LENGTH_LONG).show();
                }
            }
        });

        orientation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION) != null) {
                    Intent sensor = new Intent(getApplicationContext(), OrientationTest.class);
                    startActivity(sensor);
                } else {
                    Toast.makeText(getApplicationContext(), "Sensor Not Supported By Device.", Toast.LENGTH_LONG).show();
                }
            }
        });

        step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
                    Intent sensor = new Intent(getApplicationContext(), StepsTest.class);
                    startActivity(sensor);
                } else {
                    Toast.makeText(getApplicationContext(), "Sensor Not Supported By Device.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
