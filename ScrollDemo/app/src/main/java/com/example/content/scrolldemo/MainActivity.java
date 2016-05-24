package com.example.content.scrolldemo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.content.scrolldemo.myview.HorizontalScrollViewEx;
import com.example.content.scrolldemo.myview.ScrollButton;
import com.example.content.scrolldemo.utils.MyUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ScrollButton button;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (ScrollButton) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HorizontalScrollActivity.class);
                startActivity(intent);
            }
        });
    }


}
