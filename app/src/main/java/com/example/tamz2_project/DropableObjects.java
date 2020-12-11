package com.example.tamz2_project;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Iterator;

public class DropableObjects {
    private final ArrayList<Dropable> items = new ArrayList();

    public DropableObjects() {};

    public void add(Dropable item) {
        this.items.add(item);
    }

    public void render(Canvas canvas, Ship ship) {
        Iterator<Dropable> itr = this.items.iterator();

        while (itr.hasNext()) {
            Dropable i = itr.next();

            if (i.shipTouch(ship.getX(), ship.getY())) {
                itr.remove();
                i.onTouch(ship);
            } else if(i.isOut()){
                itr.remove();
            }
            else i.render(canvas);
        }
    }

    public void clear(){
        this.items.clear();
    }
}
