package com.example.sample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;
import com.facebook.samples.zoomable.ZoomableDraweeView;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by guohao on 16/8/9.
 */
public class ZoomableActivity extends Activity {

    private ZoomableDraweeView draweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FLog.setMinimumLoggingLevel(FLog.VERBOSE);
        Set<RequestListener> listeners = new HashSet<>();
        listeners.add(new RequestLoggingListener());
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setRequestListeners(listeners)
                .setBitmapsConfig(Bitmap.Config.ARGB_8888)
                .build();
        Fresco.initialize(this, config);
        setContentView(R.layout.activity_zoomable);


        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri("http://pic13.nipic.com/20110326/2457331_232645672000_2.jpg")
                .build();
        draweeView = (ZoomableDraweeView) findViewById(R.id.zoomableView);
        draweeView.setController(controller);



    }
}
