package com.luongnv.democo.control;

import com.luongnv.democo.model.Item;
import com.luongnv.democo.model.Square;
import com.luongnv.democo.view.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;


public class GameManager {

    public static final int ROW = 21; // 20
    public static final int COL = 21; // 22

    private Square square;
    private ArrayList<Square> squares;
    private ArrayList<Item> items;
    private ArrayList<Item> items1;

    private int [][] mt;
    public int clickNumber = 0;
    public boolean thang = false;
    public int dem1 = 0;
    public int dem2 = 0;

    public int demX = 0;

    public GameManager() {
        items = new ArrayList<>();
        items1 = new ArrayList<>();
        squares = new ArrayList<>();
        createMaTran();

    }
    public void createMaTran(){
        mt = new int[ROW][COL];
        for (int i = 0; i < ROW ; i++) {
            for (int j = 0; j < COL; j++) {
                mt[i][j] = 0;
            }
        }
    }
    public void showMatrix() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(" "+mt[i][j]);
            }
            System.out.println();
        }
    }
    public void drawMap(Graphics2D graphics2D){
        for(int i = 1; i < ROW; i++){
            for(int j = 1; j < COL; j++){
                square = new Square((i)*Square.SIZE,(j)*Square.SIZE,false);
                squares.add(square);
                square.draw(graphics2D);
            }
        }
    }

    public void newGame(){
        for (int i = 0; i < ROW ; i++) {
            for (int j = 0; j < COL; j++) {
                mt[i][j] = 0;
            }
        }
            clickNumber = 0;
            items1.clear();
            items.clear();
            squares.clear();
        System.out.println("================");
        showMatrix();
    }
    public void newGameO(){
        for (int i = 0; i < ROW ; i++) {
            for (int j = 0; j < COL; j++) {
                mt[i][j] = 0;
            }
        }
        items1.clear();
        items.clear();
        squares.clear();
        System.out.println("================");
        showMatrix();
    }
    // ve
    public void drawItem1(Graphics2D graphics2D){
        for(int i = 0; i < items1.size(); i++){
            items1.get(i).draw(graphics2D);
        }
    }
    public void drawItem(Graphics2D graphics2D){
            for(int i = 0; i < items.size(); i++){
                items.get(i).draw(graphics2D);
            }

    }
    public void drawBG(Graphics2D graphics2D){
        graphics2D.drawImage(ImageStore.IMG_NEN7, 0,0,Gui.WIDTH_FRAME,Gui.HEIGHT_FRAME,null);
    }
    public void drawAnh(Graphics2D graphics2D){
        graphics2D.drawImage(ImageStore.IMG_Anh3,650,20+60,220,220,null);
        graphics2D.drawImage(ImageStore.IMG_ANH_2,650,100+205-50+60,220,220,null);

    }

    // chech thang thua
    public void ngangOThang() {
        for (int i = 0; i < ROW - 1; i++) {
            for (int j = 0; j < COL - 1; j++) {
                if (mt[i][j] == mt[i][j + 1] && mt[i][j] == 1) {
                    dem1++;
                } else if (mt[i][j] == mt[i][j + 1] && mt[i][j] == 2) {
                    dem2++;
                } else {
                    dem1 = 0;
                    dem2 = 0;
                }
                if (dem1 == 4) {
                    System.out.println("X thắng!");
                    JOptionPane.showMessageDialog(null, "Quân " + "X" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                    dem1 = 0;
                    dem2 = 0;
                    thang = true;
                  //  clickNumber = 1;

                    return;
                }
                if (dem2 == 4) {
                    System.out.println("O thang ");
                    JOptionPane.showMessageDialog(null, "Quân " + "O" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                    dem1 = 0;
                    dem2 = 0;
                    thang = true;
                    //clickNumber = 0;
                    return;
                }
            }
        }
//        System.out.println("dem  = " + dem1);
//        System.out.println("dem  = " + dem2);
        //showMatrix();
    }
    public void docOThang(){
        for(int i = 0; i < ROW - 1; i++){
            for(int j = 0; j < COL - 1; j++){
                if(mt[j][i] == mt[j+1][i] && mt[j][i] == 1){
                    dem1++;
                }
                else if(mt[j][i] == mt[j+1][i] && mt[j][i] == 2){
                    dem2++;
                }
                else{
                    dem1 = 0;
                    dem2 = 0;
                }
                if(dem1 == 4){
                    System.out.println("X thang ");
                    JOptionPane.showMessageDialog(null, "Quân " + "X" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                    thang = true;
                    //clickNumber = 1;

                    return;
                }
                if(dem2 == 4){
                    System.out.println("O thang ");
                    JOptionPane.showMessageDialog(null, "Quân " + "O" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                    thang = true;
                    //clickNumber = 0;


                    return;
                }
            }
        }
//        System.out.println("dem  = "+dem1);
//        System.out.println("dem  = "+dem2);
        //showMatrix();
    }
    public void cheoOThang() {
        int dem = 1;
        for(int i = 0; i < ROW -1; i++){
            for(int j = 0;  j < COL -1 ; j++){
                dem = 1;
                for(int k = 1; k <= 5; k++) {
                    if(j + k < COL  && i + k < ROW ){
                        if (mt[i + k][j + k] == mt[i][j]) {
                            dem++;
                        }
                        else{
                            break;
                        }
                        if(dem == 5){
                            if(mt[i][j] == 1){
                                dem1 = 4;
                                if(dem1 == 4){
                                    System.out.println("X thang ");
                                    JOptionPane.showMessageDialog(null, "Quân " + "X" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                                    thang = true;
                                    //clickNumber = 1;

                                    return;
                                }
                            }
                            if(mt[i][j] == 2){
                                dem2 = 4;
                                if(dem2 == 4){
                                    System.out.println("O thang ");
                                    JOptionPane.showMessageDialog(null, "Quân " + "O" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                                    thang = true;
                                   // clickNumber = 0;

                                    return;
                                }
                            }
                        }
//                        System.out.println("dem1  = "+dem1);
//                        System.out.println("dem2  = "+dem2);
                    }
                }
            }

        }
    }
    public void cheo2OThang() {
        int dem = 1;
        for(int i = 0; i < ROW -1; i++){
            for(int j = 0;  j < COL -1 ; j++){
                dem = 1;
                for(int k = 1; k <= 5; k++) {
                    if(j + k < COL && i - k > 0 ){
                        if (mt[i - k][j + k] == mt[i][j]) {
                            dem++;
                        }
                        else{
                            break;
                        }
                        if(dem == 5){
                            if(mt[i][j] == 1){
                                dem1 = 4;
                                if(dem1 == 4){
                                    System.out.println("X thang ");
                                    JOptionPane.showMessageDialog(null, "Quân " + "X" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                                    thang = true;
                                    //clickNumber = 1;
                                    return;
                                }
                            }
                            if(mt[i][j] == 2){
                                dem2 = 4;
                                if(dem2 == 4){
                                    System.out.println("O thang ");
                                    JOptionPane.showMessageDialog(null, "Quân " + "O" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                                    thang = true;
                                    //clickNumber = 0;

                                    return;
                                }
                            }
                        }
//                        System.out.println("dem1  = "+dem1);
//                        System.out.println("dem2  = "+dem2);
                    }
                }
            }

        }
    }

    public void ngangThang() {
        for (int i = 0; i < ROW - 1; i++) {
            for (int j = 0; j < COL - 1; j++) {
                if (mt[i][j] == mt[i][j + 1] && mt[i][j] == 1) {
                    dem1++;
                } else if (mt[i][j] == mt[i][j + 1] && mt[i][j] == 2) {
                    dem2++;
                } else {
                    dem1 = 0;
                    dem2 = 0;
                }
                if (dem1 == 4) {
                    System.out.println("X thắng!");
                    JOptionPane.showMessageDialog(null, "Quân " + "X" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                    dem1 = 0;
                    dem2 = 0;
                    thang = true;
                    //clickNumber = 1;
                    return;
                }
                if (dem2 == 4) {
                    System.out.println("O thang ");
                    JOptionPane.showMessageDialog(null, "Quân " + "O" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                    dem1 = 0;
                    dem2 = 0;

                    thang = true;
                    //clickNumber = 0;

                    return;
                }
            }
        }
//        System.out.println("dem  = " + dem1);
//        System.out.println("dem  = " + dem2);
        //showMatrix();
    }
    public void docThang(){
        for(int i = 0; i < ROW - 1; i++){
            for(int j = 0; j < COL - 1; j++){
                if(mt[j][i] == mt[j+1][i] && mt[j][i] == 1){
                    dem1++;
                }
                else if(mt[j][i] == mt[j+1][i] && mt[j][i] == 2){
                    dem2++;
                }
                else{
                    dem1 = 0;
                    dem2 = 0;
                }
                if(dem1 == 4){
                    System.out.println("X thang ");

                    thang = true;
                    JOptionPane.showMessageDialog(null, "Quân " + "X" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                    //clickNumber = 1;
                    return;

                }
                if(dem2 == 4){
                    System.out.println("O thang ");

                    thang = true;

                    JOptionPane.showMessageDialog(null, "Quân " + "O" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                    //clickNumber = 0;
                    return;
                }
            }
        }
//        System.out.println("dem  = "+dem1);
//        System.out.println("dem  = "+dem2);
        //showMatrix();
    }
    public void cheoThang() {
        int dem = 1;
        for(int i = 0; i < ROW -1; i++){
            for(int j = 0;  j < COL -1 ; j++){
                dem = 1;
                for(int k = 1; k <= 5; k++) {
                    if(j + k < COL  && i + k < ROW ){
                        if (mt[i + k][j + k] == mt[i][j]) {
                            dem++;
                        }
                        else{
                            break;
                        }
                        if(dem == 5){
                            if(mt[i][j] == 1){
                                dem1 = 4;
                                if(dem1 == 4){
                                    System.out.println("X thang ");
                                    JOptionPane.showMessageDialog(null, "Quân " + "X" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                                    thang = true;
                                    //clickNumber = 1;
                                    return;
                                }
                            }
                            if(mt[i][j] == 2){
                                dem2 = 4;
                                if(dem2 == 4){
                                    System.out.println("O thang ");
                                    JOptionPane.showMessageDialog(null, "Quân " + "O" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                                    thang = true;
                                    //clickNumber = 0;
                                    return;
                                }
                            }
                        }
//                        System.out.println("dem1  = "+dem1);
//                        System.out.println("dem2  = "+dem2);
                    }
                }
            }

        }
    }
    public void cheo2Thang() {
        int dem = 1;
        for(int i = 0; i < ROW -1; i++){
            for(int j = 0;  j < COL -1 ; j++){
                dem = 1;
                for(int k = 1; k <= 5; k++) {
                    if(j + k < COL && i - k > 0 ){
                        if (mt[i - k][j + k] == mt[i][j]) {
                            dem++;
                        }
                        else{
                            break;
                        }
                        if(dem == 5){
                            if(mt[i][j] == 1){
                                dem1 = 4;
                                if(dem1 == 4){
                                    System.out.println("X thang ");
                                    JOptionPane.showMessageDialog(null, "Quân " + "X" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                                    thang = true;
                                    //clickNumber = 1;
                                    return;
                                }
                            }
                            if(mt[i][j] == 2){
                                dem2 = 4;
                                if(dem2 == 4){
                                    System.out.println("O thang ");
                                    JOptionPane.showMessageDialog(null, "Quân " + "O" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                                    thang = true;
                                    //clickNumber = 0;

                                    return;
                                }
                            }
                        }
//                        System.out.println("dem1  = "+dem1);
//                        System.out.println("dem2  = "+dem2);
                    }
                }
            }

        }
    }

    public void nguoiChoiClick(int x, int y) {
        System.out.println("click toa do "+x/ Square.SIZE+" - "+y/ Square.SIZE);

        if(x > (ROW )*Square.SIZE || y > (COL )*Square.SIZE || x < Square.SIZE || y < Square.SIZE){
            return;
        }
        else{
            int n = (x  / Square.SIZE);
            int m = (y / Square.SIZE );
            if (mt[n][m] > 0)
                return;
            int x1 = n * Square.SIZE ;
            int y1 = m * Square.SIZE ;
            Item xo = new Item(x1, y1, 0,30);
            items.add(xo);
            mt[n][m] = 2;
            System.out.println("number = "+clickNumber);
            clickNumber++;
            demX++;
            System.out.println("number1 = "+clickNumber);
            System.out.println("=========================");
            showMatrix();
        }
    }
    public void nguoiChoiXClick(int x, int y) {
        System.out.println("click toa do "+x/ Square.SIZE+" - "+y/ Square.SIZE);
        if(x > (ROW )*Square.SIZE || y > (COL )*Square.SIZE || x < Square.SIZE || y < Square.SIZE){
            return;
        }
        else{
            int n = (x  / Square.SIZE);
            int m = (y / Square.SIZE );
            if (mt[n][m] > 0)
                return;
            int x1 = n * Square.SIZE ;
            int y1 = m * Square.SIZE ;
            Item xo = new Item(x1, y1, 1,30);
            items.add(xo);
            mt[n][m] = 1;
            System.out.println("number = "+clickNumber);
            clickNumber++;
            demX++;
            System.out.println("number1 = "+clickNumber);
            System.out.println("=========================");
            showMatrix();
        }
    }




}
