package com.luongnv.democo.view;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame implements OnScreenSwitchListener, Setup{
    public static final int WIDTH_FRAME = 910;
    public static final int HEIGHT_FRAME = 750;
    private GamePanel  gamePanel;

    public static final String SCREEN_GAME_PLAY = "screen_game_play";


    public Gui() {
        initializeContainer();
        initializeComponents();
        registerListeners();
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

        gamePanel = new GamePanel();
        gamePanel.setOnScreenSwitchListener(this);
        add(gamePanel);


    }

    @Override
    public void registerListeners() {
    }

    @Override
    public void switchScreen(String name) {
        switch (name){

            case SCREEN_GAME_PLAY:
                if(gamePanel == null){
                    gamePanel = new GamePanel();
                }
                add(gamePanel);
                revalidate(); // ve lai giao dien
                gamePanel.focus();
                break;
        }
    }
}
