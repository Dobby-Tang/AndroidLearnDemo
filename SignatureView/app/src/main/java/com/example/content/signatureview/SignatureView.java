package com.example.content.signatureview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
*@author By Tang
*created at 16/7/18  上午9:37
 * 手写签名
*/
public class SignatureView extends View{

    private static final float STROKE_WIDTH = 20f;

    /** Need to track this so the dirty region can accommodate the stroke. **/
    private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;

    private Paint paint = new Paint();
    private Path path = new Path();

    private float lastEventX;
    private float lastEventY;
    private final RectF dirtyRect = new RectF();
    private boolean isTouchEnable = true;   //设置是否可以绘制

    public SignatureView(Context context, AttributeSet arrts) {
        super(context,arrts);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(STROKE_WIDTH);
    }

    public void clear(){
        path.reset();
        setDrawingCacheEnabled(false);
        isTouchEnable = true;
        invalidate();
    }

    public void setTouchEnable(boolean enable){
        this.isTouchEnable = enable;
    }

    //获取了绘制的图片后，禁止继续绘制
    public Bitmap getBitmap(){
        setDrawingCacheEnabled(true);
        isTouchEnable = false;
        return getDrawingCache();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float eventX = event.getX();
        float eventY = event.getY();

        if (isTouchEnable){
            switch (event.getAction()){

                case MotionEvent.ACTION_DOWN:
                    path.moveTo(eventX,eventY);
                    lastEventX = eventX;
                    lastEventY = eventY;
                    break;

                case MotionEvent.ACTION_MOVE:

                case MotionEvent.ACTION_UP:
                    resetDirtyRect(eventX, eventY);
                    int historySize = event.getHistorySize();
                    for(int i = 0; i < historySize; i++){
                        float historicalX = event.getHistoricalX(i);
                        float historicalY = event.getHistoricalY(i);
                        expanDirtyRect(historicalX,historicalY);
                        path.lineTo(historicalX,historicalY);
                    }
                    path.lineTo(eventX,eventY);
                    break;
                default:
                    Log.d("SignatureView", "onTouchEvent: Ignored touch event" + event.toString());
                    return false;
            }

            invalidate(
                    (int)(dirtyRect.left - HALF_STROKE_WIDTH),
                    (int)(dirtyRect.top - HALF_STROKE_WIDTH),
                    (int)(dirtyRect.right + HALF_STROKE_WIDTH),
                    (int)(dirtyRect.bottom + HALF_STROKE_WIDTH));

            lastEventX = eventX;
            lastEventY = eventY;
        }

        return true;
    }

    private void expanDirtyRect(float historicalX,float historicalY){
        if (historicalX < dirtyRect.left){
            dirtyRect.left = historicalX;
        }else if (historicalX > dirtyRect.right){
            dirtyRect.right = historicalX;
        }
        if (historicalY < dirtyRect.top){
            dirtyRect.top = historicalY;
        }else if (historicalY > dirtyRect.bottom){
            dirtyRect.bottom = historicalY;
        }
    }

    private void resetDirtyRect(float eventX,float eventY){
        dirtyRect.left = Math.min(lastEventX,eventX);
        dirtyRect.right = Math.min(lastEventX,eventX);
        dirtyRect.top = Math.min(lastEventY,eventY);
        dirtyRect.bottom = Math.min(lastEventY,eventY);
    }
}
