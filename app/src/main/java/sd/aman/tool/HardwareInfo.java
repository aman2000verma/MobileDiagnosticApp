package sd.aman.tool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HardwareInfo extends AppCompatActivity {
    CardView battery, cpu, memory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hardware_info);
        battery = (CardView) findViewById(R.id.card_battery);
        cpu = (CardView) findViewById(R.id.card_cpu);
        memory = (CardView) findViewById(R.id.card_storage);

        battery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent batteryIntent = new Intent(getApplicationContext(), Battery.class);
                startActivity(batteryIntent);
            }
        });

        cpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cpuIntent = new Intent(getApplicationContext(), CPU.class);
                startActivity(cpuIntent);
            }
        });

        memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent memIntent = new Intent(getApplicationContext(), Memory.class);
                startActivity(memIntent);
            }
        });
    }

}