package com.luongnv.democo.model;

import com.luongnv.democo.control.ImageStore;

import java.awt.*;

public class Square extends BaseItem{
    public static final int SIZE = 30 ;
    private boolean click;

    public Square() {
    }

    public Square(int x, int y, boolean click) {
        super(x,y);
        this.click = false;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(ImageStore.IMG_SQUARE_2 ,x,y,SIZE,SIZE,null);
    }
    public int getSIZE() {
        return SIZE;
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }

}
