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

    private Item item;
    private Square square;
    private ArrayList<Square> squares;
    private ArrayList<Item> items;
    private ArrayList<Item> items1;
    private int luotDi;
    private boolean sanSang;
    private Stack<Square> stkCacNuocDaDi;

    private int [][] mt;
    public int clickNumber = 0;
    public boolean thang = false;
    private int dem1 = 0;
    private int dem2 = 0;

    public int demX = 0;
    public int demO = 0;

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

    public void clickOne(int x, int y) {
        /*lấy ra tọa độ ma trận vừa click*/
        int m = y / Square.SIZE;
        int n = x / Square.SIZE;
        /*Kiểm tra nếu vị trí đó lớn hơn 0 có nghĩa đã vẽ nên quay lại*/
        if (mt[m][n] > 0) return;

        int xXO = n * Square.SIZE ;
        int yXO = m * Square.SIZE ;
        Item xo = new Item(xXO, yXO, 1,30);
        items.add(xo);
        mt[m][n] = 1;
        clickNumber++;
        System.out.println("===========================================");
        showMatrix();
        System.out.println("===========================================");
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
        graphics2D.drawImage(ImageStore.IMG_Anh3,650,20,220,220,null);
        graphics2D.drawImage(ImageStore.IMG_ANH_2,650,100+205-50,220,220,null);

    }

    // chech thang thua
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
                    return;
                }
                if (dem2 == 4) {
                    System.out.println("O thang ");
                    JOptionPane.showMessageDialog(null, "Quân " + "O" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                    dem1 = 0;
                    dem2 = 0;
                    thang = true;
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
                    JOptionPane.showMessageDialog(null, "Quân " + "X" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                    thang = true;
                    return;
                }
                if(dem2 == 4){
                    System.out.println("O thang ");
                    JOptionPane.showMessageDialog(null, "Quân " + "O" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                    thang = true;
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
                                    return;
                                }
                            }
                            if(mt[i][j] == 2){
                                dem2 = 4;
                                if(dem2 == 4){
                                    System.out.println("O thang ");
                                    JOptionPane.showMessageDialog(null, "Quân " + "O" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                                    thang = true;
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
                                    return;
                                }
                            }
                            if(mt[i][j] == 2){
                                dem2 = 4;
                                if(dem2 == 4){
                                    System.out.println("O thang ");
                                    JOptionPane.showMessageDialog(null, "Quân " + "O" + " thắng!", "Thông báo", JOptionPane.OK_OPTION);
                                    thang = true;
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

    // con AI

    //để đánh giá nước đi có thông minh hay ko cần mảng tính điểm
    private long[] MangDiemTanCong = {0,9,54,162,1458,13112,118008};
    private long[] MangDiemPhongNgu = {0,3,27,99,729,6561,59049};
    // số quân dựa vào vị trí mảng , nếu số quân đánh là 1 thì lấy mảng điểm tấn công là 1 ra

    // hàm khởi động cho computer
    public int kiemTraMT(){
        for(int i = 0; i < ROW ; i++){
            for(int j = 0; j < COL; j++){
                if(mt[i][j] != 0){
                    System.out.println("vi tri # 0"+i+"-"+j);
                    return 0;
                }
            }
        }
        return 1;
    }
    public void khoiDongComputer(){
        if(kiemTraMT() == 1){
            System.out.println(" vao khoi tao mac dinh ");
            Random random = new Random();
            int a = 5 + random.nextInt(ROW/2);
            int b = 6 + random.nextInt(ROW/2);
            //item = new Item(ROW / 2 * Square.SIZE  ,COL /2 * Square.SIZE  ,1,30);
            Item item2 = new Item(a * Square.SIZE  ,b * Square.SIZE  ,1,30);
            items1.add(item2);
            mt[a][b] = 1;
            clickNumber++;
            System.out.println("number =" + clickNumber);

            System.out.println("=========================");
            showMatrix();
            return;
        }
        if(kiemTraMT() == 0) {
            DanhCo();
            System.out.println("=========================");
            showMatrix();
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
    public void DanhCo(){
        //System.out.println(" vao danh co cua may  ");
        Square square1 = TimKiemNuocDi();
       // System.out.println("square1 x = "+square1.getX() / 30 +" y = "+square1.getY() / 30);
        Item item1 = new Item(square1.getX(),square1.getY(),1,30);
        items1.add(item1);
        int n = square1.getX() / 30;
        //System.out.println("n = "+n);
        int m = square1.getY() / 30;
        //System.out.println("m = "+m);
        if(mt[n][m] > 0){
           return;
        }
        demX++;
        mt[n][m] = 1;
        clickNumber++;

    }

    public Square TimKiemNuocDi(){
        System.out.println("fa vao tim kiem nuoc di ");
        Square square2 = new Square();
        long DiemMax = 0;
        for(int i = 0; i < ROW ; i++){
            for(int j = 0; j < COL ; j++){
                if(mt[i][j] == 0 && i > 0 && j > 0){
                    long DiemTanCong = DiemTanCongDoc(i,j)+DiemTanCongNgang(i,j)+DiemTanCongCheoNguoc(i,j)+DiemTanCongCheoXuoi(i,j);
                    long DiemPhongNgu = DiemPhongNguDoc(i,j)+DiemPhongNguNgang(i,j)+DiemPhongNguCheoNguoc(i,j)+DiemPhongNguCheoXuoi(i,j);
                    long DiemTam = DiemTanCong > DiemPhongNgu ? DiemTanCong : DiemPhongNgu;
                    if(DiemMax < DiemTam){
                        DiemMax = DiemTam;
                        square2 = new Square(i*Square.SIZE,j*Square.SIZE,true);
                        System.out.println("vi tri may du dinh danh la ["+i+"]["+j+"]");
                    }
                }
            }
        }
        return square2;
    }
   // tan cong
    private long DiemTanCongDoc(int x, int y){
        long DiemTong = 0;
        int soQuanTa = 0;
        int soQuanDich = 0;
        // duyet tu tren xuong
        for(int dem = 1; dem < 6 && x + dem <= ROW - 1; dem++) {
            if (mt[x + dem][y] == 1) {
                soQuanTa++;
            } else if (mt[x + dem][y] == 2) {
                soQuanDich++;
                break;
            } else {
                break;
            }
        }
            // duyet tu duoi len
        for(int dem = 1; dem < 6 && x - dem >= 0 ; dem++){
            if(mt[x - dem][y] == 1){
                    soQuanTa ++;
            }
            else if(mt[x - dem][y] == 2){
                soQuanDich++;
                break;
            }
            else {
                break;
            }
        }
        if(soQuanDich == 2)   return  0; // chan 2 dau thi thoat ko nghien cuu nuoc danh o do nua
        DiemTong -= MangDiemPhongNgu[soQuanDich + 1]*2;
        DiemTong += MangDiemTanCong[soQuanTa];
        return DiemTong;
    }
    private long DiemTanCongNgang(int x, int y){
        long DiemTong = 0;
        int soQuanTa = 0;
        int soQuanDich = 0;
        // duyet tu tren xuong
        for(int dem = 1; dem < 6 && y + dem <= COL - 1; dem++) {
            if (mt[x][y + dem] == 1) {
                soQuanTa++;
            } else if (mt[x][y + dem] == 2) {
                soQuanDich++;
                break;
            } else {
                break;
            }
        }
        // duyet tu duoi len
        for(int dem = 1; dem < 6 && y - dem >= 0 ; dem++){
            if(mt[x][y - dem] == 1){
                soQuanTa ++;
            }
            else if(mt[x][y - dem] == 2){
                soQuanDich++;
                break;
            }
            else {
                break;
            }
        }
        if(soQuanDich == 2)   return  0; // chan 2 dau thi thoat ko nghien cuu nuoc danh o do nua
        DiemTong -= MangDiemPhongNgu[soQuanDich + 1]*2;
        DiemTong += MangDiemTanCong[soQuanTa];
        return DiemTong;
    }
    private long DiemTanCongCheoNguoc(int x, int y){
        long DiemTong = 0;
        int soQuanTa = 0;
        int soQuanDich = 0;
        // duyet tu duoi len
        for(int dem = 1; dem < 6 && y + dem <= COL - 1 && x - dem >= 0 ; dem++) {
            if (mt[x - dem][y + dem] == 1) {
                soQuanTa++;
            } else if (mt[x - dem][y + dem] == 2) {
                soQuanDich++;
                break;
            } else {
                break;
            }
        }
        // duyet tu tren xuong
        for(int dem = 1; dem < 6 && y - dem >= 0 && x + dem <= ROW  - 1; dem++){
            if(mt[x + dem][y - dem] == 1){
                soQuanTa ++;
            }
            else if(mt[x + dem][y - dem] == 2){
                soQuanDich++;
                break;
            }
            else {
                break;
            }
        }
        if(soQuanDich == 2)   return  0; // chan 2 dau thi thoat ko nghien cuu nuoc danh o do nua
        DiemTong -= MangDiemPhongNgu[soQuanDich + 1]*2;
        DiemTong += MangDiemTanCong[soQuanTa];
        return DiemTong;
    }
    private long DiemTanCongCheoXuoi(int x, int y){
        long DiemTong = 0;
        int soQuanTa = 0;
        int soQuanDich = 0;
        // duyet tu tren xuong
        for(int dem = 1; dem < 6 && y + dem <= COL -1 && x + dem <= ROW -1; dem++) {
            if (mt[x + dem][y + dem] == 1) {
                soQuanTa++;
            } else if (mt[x + dem][y + dem] == 2) {
                soQuanDich++;
                break;
            } else {
                break;
            }
        }
        // duyet tu duoi len
        for(int dem = 1; dem < 6 && y - dem >= 0 && x - dem >= 0 ; dem++){
            if(mt[x - dem][y - dem] == 1){
                soQuanTa ++;
            }
            else if(mt[x - dem][y - dem] == 2){
                soQuanDich++;
                break;
            }
            else {
                break;
            }
        }
        if(soQuanDich == 2)   return  0; // chan 2 dau thi thoat ko nghien cuu nuoc danh o do nua
        DiemTong -= MangDiemPhongNgu[soQuanDich + 1]*2;
        DiemTong += MangDiemTanCong[soQuanTa];
        return DiemTong;
    }


    // phong ngu
    private long DiemPhongNguDoc(int x, int y){
        long DiemTong = 0;
        int soQuanTa = 0;
        int soQuanDich = 0;
        // duyet tu tren xuong
        for(int dem = 1; dem < 6 && x + dem <= ROW - 1; dem++) {
            if (mt[x + dem][y] == 1) {
                soQuanTa++;
                break;
            } else if (mt[x + dem][y] == 2) {
                soQuanDich++;
            } else {
                break;
            }
        }
        // duyet tu duoi len
        for(int dem = 1; dem < 6 && x - dem >= 0 ; dem++){
            if(mt[x - dem][y] == 1){
                soQuanTa ++;
                break;
            }
            else if(mt[x - dem][y] == 2){
                soQuanDich++;
            }
            else {
                break;
            }
        }
        if(soQuanTa == 2)   return  0; // co 2 quan cua ta da chan dau
        DiemTong += MangDiemPhongNgu[soQuanDich];
        return DiemTong;
    }
    private long DiemPhongNguNgang(int x, int y){
        long DiemTong = 0;
        int soQuanTa = 0;
        int soQuanDich = 0;
        // duyet tu tren xuong
        for(int dem = 1; dem < 6 && y + dem <= COL - 1; dem++) {
            if (mt[x][y + dem] == 1) {
                soQuanTa++;
                break;
            } else if (mt[x][y + dem] == 2) {
                soQuanDich++;
            } else {
                break;
            }
        }
        // duyet tu duoi len
        for(int dem = 1; dem < 6 && y - dem >= 0 ; dem++){
            if(mt[x][y - dem] == 1){
                soQuanTa ++;
                break;
            }
            else if(mt[x][y - dem] == 2){
                soQuanDich++;
            }
            else {
                break;
            }
        }
        if(soQuanTa == 2)   return  0; // chan 2 dau thi thoat ko nghien cuu nuoc danh o do nua
        DiemTong += MangDiemPhongNgu[soQuanDich];
        return DiemTong;
    }
    private long DiemPhongNguCheoNguoc(int x, int y){
        long DiemTong = 0;
        int soQuanTa = 0;
        int soQuanDich = 0;
        // duyet tu duoi len
        for(int dem = 1; dem < 6 && y + dem <= COL - 1 && x - dem >= 0 ; dem++) {
            if (mt[x - dem][y + dem] == 1) {
                soQuanTa++;
                break;
            } else if (mt[x - dem][y + dem] == 2) {
                soQuanDich++;
            } else {
                break;
            }
        }
        // duyet tu tren xuong
        for(int dem = 1; dem < 6 && y - dem >= 0 && x + dem <= ROW  - 1; dem++){
            if(mt[x + dem][y - dem] == 1){
                soQuanTa ++;
                break;
            }
            else if(mt[x + dem][y - dem] == 2){
                soQuanDich++;
            }
            else {
                break;
            }
        }
        if(soQuanTa == 2)   return  0; // chan 2 dau thi thoat ko nghien cuu nuoc danh o do nua
        DiemTong += MangDiemPhongNgu[soQuanDich];
        return DiemTong;
    }
    private long DiemPhongNguCheoXuoi(int x, int y){
        long DiemTong = 0;
        int soQuanTa = 0;
        int soQuanDich = 0;
        // duyet tu tren xuong
        for(int dem = 1; dem < 6 && y + dem <= COL - 1 && x + dem <= ROW  - 1; dem++) {
            if (mt[x + dem][y + dem] == 1) {
                soQuanTa++;
                break;
            } else if (mt[x + dem][y + dem] == 2) {
                soQuanDich++;
            } else {
                break;
            }
        }
        // duyet tu duoi len
        for(int dem = 1; dem < 6 && y - dem >= 0 && x - dem >= 0 ; dem++){
            if(mt[x - dem][y - dem] == 1){
                soQuanTa ++;
                break;
            }
            else if(mt[x - dem][y - dem] == 2){
                soQuanDich++;
            }
            else {
                break;
            }
        }
        if(soQuanTa == 2)   return  0; // chan 2 dau thi thoat ko nghien cuu nuoc danh o do nua
        DiemTong += MangDiemPhongNgu[soQuanDich];
        return DiemTong;
    }



}
