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
    GridBagConstraints constraints = new GridBagConstraints();


    public MyTask() {
    
       super("Foquet");

       //Carregant cositas
       firePalette = new FirePalette();
       Fire f = new Fire(500, 500, firePalette);
       v = new Viewer(f);
       cp = new ControlPanel();

       this.setLayout(new GridBagLayout());
       this.getContentPane().setBackground(Color.cyan);

        farcirMyTaskViewer();
        farcirMyTaskCP();
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

    public void farcirMyTaskViewer(){
        this.setSize(1366, 768);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        changeGridPos(constraints, 0, 0);
        this.add(v,constraints);
        Thread t1 = new Thread(v);
        t1.start();

    }
    public void farcirMyTaskCP(){
        changeGridPos(constraints, 1, 0);
        changeGridDim(constraints, 2, 3);
        cp.setBackground(Color.green);
        this.add(cp, constraints);
    }

    public static void main(String[] args) {

        new MyTask();
    }

    
}
