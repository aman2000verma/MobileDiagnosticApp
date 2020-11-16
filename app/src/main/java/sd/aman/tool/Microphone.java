package sd.aman.tool;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;

public class Microphone extends AppCompatActivity {

    TextView res;
    private MediaRecorder mRecorder;
    Runnable mPollTask;
    Handler mHandler;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mic_test);
        res = (TextView) findViewById(R.id.textView);

        ActivityCompat.requestPermissions(this, new String[]{RECORD_AUDIO}, 1);

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecorder.setOutputFile("/dev/null");

        mHandler = new Handler();
        mPollTask = new Runnable() {
            public void run() {
                double amp = mRecorder.getMaxAmplitude();
                double db = (20 * Math.log10(amp / 0.1));

                res.setText("Level = " + db + " dB");
                mHandler.postDelayed(mPollTask, 500);
            }
        };


        try {
            mRecorder.prepare();
            mRecorder.start();
            mPollTask.run();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Mic not starting.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}


