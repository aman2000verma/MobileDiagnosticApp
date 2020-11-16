package sd.aman.tool;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class Speaker extends AppCompatActivity {
    ToggleButton btn;
    MediaPlayer mp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speaker);
        btn = (ToggleButton) findViewById(R.id.toggleButton);

        mp = MediaPlayer.create(this, R.raw.sound);
        mp.setLooping(true);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn.isChecked()) {
                    mp.start();
                    Toast.makeText(getApplicationContext(), "Playing.", Toast.LENGTH_SHORT).show();
                } else {
                    mp.pause();
                    Toast.makeText(getApplicationContext(), "Paused Playing.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp.isPlaying())
            mp.stop();
    }

}
