package sd.aman.tool;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class Vibrate extends AppCompatActivity {
    ToggleButton btn;
    Vibrator v;

    //Pattern to vibrate every second without any delay
    long[] pattern = {0, 1000, 0};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vibrator_test);
        btn = (ToggleButton) findViewById(R.id.toggleButton);

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if (v.hasVibrator()) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (btn.isChecked()) {
                        //Vibrate the pattern indefinitely
                        v.vibrate(pattern, 0);
                        Toast.makeText(getApplicationContext(), "Vibrator ON.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Vibrator OFF.", Toast.LENGTH_LONG).show();
                        v.cancel();
                    }
                }
            });
        } else {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btn.setChecked(false);
                    Toast.makeText(getApplicationContext(), "No Vibrator Found in Device.", Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (v.hasVibrator())
            v.cancel();
    }

}
