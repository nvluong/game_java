package com.luongnv.democo.model;

import java.awt.*;

public abstract class BaseItem {
    public int x;
    public int y;


    public abstract void draw(Graphics2D graphics2D);

    public BaseItem() {
    }

    public BaseItem(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
