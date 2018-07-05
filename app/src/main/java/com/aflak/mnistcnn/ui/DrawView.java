package com.aflak.mnistcnn.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by omar on 7/5/18.
 */

public class DrawView extends LinearLayout {
    private Bitmap bitmap;
    private Canvas bitmapCanvas;
    private Path path;
    private Paint paint;

    private boolean isClear;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.BLACK);

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(90);

        path = new Path();
        isClear = true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
        bitmapCanvas.drawColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isClear){
            path.rewind();
            canvas.drawColor(Color.BLACK);
            bitmapCanvas.drawColor(Color.BLACK);
            isClear = false;
        }
        else {
            canvas.drawPath(path, paint);
            bitmapCanvas.drawPath(path, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(), event.getY());
                invalidate();
                break;
        }
        return true;
    }

    public void clear(){
        isClear = true;
        invalidate();
    }

    public float[] getImage(int width, int height){
        Bitmap resized = Bitmap.createScaledBitmap(bitmap, width, height, true);
        float[] image = new float[width*height];
        int p = 0;

        for(int i=0 ; i<resized.getHeight() ; i++){
            for(int j=0 ; j<resized.getWidth() ; j++){
                int pixel = resized.getPixel(j, i);
                int red = Color.red(pixel);
                int blue = Color.blue(pixel);
                int green = Color.green(pixel);
                if(red==255 && blue==255 && green==255){
                    image[p++] = 255;
                }
                else{
                    image[p++] = 0;
                }
            }
        }
        return image;
    }
}
