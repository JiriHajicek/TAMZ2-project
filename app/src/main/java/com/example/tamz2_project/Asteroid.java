package com.example.tamz2_project;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Asteroid extends DropableObject {
    public Asteroid(Resources resources, int displayWidth, int displayHeight) {
        Random randomGenerator = new Random();
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        this.x = randomGenerator.nextInt(displayWidth - width) + width/2;
        this.y = -height/2;
        this.speed= randomGenerator.nextInt(5) + 3;

        int asteroidType = randomGenerator.nextInt(3) + 1;
        switch (asteroidType) {
            case 1:
                this.image = BitmapFactory.decodeResource(resources, R.drawable.asteroid1);
                break;
            case 2:
                this.image =  BitmapFactory.decodeResource(resources, R.drawable.asteroid2);
                break;
            case 3:
                this.image = BitmapFactory.decodeResource(resources, R.drawable.asteroid3);
                break;
        }
    }

    @Override
    public void onTouch(Ship ship) {
        ship.onAsteroidHit();
    }
}
