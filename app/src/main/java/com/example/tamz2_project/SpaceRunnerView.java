package com.example.tamz2_project;

import android.content.Context;
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
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class SpaceRunnerView extends View {
    Bitmap spaceship;

    int displayWidth;
    int displayHeight;

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
        spaceship = BitmapFactory.decodeResource(getResources(), R.drawable.spaceship);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        displayWidth = w;
        displayHeight = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(spaceship, null, new Rect(displayWidth /2 - 50, displayHeight - 150,displayWidth/2 +50, displayHeight-50), null);
    }
}
