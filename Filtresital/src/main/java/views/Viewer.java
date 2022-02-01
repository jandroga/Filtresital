/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;


import models.Fire;

import java.awt.*;

/**
 *
 * @author NitroPC
 */



public class Viewer extends Canvas implements Runnable{

    private Fire fuego;
    private volatile boolean running;

    public Viewer(Fire fuego) {
        this.fuego = fuego;
        setSize(fuego.getWidth(), fuego.getHeight());
        setVisible(true);

        Thread threadV = new Thread(this);
        threadV.start();

        Thread thread = new Thread(fuego);
        thread.start();
    }


    public void paint(Graphics g) {

        g.drawImage(fuego,0,0,this);
    }
    

    @Override
    public void run() {

        while (true) {
            if (this.getGraphics() != null){
                paint(this.getGraphics());
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    public boolean isRunning(){
        return running;
    }

    public void setRunning(boolean running){
        this.running = running;
    }
}