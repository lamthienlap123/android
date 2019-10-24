package com.example.ql_tt_sv;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    EditText edtuser,edtpassword;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttsv_form);
        Anhxa();
        convetpicture();
        String hoten = "Huynh Hoai Bao";
        Log.d("HUYNHBAO",hoten);

    }

    private void convetpicture() {
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.pikalong);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);

        ImageView circularImageView = (ImageView) findViewById(R.id.imageView);
        circularImageView.setImageBitmap(circularBitmap);
    }


    private void Anhxa() {
        edtuser = (EditText)findViewById(R.id.edittextuser);
        edtpassword = (EditText)findViewById(R.id.edittextpassword);
        btn_login = (Button)findViewById(R.id.btn_login);

    }
}
