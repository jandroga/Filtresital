package com.mycompany.convotest;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MyTask extends JFrame {

    private ImageToConvolve imageToConvolve;

    public MyTask(){
        super("Imatge test");

        imageToConvolve = new ImageToConvolve(500, 500, BufferedImage.TYPE_INT_ARGB);
        BufferedImage image = imageToConvolve.loadImage();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JLabel label = new JLabel(new ImageIcon(image));
        getContentPane().add(label);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MyTask();
    }
}