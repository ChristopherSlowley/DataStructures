package com.datastructures.gui;

import javax.swing.*;

public class MainClass {
    public static void main(String[] args) {


        JFrame mainFrame = new JFrame("Data Structures");
        mainFrame.setContentPane(new MainPanel().getMainPanel_pnl());
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(600,400);
        //mainFrame.pack();
        mainFrame.setVisible(true);
    }

}
