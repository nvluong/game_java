package com.luongnv.democo.view;

import com.luongnv.democo.control.GameManager;
import com.luongnv.democo.control.ImageStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuPanel  extends BasePanel{
    private JButton btn;
    private OnScreenSwitchListener onScreenSwitchListener;
    private GameManager gameManager;

    public void focus(){
        setFocusable(true);
        requestFocusInWindow();
    }
    @Override
    public void initializeContainer() {
        setLayout(null);
        setBackground(Color.GREEN);
    }

    @Override
    public void initializeComponents() {
        btn = new JButton(new ImageIcon(ImageStore.IMG_PLAY23));
        btn.setBounds(400,200,100,46);
        add(btn);
    }

    @Override
    public void registerListeners() {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                onScreenSwitchListener.switchScreen(Gui.SCREEN_GAME_PLAY);
            }
        });
    }
    public void setOnScreenSwitchListener(OnScreenSwitchListener onScreenSwitchListener) {
        this.onScreenSwitchListener = onScreenSwitchListener;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(ImageStore.IMG_PLAY23456, 0,0, Gui.WIDTH_FRAME, Gui.HEIGHT_FRAME, null);
    }

}
