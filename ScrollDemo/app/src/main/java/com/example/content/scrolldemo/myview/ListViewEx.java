package com.example.content.scrolldemo.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
*
*@author Dobby-Tang
*created at 16/5/26  上午10:35
*/
public class ListViewEx extends ListView{

    private static final String TAG = "ListViewEx";

    private HorizontalScrollViewEx2 mHorizontalScrollViewEx2;
    private int mLastX = 0;
    private int mLastY = 0;

    public ListViewEx(Context context) {
        super(context);
    }

    public ListViewEx(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    public ListViewEx(Context context,AttributeSet attrs,int defStyle){
        super(context,attrs,defStyle);
    }

    public void setHorizontalScrollViewEx2(HorizontalScrollViewEx2 mHorizontalScrollViewEx2){
        this.mHorizontalScrollViewEx2 = mHorizontalScrollViewEx2;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mHorizontalScrollViewEx2.requestDisallowInterceptTouchEvent(true);
                break;

            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;

                if (Math.abs(deltaX) > Math.abs(deltaY)){
                    mHorizontalScrollViewEx2.requestDisallowInterceptTouchEvent(false);
                }
                break;

            case MotionEvent.ACTION_UP:

                break;
        }

        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(ev);
    }
}
