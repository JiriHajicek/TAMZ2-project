package com.example.tamz2_project;

import android.graphics.Canvas;

public interface Dropable {
    void render(Canvas canvas);
    boolean shipTouch(double shipX, double shipY);
    void onTouch(Ship ship);
    boolean isOut();
    void faster();
}

