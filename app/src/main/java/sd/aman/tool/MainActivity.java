package sd.aman.tool;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 101;
    ImageButton hardInfo, sysInfo, hardTest, sensorTest, netTest, viewApps;
    Button fullTest;
    TextView devName, imeiOne, imeiTwo, android, cpu, ram, storage;

    public static String imei1, imei2;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hardInfo = (ImageButton) findViewById(R.id.Button1);
        sysInfo = (ImageButton) findViewById(R.id.Button2);
        hardTest = (ImageButton) findViewById(R.id.Button3);
        sensorTest = (ImageButton) findViewById(R.id.Button4);
        netTest = (ImageButton) findViewById(R.id.Button5);
        viewApps = (ImageButton) findViewById(R.id.Button6);
        devName = (TextView) findViewById(R.id.text_devName);
        imeiOne = (TextView) findViewById(R.id.text_IMEI1);
        imeiTwo = (TextView) findViewById(R.id.text_IMEI2);
        android = (TextView) findViewById(R.id.text_version);
        cpu = (TextView) findViewById(R.id.text_cpu);
        ram = (TextView) findViewById(R.id.text_ram);
        storage = (TextView) findViewById(R.id.text_storage);
        fullTest = (Button) findViewById(R.id.report);

        sensorTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sensorList = new Intent(getApplicationContext(), Sensors.class);
                startActivity(sensorList);
            }
        });
        hardTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hardTest = new Intent(getApplicationContext(), TestHardware.class);
                startActivity(hardTest);
            }
        });
        sysInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sysInfo = new Intent(getApplicationContext(), SystemInfo.class);
                startActivity(sysInfo);
            }
        });
        netTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent netTest = new Intent(getApplicationContext(), TestNetwork.class);
                startActivity(netTest);
            }
        });
        viewApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent apps = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
                startActivity(apps);
            }
        });
        hardInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hardInfo = new Intent(getApplicationContext(), HardwareInfo.class);
                startActivity(hardInfo);
            }
        });
        fullTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fullTest = new Intent(getApplicationContext(), FullTest.class);
                startActivity(fullTest);
            }
        });

        devName.append(Build.MANUFACTURER + " " + Build.MODEL);
        android.append(Build.VERSION.RELEASE);
        ram.append(String.valueOf(totalRamSize()) + " MB");
        long internal = totalInternalMemorySize();
        long external = totalExternalMemorySize();
        storage.append(String.valueOf(internal + external) + " MB");

        try {
            Map<String, String> map = new HashMap<>(getCPUInfo());
            String output = "";
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.toString().contains("model_name"))
                    output += entry.getValue();
                if (entry.toString().contains("cpu_MHz"))
                    output += " @ " + entry.getValue() + "MHz";
            }

            cpu.append(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
            return;
        }
        if (telephonyManager.getPhoneCount() == 2) {
            imei1 = telephonyManager.getImei(0);
            imeiOne.append(imei1);

            imei2 = telephonyManager.getImei(1);
            imeiTwo.append(imei2);
        } else {
            imei1 = telephonyManager.getImei(0);
            imeiOne.append(imei1);

            imei2 = "N/A";
            imeiTwo.append(imei2);
        }
    }

    //Permission for IMEI
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    private long totalRamSize() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        long availableMegs = mi.totalMem / 1048576L;
        return availableMegs;
    }

    public static long totalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        return stat.getTotalBytes() / 1048576L;
    }

    public static boolean externalMemoryAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static long totalExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            return stat.getTotalBytes() / 1048576L;
        } else {
            return 0;
        }
    }

    public static Map<String, String> getCPUInfo() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/proc/cpuinfo"));
        String str;
        Map<String, String> output = new HashMap<>();

        while ((str = br.readLine()) != null) {
            String[] data = str.split(":");
            if (data.length > 1) {
                String key = data[0].trim().replace(" ", "_");
                output.put(key, data[1].trim());
            }
        }

        br.close();
        return output;
    }
}
