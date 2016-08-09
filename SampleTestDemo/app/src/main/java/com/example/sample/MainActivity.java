package com.example.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by guohao on 16/8/9.
 */
public class MainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void zoomable(View v){
        Intent intent = new Intent(this,ZoomableActivity.class);
        startActivity(intent);
    }

    public void zoomableList(View v){
        Intent intent = new Intent(this,ZoomableListActivity.class);
        startActivity(intent);
    }
}
