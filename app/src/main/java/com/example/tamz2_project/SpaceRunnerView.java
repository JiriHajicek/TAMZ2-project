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
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * TODO: document your custom view class.
 */
public class SpaceRunnerView extends View implements Runnable {
    private SoundPool soundPool;
    private int displayWidth;
    private int displayHeight;
    private int objectDelay;
    private Thread thread;
    private boolean isRunning;
    private boolean classesExists;
    private Ship ship;
    private DropableObjects objects;
    private int delay;
    private int crashSound;
    private int scoreSound;

    public SpaceRunnerView(GameActivity gameActivity) {
        super(gameActivity);
        this.isRunning = true;
        this.ship = new Ship(getResources());
        this.classesExists = true;
        this.objects = new DropableObjects();
        this.objectDelay = 200;
        this.delay = 0;


        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build();
        this.soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .build();

        this.crashSound = this.soundPool.load(gameActivity, R.raw.crash, 1);
        this.scoreSound = this.soundPool.load(gameActivity, R.raw.score, 1);
    }

    public SpaceRunnerView(Context context) {
        super(context);
    }

    public SpaceRunnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpaceRunnerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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

    private void generateObject() {
        if(this.ship.isAlive()){
            Random randomGenerator = new Random();
            int isCoin = randomGenerator.nextInt(3);

            Log.d("generate", "isCoin " + (isCoin == 0));
            this.objects.add(isCoin == 0
                    ? new Coin(getResources(), this.soundPool, this.scoreSound, this.displayWidth, this.displayHeight)
                    : new Asteroid(getResources(), this.soundPool, this.crashSound, this.displayWidth, this.displayHeight));
        } else {
            this.objects.clear();
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
            if(this.delay == this.objectDelay){
               this.generateObject();
                this.delay = 0;
            }
            sleep();
            this.delay += 1;
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
            this.objects.render(canvas, this.ship);
        }
    }
}
