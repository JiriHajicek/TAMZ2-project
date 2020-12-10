package com.example.tamz2_project;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.DisplayMetrics;

public class Ship {
    private final int shipWidth = 100;
    private final int shipHeight = 100;
    private int x;
    private int y;
    private int displayWidth;
    private Bitmap shipImage;
    private boolean moveLeft;
    private boolean moveRight;
    private boolean sizesSet;

    public Ship(Resources resources) {
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        this.x = 0;
        this.y = 0;
        this.displayWidth = 0;
        this.shipImage = BitmapFactory.decodeResource(resources, R.drawable.spaceship);
        this.moveRight = false;
        this.moveLeft = false;
        this.sizesSet = false;
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
    }

    public void setMoveRight(boolean value){
        this.moveRight = value;
    }

    public void setMoveLeft(boolean value){
        this.moveLeft = value;
    }

    private void move(){
        if(this.moveLeft && !this.moveRight && this.x > shipWidth/2){
            this.x -= 5;
        } else if(this.moveRight && !this.moveLeft && this.x <  displayWidth-shipWidth/2){
            this.x += 5;
        }
    }
}