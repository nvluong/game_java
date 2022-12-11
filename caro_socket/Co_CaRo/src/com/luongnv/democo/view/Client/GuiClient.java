package com.luongnv.democo.view.Client;

import com.luongnv.democo.model.Item;
import com.luongnv.democo.model.Square;
import com.luongnv.democo.view.OnScreenSwitchListener;
import com.luongnv.democo.view.Setup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class GuiClient extends JFrame implements OnScreenSwitchListener, Setup{
    public static final int WIDTH_FRAME = 910;
    public static final int HEIGHT_FRAME = 750;
    private GameClient gameClient;
    Thread thread = new Thread();

    public static final String SCREEN_GAME_PLAY = "screen_game_play";

    Socket socketGame = null;
    ObjectInputStream inGame = null;
    ObjectOutputStream outGame = null;
    String host;


    public GuiClient() {
        initializeContainer();
        initializeComponents();
        registerListeners();
        khoitao();
    }
    public void khoitao(){
        host = JOptionPane.showInputDialog("Nhập host", "");
        class ListenGame extends Thread {

            public ListenGame() {
                start();
            }
            @Override
            public void run() {
                listenSocketGame();
            }
        }
        new ListenGame();
    }
    public void listenSocketGame() {
        try {
            socketGame = new Socket(host, 1005);
            OutputStream o = socketGame.getOutputStream();
            outGame = new ObjectOutputStream(o);
            InputStream i = socketGame.getInputStream();
            inGame = new ObjectInputStream(i);
        } catch (UnknownHostException e) {
            System.err.println("Server không tồn tại");
        } catch (IOException e) {
            System.err.println("Server không mở");
        }
        while (true) {
            try {
                Point s ;
                try {
                    s = (Point) inGame.readObject();
                    int x = (int) s.getX();
                    int y = (int) s.getY();

                    gameClient.gameManager.nguoiChoiXClick(x,y);
                    gameClient.repaint();
                    gameClient.gameManager.ngangOThang();
                    gameClient.gameManager.docOThang();
                    gameClient.gameManager.cheoOThang();
                    gameClient.gameManager.cheo2OThang();

                    if (gameClient.gameManager.thang == true) {//quân đỏ thắng
                        gameClient.gameManager.newGameO();
                        gameClient.repaint();
                        gameClient.gameManager.thang = false;
                        gameClient.gameManager.clickNumber ++;
                    }

                } catch (ClassNotFoundException ex) {

                }


            } catch (IOException ex) {
            }
        }

    }


    @Override
    public void initializeContainer() {
        setTitle("GAME CARO");
        setLayout(new CardLayout());
        setLayout(new CardLayout());
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setResizable(false);
        setLocationRelativeTo(null);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res1/drawable/ca.png")));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void initializeComponents() {

        gameClient = new GameClient();
        gameClient.setOnScreenSwitchListener(this);
        add(gameClient);

    }

    @Override
    public void registerListeners() {


        MouseAdapter adapter = new MouseAdapter() {

            @Override

            public void mouseClicked(MouseEvent e) {
                if(gameClient.gameManager.clickNumber %2 !=0){
                    gameClient.gameManager.nguoiChoiClick(e.getX(),e.getY() - Square.SIZE);
                    gameClient.repaint();

                    Point point1;
                    point1 = new Point(e.getX(), e.getY()-Square.SIZE);

                    try{
                        outGame.writeObject(point1);
                    }catch (Exception ea){

                    }


                    gameClient.gameManager.ngangThang();
                    gameClient.gameManager.docThang();
                    gameClient.gameManager.cheoThang();
                    gameClient.gameManager.cheo2Thang();
                }

                if(gameClient.gameManager.thang == true){

                    gameClient.gameManager.newGameO();
                    gameClient.repaint();
                    gameClient.gameManager.thang = false;
                }
            }
        };

        addMouseListener(adapter);
    }

    @Override
    public void switchScreen(String name) {
        switch (name){

            case SCREEN_GAME_PLAY:
                if(gameClient == null){
                    gameClient = new GameClient();
                }
                add(gameClient);
                revalidate(); // ve lai giao dien
                gameClient.focus();
                break;
        }
    }
}
