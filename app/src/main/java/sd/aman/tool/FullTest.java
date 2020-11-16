package sd.aman.tool;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class FullTest extends AppCompatActivity {

    public static SQLiteDatabase db;

    Button start, view;

    String[] tests = {"Battery", "Memory", "GPS",
            "Camera", "Bluetooth", "Flash", "Vibrate",
            "Microphone", "Speaker", "AccelerometerTest",
            "AmbientTest", "LightTest", "ProximityTest", "GyroscopeTest"};

    File myFile;

    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_diagnosis);

        ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, RECORD_AUDIO, ACCESS_FINE_LOCATION}, 1);

        start = (Button) findViewById(R.id.button);
        view = (Button) findViewById(R.id.button1);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectDB();

                try {
                    startDiagnosis();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectDB();
                try {
                    createPDF();
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                viewPdf();
            }
        });

    }

    private void startDiagnosis() throws ClassNotFoundException, FileNotFoundException, DocumentException {

        db.execSQL("DELETE FROM test");

        for (String i : tests) {
            Intent res = new Intent(getApplicationContext(), TestResult.class);
            res.putExtra("test", i);
            startActivity(res);

            Log.d("Current Class: ", i);
            Intent act = new Intent(getApplicationContext(), Class.forName("sd.aman.tool." + i));
            startActivity(act);
        }

    }

    private void connectDB() {
        db = openOrCreateDatabase("MDT", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS test(comp VARCHAR, status VARCHAR);");
        Log.d("DB", "Running");
    }


    private void createPDF() throws DocumentException, FileNotFoundException {
        //Create time stamp
        Date date = new Date();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);

        myFile = new File(Environment.getExternalStorageDirectory() + "/" + timeStamp + ".pdf");

        FileOutputStream output = new FileOutputStream(myFile);

        //Step 1
        Document document = new Document();

        //Step 2
        PdfWriter.getInstance(document, output);

        //Step 3
        document.addAuthor("MDT");
        document.addCreationDate();
        document.addTitle("Test");
        document.open();

        //Step 4 Add content
        document.add((new Anchor("Mobile Diagnostic Tool - Test Report")));
        document.add(new Paragraph("Date: " + timeStamp));

        Cursor resultSet = db.rawQuery("SELECT * FROM test", null);
        resultSet.moveToFirst();
        String res = "";
        for (int i = 1; i <= resultSet.getCount(); i++) {
            res += i + ". " + resultSet.getString(0) + ": " + resultSet.getString(1) + "\n";
            resultSet.moveToNext();
        }

        Log.d("Res = ", res);
        document.add(new Paragraph(res));

        //Step 5: Close the document
        document.close();
    }


    private void viewPdf() {
        Intent intent = new Intent(Intent.ACTION_DEFAULT);
        Uri uri = Uri.fromFile(myFile);
        intent.setDataAndType(uri, "application/pdf");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}


