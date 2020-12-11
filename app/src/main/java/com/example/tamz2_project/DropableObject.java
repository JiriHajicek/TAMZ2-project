package com.example.tamz2_project;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

abstract class DropableObject implements Dropable{
    protected final int width = 50;
    protected final int height = 50;
    protected int x;
    protected int y;
    protected int displayWidth;
    protected int displayHeight;
    protected Bitmap image;
    protected int speed;

    @Override
    public void render(Canvas canvas) {
        this.y += speed;
        canvas.drawBitmap(this.image, null, new Rect(x - width/2, y - height/2,x + width/2, y + height/2), null);
    }

    @Override
    public boolean shipTouch(double shipX, double shipY) {
        return (Math.abs(shipX - this.x) < (Ship.shipWidth/2 + width/2) && Math.abs(shipY - this.y) < (Ship.shipHeight/2 + height/2));
    }

    @Override
    public boolean isOut(){
        return this.y > (displayHeight + height);
    }

    @Override
    public void faster(){
        this.speed += 1;
    }
}
