package com.example.tamz2_project;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.media.SoundPool;

import java.util.Random;

public class Coin extends DropableObject {
    public Coin(Resources resources, SoundPool soundPool, int sound, int displayWidth, int displayHeight) {
        Random randomGenerator = new Random();
        this.soundPool = soundPool;
        this.sound = sound;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        this.x = randomGenerator.nextInt(displayWidth - width) + width/2;
        this.y = -height/2;
        this.speed= randomGenerator.nextInt(3) + 2;

        this.image = BitmapFactory.decodeResource(resources, R.drawable.coin);
    }

    @Override
    public void onTouch(Ship ship) {
        this.soundPool.play(this.sound, 1 , 1, 0, 0, 1);
        ship.onCoinHit();
    }
}

