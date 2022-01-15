/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controllers;

import models.Fire;
import models.FlamePalette;
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
    private FlamePalette flamePalette;
    
    public MyTask() {
    
       super("Foquet");
       flamePalette = new FlamePalette();
       Fire f = new Fire(500, 500, flamePalette);
       v = new Viewer(f);
       this.setLayout(new GridBagLayout());
       this.setSize(1000, 1000);
       //this.getContentPane().setBackground(new Color(255,0,0,255));
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setVisible(true);
       this.add(v);
       
       Thread t1 = new Thread(v); //ho pos aquí perquè a nes viewer no funciona
       t1.start();

       
       
       
    }
    public static void main(String[] args) {
        
        // TODO code application logic here
        new MyTask();
       
        

    }

    
}
