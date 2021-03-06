package com.example.tamz2_project;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;

public class Ship {
    public static int shipWidth = 100;
    public static int shipHeight = 100;
    private int x;
    private int y;
    private int displayWidth;
    private int health;
    private int score;
    private Bitmap shipImage;
    private Bitmap hearthImage;
    private boolean moveLeft;
    private boolean moveRight;
    private boolean sizesSet;

    public Ship(Resources resources) {
        this.x = 0;
        this.y = 0;
        this.displayWidth = 0;
        this.shipImage = BitmapFactory.decodeResource(resources, R.drawable.spaceship);
        this.hearthImage = BitmapFactory.decodeResource(resources, R.drawable.heart);
        this.moveRight = false;
        this.moveLeft = false;
        this.sizesSet = false;
        this.health = 3;
        this.score = 0;
    }

    public void fixSizes(int height, int width){
        if(!this.sizesSet){
            this.x = width / 2;
            this.y = height - 100;
            this.displayWidth = width;
            this.sizesSet = true;
        }
    }

    public void render(Canvas canvas) {
        move();
        canvas.drawBitmap(shipImage, null, new Rect(x - shipWidth/2, y - shipHeight,x + shipWidth/2, y), null);
        for(int i = 0; i<health; i+=1){
            canvas.drawBitmap(this.hearthImage,null, new Rect(this.displayWidth - 25 - i*100, 25, this.displayWidth - 30 - 50 - i*100,75 ), null);
        }

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        canvas.drawText("Score: " + this.score,  25, 75, paint);
    }

    public void setMoveRight(boolean value){
        this.moveRight = value;
    }

    public void setMoveLeft(boolean value){
        this.moveLeft = value;
    }

    private void move(){
        if(this.moveLeft && !this.moveRight && this.x > shipWidth/2){
            this.x -= 10;
        } else if(this.moveRight && !this.moveLeft && this.x <  displayWidth-shipWidth/2){
            this.x += 10;
        }
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void onAsteroidHit(){
        this.health -= 1;
    }

    public void onCoinHit(){
        this.score += 10;
    }

    public boolean isAlive(){
        return this.health > 0;
    }

    public int getScore(){
        return this.score;
    }
}