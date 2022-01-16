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
    
    public MyTask() {
    
       super("Foquet");
       firePalette = new FirePalette();
       Fire f = new Fire(500, 500, firePalette);
       v = new Viewer(f);
       cp = new ControlPanel();
       this.setLayout(new GridBagLayout());
       GridBagConstraints constraints = new GridBagConstraints();

       //Viewier ubi
       this.setSize(1366, 768);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setVisible(true);
       changeGridPos(constraints, 0, 0);
       this.add(v,constraints);

       //Control Panel ubi
       changeGridPos(constraints, 1, 0);
       changeGridDim(constraints, 2, 3);
       cp.setBackground(Color.green);
       this.add(cp, constraints);

       //Thread on marxa
       Thread t1 = new Thread(v); //ho pos aquí perquè a nes viewer no funciona
       t1.start();

       
       
       
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
