
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controllers;

import models.Fire;
import models.FirePalette;
import views.ControlPanel;
import views.Viewer;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author NitroPC
 */
public class MyTask extends JFrame {

    private Fire f;
    private volatile boolean myTaskMontada = false;
    private Viewer v;
    private FireController fc;
    private ControlPanel cp;
    private FirePalette firePalette;
    private FireController fireController;
    private Dimension resolution;
    public Color bgColor;
    GridBagConstraints constraints = new GridBagConstraints();


    public MyTask() {
        super("Foquet");

        if (!myTaskMontada) {

            montarMyTask();
            montarViewer();
            montarControlPanel();

            this.setVisible(true);
            this.getContentPane().setBackground(Color.PINK);
            myTaskMontada = true;

        } else {

            System.out.println("asdasd");
            this.getContentPane().setBackground(Color.getColor(String.valueOf(bgColor)));
            this.setVisible(false);
            this.setVisible(true);
        }
    }

    private void montarMyTask() {
        this.setLayout(new GridBagLayout());
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Fire getF() {
        return f;
    }

    private void montarViewer() {

        firePalette = new FirePalette(new Color(255,255,0,255),
                new Color(255,180,0,255),
                new Color(255,145,0,255),
                new Color(255,100,0,255),
                new Color(255,0,0,255),
                new Color(0,0,0,255));

        f = new Fire(500, 500, firePalette);
        v = new Viewer(f);
        changeGridPos(constraints, 0, 0);
        this.add(v, constraints);
    }

    private void montarControlPanel() {
        cp = new ControlPanel(this);
        cp.setBackground(Color.green);
        cp.setBorder(BorderFactory.createLineBorder(Color.black));
        changeGridPos(constraints, 1, 0);
        this.add(cp, constraints);
    }

    private void setBgColor(Color color) {
        this.bgColor = color;
//        this.getContentPane().setBackground(Color.getColor(String.valueOf(bgColor)));
        this.setVisible(false);
        this.setVisible(true);
    }

    private Color getBgColor() {
        return bgColor;
    }



    //Obra mestra
    private void changeGridDim(GridBagConstraints constraints, int x, int y) {
        constraints.gridwidth = x;
        constraints.gridheight = y;
    }

    private void changeGridPos(GridBagConstraints constraints, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
    }


    public static void main(String[] args) {

        new MyTask();

    }
}
