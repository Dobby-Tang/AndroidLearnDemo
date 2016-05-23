package com.example.content.scrolldemo.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
*
*@author Dobby-Tang
*created at 16/5/23  下午10:48
*/
public class HorizontalScrollViewEx extends ViewGroup{

    private static final String TAG = "HorizontalScrollViewEx";

    private Scroller mScroller;    //滑动
    private VelocityTracker mVelocityTracker;

    private int mChildrenSize;
    private int mChildWidth;
    private int mChildIndex;

    private int mLastX = 0;
    private int mLastY = 0;
    private int mLastXIntercept = 0;
    private int mLastYInterceppt = 0;

    public HorizontalScrollViewEx(Context context) {
        super(context);
        init();
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }

    public HorizontalScrollViewEx(Context context,AttributeSet attrs,int defStyle){
        super(context,attrs,defStyle);
        init();
    }

    private void init(){
        mScroller = new Scroller(getContext());
        mVelocityTracker = VelocityTracker.obtain();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                    intercepted = true;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYInterceppt;

                if (Math.abs(deltaX) > Math.abs(deltaY)){
                    intercepted = true;
                }else {
                    intercepted = false;
                }

                break;

            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;

            default:
                break;
        }

        Log.d(TAG, "onInterceptTouchEvent: ");

        mLastX = x;
        mLastY = y;
        mLastXIntercept = x;
        mLastYInterceppt = y;

        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:

                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }

                break;

            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                scrollBy(- deltaX,0);
                break;

            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                int scrollToChILDIndex = scrollX / mChildWidth;
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                if (Math.abs(xVelocity) >= 50){
                    mChildIndex = xVelocity>0 ? mChildIndex - 1 : mChildIndex + 1;
                }else {
                    mChildIndex = (scrollX + mChildWidth / 2) / mChildWidth;
                }

                mChildIndex = Math.max(0,Math.min(mChildIndex,mChildIndex - 1));
                int dx = mChildIndex * mChildWidth - scrollX;
                break;

            default :
                break;
        }

        mLastX = x;
        mLastY = y;

        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
