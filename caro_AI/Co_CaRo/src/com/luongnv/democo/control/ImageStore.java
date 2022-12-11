package com.luongnv.democo.control;
import javax.swing.*;
import java.awt.*;
import java.lang.*;
public class ImageStore {

    public static final Image IMG_X = getImage("/res1/drawable/jmg_X.png");
    public static final Image IMG_O = getImage("/res1/drawable/jmg_O.png");
    public static final Image IMG_ANH_2 = getImage("/res1/drawable/jmg_anh_2.png");
    public static final Image IMG_SQUARE_2 = getImage("/res1/drawable/square.png");
    public static final Image IMG_Anh3 = getImage("/res1/drawable/anhnen.png");
    public static final Image IMG_NEWGAME = getImage("/res1/drawable/newgame.png");
    public static final Image IMG_EXIT1= getImage("/res1/drawable/exit.jpg");
    public static final Image IMG_CLOSE1 = getImage("/res1/drawable/close1.png");
    public static final Image IMG_PLAY23 = getImage("/res1/drawable/play23.png");
    public static final Image IMG_PLAY23456 = getImage("/res1/drawable/1234.jpg");
    public static final Image IMG_NEN7 = getImage("/res1/drawable/nen7.png");
    private static Image getImage(String path){
        return new ImageIcon(ImageStore.class.getResource(path)).getImage();
    }


}

