package com.luongnv.democo.view;

import com.luongnv.democo.control.GameManager;
import com.luongnv.democo.control.ISound;
import com.luongnv.democo.control.ImageStore;
import com.luongnv.democo.control.SoundWav;
import com.luongnv.democo.model.Item;
import com.luongnv.democo.model.Square;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.BitSet;

public class GamePanel extends BasePanel {
    private GameManager gameManager;
    private BitSet bitSet;
    private Square square;

    private OnScreenSwitchListener onScreenSwitchListener;
    private JLabel lbCaro;
    public Graphics g2d;
    private JButton btNewGame;
    private JButton btCom;
    private JButton btthoat;
    private JButton btn;

    private Thread thread;

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
        bitSet = new BitSet();
        btn = new JButton(new ImageIcon(ImageStore.IMG_CLOSE1));
        btn.setBounds(700, 490, 118, 56);
        add(btn);

        btNewGame = new JButton(new ImageIcon(ImageStore.IMG_NEWGAME));
        btNewGame.setBounds(700, 490 + 60, 117, 50);
        add(btNewGame);

        btthoat = new JButton(new ImageIcon(ImageStore.IMG_EXIT1));
        btthoat.setBounds(700, 490 + 60 + 54, 119, 46);
        add(btthoat);

        ISound sound = new SoundWav("BG_Map1");

        sound.loop(Clip.LOOP_CONTINUOUSLY);
        sound.play();

    }


    @Override
    public void registerListeners() {

        gameManager.khoiDongComputer();
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
                            gameManager.khoiDongComputer();
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
                            gameManager.khoiDongComputer();
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


        btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent e){
                    onScreenSwitchListener.switchScreen(Gui.SCREEN_MENU);
                }
            });

        btNewGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent e){
                    gameManager.newGame();
                    gameManager.khoiDongComputer();
                    repaint();
                }
            });

        btthoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                System.exit(0);
            }
        });
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
