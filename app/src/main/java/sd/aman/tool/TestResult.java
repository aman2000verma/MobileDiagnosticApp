package sd.aman.tool;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TestResult extends AppCompatActivity {

    Button pass, fail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_status);

        pass = (Button) findViewById(R.id.button1);
        fail = (Button) findViewById(R.id.button2);

        Intent i = getIntent();
        final String testName = i.getStringExtra("test");

        final SQLiteDatabase db = FullTest.db;

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql = "INSERT INTO test VALUES('" + testName + "', 'PASS');";
                Log.d("SQL: ", sql);
                db.execSQL(sql);
                finish();
            }
        });

        fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql = "INSERT INTO test VALUES('" + testName + "', 'FAIL');";
                Log.d("SQL: ", sql);
                db.execSQL(sql);
                finish();
            }
        });

    }
}
