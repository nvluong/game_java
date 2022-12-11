package com.luongnv.democo.view;

import com.luongnv.democo.control.ISound;
import com.luongnv.democo.control.ImageStore;
import com.luongnv.democo.control.SoundWav;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame implements OnScreenSwitchListener, Setup{
    public static final int WIDTH_FRAME = 910;
    public static final int HEIGHT_FRAME = 750;
    private MenuPanel menuPanel;
    private GamePanel  gamePanel;
    //
    //tên cac cái trong menu cần chuyển
    public static final String SCREEN_MENU= "screen_menu";
    public static final String SCREEN_GAME_PLAY = "screen_game_play";
    public static final String SCREEN_HIGH_SCORE = "screen_high_score";

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
       // add(new GamePanel());
        menuPanel = new MenuPanel();
        menuPanel.setOnScreenSwitchListener(this);
        add(menuPanel);

        gamePanel = new GamePanel();
        gamePanel.setOnScreenSwitchListener(this);
        add(gamePanel);

        ISound sound = new SoundWav("BG_Map1");
        sound.play();
    }

    @Override
    public void registerListeners() {
    }

    @Override
    public void switchScreen(String name) {
        switch (name){
            case SCREEN_MENU:
                remove(gamePanel);
                if(menuPanel == null){
                    menuPanel = new MenuPanel();
                }
                add(menuPanel);
                revalidate(); // ve lai giao dien
                menuPanel.focus();
                break;
            case SCREEN_GAME_PLAY:
                // menuPanel.setVisible(false);
                //or
                remove(menuPanel);
                if(gamePanel == null){
                    gamePanel = new GamePanel();
                }
                add(gamePanel);
                revalidate(); // ve lai giao dien
                gamePanel.focus();
                break;
            case SCREEN_HIGH_SCORE:
                break;
        }
    }
}
