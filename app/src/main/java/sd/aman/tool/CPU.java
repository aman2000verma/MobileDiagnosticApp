package sd.aman.tool;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Map;

public class CPU extends AppCompatActivity {

    TextView cpuRes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cpu);
        cpuRes = (TextView) findViewById(R.id.textCPU);

        try {
            Map<String, String> cpuMap = MainActivity.getCPUInfo();
            cpuRes.setText("");
            for (Map.Entry<String, String> entry : cpuMap.entrySet()) {
                cpuRes.append(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
