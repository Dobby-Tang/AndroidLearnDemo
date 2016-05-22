package com.example.content.scrolldemo.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

/**
*@author By Tang
*created at 16/5/22  下午9:11
*/
public class ScrollButton extends TextView{

    private static final String TAG = "ScrollButton";

    private int mScaledTouchSlop;
    private int mLastX = 0;
    private int mLastY = 0;


    public ScrollButton(Context context) {
        super(context);
    }

    public ScrollButton(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }

    public ScrollButton(Context context,AttributeSet attrs , int defStyle){
        super(context,attrs,defStyle);
        init();
    }

    private void init(){
        mScaledTouchSlop = ViewConfiguration.get(getContext())
                .getScaledTouchSlop();
        Log.d(TAG, "mScaledTouchSlop: " + mScaledTouchSlop);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:

                int deltaX = x - mLastX;
                int deltaY = y - mLastY;

                int translationX = (int) ViewHelper.getTranslationX(this) + deltaX;
                int translationY = (int) ViewHelper.getTranslationY(this) + deltaY;

                ViewHelper.setTranslationX(this,translationX);
                ViewHelper.setTranslationY(this,translationY);

                break;

            case MotionEvent.ACTION_UP:

                break;
        }

        mLastX = x;
        mLastY = y;

        return true;
    }
}
