package com.luongnv.democo.model;

import com.luongnv.democo.control.ImageStore;

import java.awt.*;

public class Item extends BaseItem {
    private int SIZE;
    public static final int TYPE_O = 0;
    public static final int TYPE_X = 1;

    private int type;
    public Item(int x, int y, int type, int SIZE) {
        super(x, y);
        this.type = type;
        this.SIZE = SIZE;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if(this.type == 0){
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.drawImage(ImageStore.IMG_O,x,y,SIZE, SIZE,null);

        }
        if(this.type == 1){
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.drawImage(ImageStore.IMG_X,x,y,SIZE,SIZE,null);
       }
        if(this.type == 2){
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.drawImage(ImageStore.IMG_SQUARE_2,x,y,SIZE,SIZE,null);
        }
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public int getSIZE() {
        return SIZE;
    }

    public void setSIZE(int SIZE) {
        this.SIZE = SIZE;
    }
}
