package com.luongnv.democo.view;

import com.luongnv.democo.control.GameManager;

import java.awt.*;
import java.awt.event.*;

public class GamePanel extends BasePanel {
    private GameManager gameManager;

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

            MouseAdapter adapter = new MouseAdapter() {
                @Override

                public void mouseClicked(MouseEvent e) {
                    if (gameManager.thang == false) {
                        if (gameManager.clickNumber % 2 != 0) {
                            gameManager.nguoiChoiClick(e.getX(), e.getY());
                            gameManager.ngangThang();
                            gameManager.docThang();
                            gameManager.cheoThang();
                            gameManager.cheo2Thang();
                            repaint();
                        }
                        if (gameManager.clickNumber % 2 == 0) {
                            gameManager.nguoiChoiClick(e.getX(), e.getY());
                            gameManager.ngangThang();
                            gameManager.docThang();
                            gameManager.cheoThang();
                            gameManager.cheo2Thang();
                            repaint();
                        }
                    }
                    if (gameManager.thang == true) {
                        gameManager.newGame();
                        if (gameManager.clickNumber % 2 != 0) {
                            gameManager.nguoiChoiClick(e.getX(), e.getY());
                            gameManager.ngangThang();
                            gameManager.docThang();
                            gameManager.cheoThang();
                            gameManager.cheo2Thang();
                            repaint();
                        }
                        if (gameManager.clickNumber % 2 == 0) {
                            gameManager.nguoiChoiClick(e.getX(), e.getY());
                            gameManager.ngangThang();
                            gameManager.docThang();
                            gameManager.cheoThang();
                            gameManager.cheo2Thang();
                            repaint();
                        }
                        gameManager.thang = false;
                    }
                }
            };

            addMouseListener(adapter);



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
