package com.example.tamz2_project;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class SpaceRunnerView extends View implements Runnable {
    private int displayWidth;
    private int displayHeight;
    private Thread thread;
    private boolean isRunning;
    private boolean classesExists;
    private Ship ship;

    public SpaceRunnerView(Context context) {
        super(context);
        init(context);
    }

    public SpaceRunnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SpaceRunnerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.isRunning = true;
        this.ship = new Ship(getResources());
        this.classesExists = true;
    }

    public void resume() {
        this.isRunning = true;
        this.thread = new Thread(this);
        this.thread.start();
    }

    public void pause() throws InterruptedException {
        try {
            this.isRunning = false;
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                if(x<displayWidth/2){
                    this.ship.setMoveLeft(true);
                    this.ship.setMoveRight(false);
                } else {
                    this.ship.setMoveLeft(false);
                    this.ship.setMoveRight(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                this.ship.setMoveLeft(false);
                this.ship.setMoveRight(false);
                break;
        }
        return true;
    }

    @Override
    public void run() {
        while(this.isRunning) {
            invalidate();
            sleep();
        }
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        displayWidth = width;
        displayHeight = height;
        this.ship.fixSizes(height, width);
        super.onSizeChanged(width, height, oldWidth, oldHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        if(this.classesExists) {
            this.ship.render(canvas);
        }
    }
}
