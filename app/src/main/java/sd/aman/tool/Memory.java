package sd.aman.tool;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class Memory extends AppCompatActivity {

    TextView output1, output2, output3;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory);
        output1 = (TextView) findViewById(R.id.output1);
        output2 = (TextView) findViewById(R.id.output2);
        output3 = (TextView) findViewById(R.id.output3);

        /**
         * RAM Information
         */
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);

        long totalRAM = mi.totalMem / 1048576L;
        long freeRAM = mi.availMem / 1048576L;
        long usedRAM = totalRAM - freeRAM;

        output1.setText("Total RAM: " + totalRAM + "MB" +
                "\nRAM Used: " + usedRAM + "MB" +
                "\nAvailable RAM: " + freeRAM + "MB");

        /**
         * Internal Storage
         */
        File path = Environment.getDataDirectory();
        StatFs in = new StatFs(path.getPath());
        long totalInternal = in.getTotalBytes() / 1048576L;
        long freeInternal = in.getAvailableBytes() / 1048576L;
        long usedInternal = totalInternal - freeInternal;
        output2.setText("Total: " + totalInternal + "MB" +
                "\nUsed: " + usedInternal + "MB" +
                "\nAvailable: " + freeInternal + "MB");

        /**
         * External Storage
         */
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File pathEx = Environment.getExternalStorageDirectory();
            StatFs ex = new StatFs(pathEx.getPath());
            long totalExternal = ex.getTotalBytes() / 1048576L;
            long freeExternal = ex.getAvailableBytes() / 1048576L;
            long usedExternal = totalExternal - freeExternal;
            output3.setText("Total: " + totalExternal + "MB" +
                    "\nUsed: " + usedExternal + "MB" +
                    "\nAvailable: " + freeExternal + "MB");
        } else {
            output3.setText("N/A");
        }

    }

}
