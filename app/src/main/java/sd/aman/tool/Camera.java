package sd.aman.tool;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Camera extends AppCompatActivity {

    private static final int REQUEST_CAPTURE_IMAGE = 101;
    Button btn;
    ImageView pic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        btn = (Button) findViewById(R.id.btn_picture);
        pic = findViewById(R.id.img_picture);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture(view);
            }
        });
    }

    public void takePicture(View view) {
        Intent picture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (picture.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(picture, REQUEST_CAPTURE_IMAGE);
        }
    }


    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CAPTURE_IMAGE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            pic.setImageBitmap(imgBitmap);
        }

    }
}