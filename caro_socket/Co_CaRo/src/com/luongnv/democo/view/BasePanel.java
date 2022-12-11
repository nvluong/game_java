package com.luongnv.democo.view;

import javax.swing.*;

public abstract class BasePanel extends JPanel implements Setup{
    public BasePanel() {
        initializeContainer();
        initializeComponents();
        registerListeners();
    }
}
