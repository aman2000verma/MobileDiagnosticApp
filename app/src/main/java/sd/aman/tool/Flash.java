package sd.aman.tool;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Flash extends AppCompatActivity {
    ToggleButton btn;
    private CameraManager mCameraManager;
    private String mCameraId;
    ImageView image;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash);

        btn = (ToggleButton) findViewById(R.id.toggleButton);
        image = (ImageView) findViewById(R.id.imageView4);

        boolean isFlashAvailable = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        if (isFlashAvailable) {
            mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            try {
                mCameraId = mCameraManager.getCameraIdList()[0];
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }

            btn.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View view) {
                    if (btn.isChecked()) {
                        try {
                            mCameraManager.setTorchMode(mCameraId, true);
                            Toast.makeText(getApplicationContext(), "Flash ON", Toast.LENGTH_LONG).show();
                            image.setImageResource(R.drawable.flashon);
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            mCameraManager.setTorchMode(mCameraId, false);
                            Toast.makeText(getApplicationContext(), "Flash OFF", Toast.LENGTH_LONG).show();
                            image.setImageResource(R.drawable.flashoff);
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
        } else {
            btn.setChecked(false);
            Toast.makeText(getApplicationContext(), "Flash not available", Toast.LENGTH_LONG).show();
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btn.setChecked(false);
                    Toast.makeText(getApplicationContext(), "Flash not available", Toast.LENGTH_LONG).show();
                }
            });
        }

    }
}
