package com.luongnv.democo.view.Server;

import com.luongnv.democo.model.Square;
import com.luongnv.democo.view.OnScreenSwitchListener;
import com.luongnv.democo.view.Setup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class GuiServer extends JFrame implements OnScreenSwitchListener, Setup{
    public static final int WIDTH_FRAME = 910;
    public static final int HEIGHT_FRAME = 750;
    private GameServer gameServer;
    Thread thread = new Thread();

    public static final String SCREEN_GAME_PLAY = "screen_game_play";

    ServerSocket serverGame = null;
    ObjectInputStream inGame = null;
    ObjectOutputStream outGame = null;

    Point point = new Point();



    public GuiServer() {
        initializeContainer();
        initializeComponents();
        registerListeners();
        khoitao();
    }
    public void khoitao(){
        class ListenGame extends Thread {

            public ListenGame() {
                start();
            }

            @Override
            public void run() {
                listenSocketGamne();
            }
        }
        new ListenGame();
    }
    public void listenSocketGamne() {

        try {
            serverGame = new ServerSocket(1005);
            Socket socket = serverGame.accept();
            OutputStream o = socket.getOutputStream();
            outGame = new ObjectOutputStream(o);
            InputStream i = socket.getInputStream();
            inGame = new ObjectInputStream(i);
        } catch (IOException e) {
            System.out.println("Could not listen on port 4444");
            System.exit(-1);
        }
        while (true) {
            try {
                Point s = null;
                try {
                    s = (Point) inGame.readObject();
                    int x = (int) s.getX();
                    int y = (int) s.getY();

                    gameServer.gameManager.nguoiChoiClick(x,y);
                    gameServer.repaint();
                    gameServer.gameManager.ngangThang();
                    gameServer.gameManager.docThang();
                    gameServer.gameManager.cheoThang();
                    gameServer.gameManager.cheo2Thang();

                    if (gameServer.gameManager.thang == true) {//quân đỏ thắng

                        gameServer.gameManager.newGame();
                        gameServer.repaint();
                        gameServer.gameManager.thang = false;

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

        gameServer = new GameServer();
        gameServer.setOnScreenSwitchListener(this);
        add(gameServer);


    }

    @Override
    public void registerListeners() {




        MouseAdapter adapter = new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(gameServer.gameManager.clickNumber %2 == 0){
                    gameServer.gameManager.nguoiChoiXClick(e.getX(),e.getY() - Square.SIZE);
                    gameServer.repaint();

                    Point point1;
                    point1 = new Point(e.getX(), e.getY()-Square.SIZE);

                    try{
                        outGame.writeObject(point1);
                    }catch (Exception ea){

                    }
                    gameServer.gameManager.ngangThang();
                    gameServer.gameManager.docThang();
                    gameServer.gameManager.cheoThang();
                    gameServer.gameManager.cheo2Thang();
                }
                if(gameServer.gameManager.thang == true){
                    gameServer.gameManager.newGame();
                    gameServer.repaint();
                    gameServer.gameManager.thang = false;
                }

            }
        };

        addMouseListener(adapter);




    }

    @Override
    public void switchScreen(String name) {
        switch (name){

            case SCREEN_GAME_PLAY:
                if(gameServer == null){
                    gameServer = new GameServer();
                }
                add(gameServer);
                revalidate(); // ve lai giao dien
                gameServer.focus();
                break;
        }
    }
}
