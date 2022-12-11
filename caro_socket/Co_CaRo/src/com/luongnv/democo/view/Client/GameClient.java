package com.luongnv.democo.view.Client;

import com.luongnv.democo.control.GameManager;
import com.luongnv.democo.view.BasePanel;
import com.luongnv.democo.view.OnScreenSwitchListener;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameClient extends BasePanel {
    public GameManager gameManager;

    private OnScreenSwitchListener onScreenSwitchListener;


    // cai nay de cho phim thi no van di chuyen dc
    public void focus() {
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    public void initializeContainer() {
        setLayout(null);
        setBackground(Color.WHITE);
        setFocusable(true);

    }


    @Override
    public void initializeComponents() {
        gameManager = new GameManager();

    }


    @Override
    public void registerListeners() {
        }



    public void setOnScreenSwitchListener(OnScreenSwitchListener onScreenSwitchListener) {
        this.onScreenSwitchListener = onScreenSwitchListener;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        gameManager.drawBG(graphics2D);
        gameManager.drawMap(graphics2D);
        gameManager.drawAnh(graphics2D);
        gameManager.drawItem1(graphics2D);
        gameManager.drawItem(graphics2D);
    }


}
