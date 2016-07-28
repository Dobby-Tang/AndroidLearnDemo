package com.example.content.signatureview;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button clear;
    private Button define;
    private SignatureView signatureView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signatureView = (SignatureView) findViewById(R.id.signature_view);
        clear = (Button) findViewById(R.id.button);
        define = (Button) findViewById(R.id.button1);
        imageView = (ImageView) findViewById(R.id.signature_img);


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signatureView.clear();
                imageView.setImageBitmap(null);
            }
        });

        define.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signatureView.setDrawingCacheEnabled(true);
                imageView.setImageBitmap(signatureView.getBitmap());
            }
        });

    }
}
