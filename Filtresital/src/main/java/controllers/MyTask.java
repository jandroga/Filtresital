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
public class MyTask extends JFrame{

    /**
     * @param args the command line arguments
     */
    private Viewer v;
    private ControlPanel cp;
    private FirePalette firePalette;
    private Dimension resolution;
    private Color backGroundColor;
    GridBagConstraints constraints = new GridBagConstraints();


    public MyTask() {
        super("Foquet");
        firePalette = new FirePalette();
        Fire f = new Fire(500, 500, firePalette);
        v = new Viewer(f);
        cp = new ControlPanel();
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        this.setSize(1366, 768);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


        //Viewier ubi

        changeGridPos(constraints, 0, 0);
        changeGridDim(constraints, 1, 3);
        this.add(v,constraints);

        //Control Panel ubi
        changeGridPos(constraints, 1, 0);
        changeGridDim(constraints, 2, 3);
        cp.setBackground(Color.green);
        this.add(cp, constraints);

        //Thread on marxa
        Thread t1 = new Thread(v); //ho pos aquí perquè a nes viewer no funciona
        t1.start();

        this.getContentPane().setBackground(Color.cyan);
    }

    private void setBackgroundColor(Color color){
        this.backGroundColor = color;
        this.setBackground(color);
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

//    public void farcirMyTaskViewer(){
//
//        changeGridPos(constraints, 0, 0);
//        changeGridDim(constraints, 3, 3);
//        this.add(v,constraints);
//        Thread t1 = new Thread(v);
//        t1.start();
//
//    }
//    public void farcirMyTaskCP(){
//        changeGridPos(constraints, 1, 0);
//        changeGridDim(constraints, 2, 3);
//        cp.setBackground(Color.green);
//        this.add(cp, constraints);
//    }

    public static void main(String[] args) {

        new MyTask();
    }

    
}
