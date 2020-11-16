package sd.aman.tool;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class Bluetooth extends AppCompatActivity {
    ToggleButton tblbutton;
    BluetoothAdapter mBluetoothAdapter;
    TextView out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth);
        tblbutton = (ToggleButton) findViewById(R.id.toggleButton);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        out = (TextView) findViewById(R.id.textView6);

        if (mBluetoothAdapter == null) {

            tblbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tblbutton.setChecked(false);
                    Toast.makeText(getApplicationContext(), "Bluetooth not supported", Toast.LENGTH_LONG).show();
                }
            });

        } else {
            tblbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tblbutton.isChecked()) {
                        mBluetoothAdapter.enable();
                        Toast.makeText(getApplicationContext(), "Bluetooth turned ON", Toast.LENGTH_LONG).show();
                        out.setText("Name: " + mBluetoothAdapter.getName());
                        out.append("\nAddress: " + mBluetoothAdapter.getAddress());
                        out.append("\nBonded Devices: " + mBluetoothAdapter.getBondedDevices());
                    } if (!tblbutton.isChecked()) {
                        mBluetoothAdapter.disable();
                        Toast.makeText(getApplicationContext(), "Bluetooth turned OFF", Toast.LENGTH_LONG).show();
                        out.setText("");
                    }
                }
            });
        }
    }
}