package com.example.sample;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.samples.zoomable.ZoomableDraweeView;

/**
 * Created by guohao on 16/8/9.
 */
public class MyPagerAdapter extends PagerAdapter{

    public Object instantiateItem(ViewGroup container, int position) {
        FrameLayout page = (FrameLayout) container.getChildAt(position);
        ZoomableDraweeView zoomableDraweeView = (ZoomableDraweeView) page.getChildAt(0);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri("http://pic25.nipic.com/20121112/5955207_224247025000_2.jpg")
                .build();
        zoomableDraweeView.setController(controller);
        zoomableDraweeView.setTapListener(createTapListener(position));
        page.requestLayout();
        return page;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        FrameLayout page = (FrameLayout) container.getChildAt(position);
        ZoomableDraweeView zoomableDraweeView = (ZoomableDraweeView) page.getChildAt(0);
        zoomableDraweeView.setController(null);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    private GestureDetector.SimpleOnGestureListener createTapListener(final int position) {
        return new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
                Log.d("MyPagerAdapter", "onLongPress: " + position);
            }
        };
    }
}
