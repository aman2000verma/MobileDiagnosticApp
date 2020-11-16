package sd.aman.tool;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SystemInfo extends AppCompatActivity {

    TextView output1, output2, output3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_info);
        output1 = (TextView) findViewById(R.id.output1);
        output2 = (TextView) findViewById(R.id.output2);
        output3 = (TextView) findViewById(R.id.output3);

        output1.append("\nRelease: " + Build.VERSION.RELEASE +
                "\nSecurity Patch: " + Build.VERSION.SECURITY_PATCH +
                "\nSDK API: " + Build.VERSION.SDK_INT +
                "\nKernel Version: " + System.getProperty("os.version")
        );

        output2.append("\nManufacturer: " + Build.MANUFACTURER +
                "\nModel: " + Build.MODEL +
                "\nDevice: " + Build.DEVICE +
                "\nBoard: " + Build.BOARD +
                "\nDisplay: " + Build.DISPLAY +
                "\nBootloader: " + Build.BOOTLOADER +
                "\nFingerprint: " + Build.FINGERPRINT +
                "\nHardware: " + Build.HARDWARE + "\n"
        );


        output3.append("\nAndroid ID: " + Settings.Secure.ANDROID_ID +
                "\nHost: " + Build.HOST +
                "\nID: " + Build.ID +
                "\nType: " + Build.TYPE +
                "\nUser: " + Build.USER +
                "\nIMEI 1: " + MainActivity.imei1 +
                "\nIMEI 2: " + MainActivity.imei2
        );
    }
}