/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.awt.image.BufferedImage;


public class Fire extends BufferedImage implements Runnable{

    private volatile FirePalette firePalette;
    private Convolution convolution;
    private final int width;
    private final int height;
    private int[][] tempMap; //array 2d que servirà de mapa de temperatura
    private int[][] newTempMap;
    private boolean running = true;
    private boolean convoluted;
    private double fireLenght = 0.68;

    public Fire(int width, int height, FirePalette firePalette, Convolution convolution){
        super(width,height,BufferedImage.TYPE_INT_ARGB);

        this.convolution = convolution;
        this.newTempMap = new int[width][height];
        this.firePalette = firePalette;
        this.height = height;
        this.width = width;
        this.tempMap = new int[width][height];

    }


    public FirePalette getFirePalette() {
        return firePalette;
    }

    public void setFirePalette(FirePalette firePalette) {
        this.firePalette = firePalette;
    }


    public void createSparks(){
    
        for (int i = 1; i < width; i++){ //recorrem la base del foc
            if(((int)(Math.random()*5+1))<5){
                tempMap[i][height - 1] = 255; //i = eix d'x i height - 100 és sa posició on començarà es foquet
            }
        }
    }

    private void createConvolutedSparks(){
        tempMap = convolution.getImage2d();
        for (int i = 0; i < convolution.getImage2d().length; i++) {
            for (int j = 0; j < convolution.getImage2d().length; j++) {
                System.out.println(convolution.getImage2d()[i][j]);
            }
        }
    }


    public void flameEvolve(){


        newTempMap = tempMap;
        for (int i = 1;  i < width -1; i++){
            for (int j = 1; j < height -1; j++){
                int down = newTempMap[i][j+1];          //assignam cada pròxim
                int downLeft = newTempMap[i-1][j+1];    //valor a una posició
                int downRight = newTempMap[i+1][j+1];   //per canviar-ho tot més fàcil
                int current = newTempMap[i][j];
                int result = (down + downLeft + downRight + current)/4; //mitja de sa temperatura
                tempMap[i][j] = result;
            }
        }
    }
    
    private void flamePaint(){

        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                this.setRGB(i, j, firePalette.getColor(tempMap[i][j]));
                //flamePalette.getColor(tempMap[i][j])
        
            }
        }   
    }

    public double getFireLenght() {
        return fireLenght;
    }

    private void coldSparks() {
        for (int i = 0; i < width; i++) {
            for (int j = height - 5; j > 0; j--) {
                if (((int) (Math.random() * 2)) < 1) {
                    tempMap[i][j] = (int)Math.ceil(tempMap[i][j]*fireLenght);
                }
            }
        }
    }

    public void setFireLength(double fireLenght){
        this.fireLenght = fireLenght;
    }

    @Override
    public void run(){

        while(true) {
            try {
                //Si llev aquest sout no se reanuda es foc (per lo que sigui deixa de detectar es while)
                Thread.sleep(10);
                while(running) {
//                    createConvolutedSparks();
                    createSparks();
                    flameEvolve();
                    coldSparks();
                    flamePaint();
                    Thread.sleep(20);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public boolean getRunning() {

        return this.running;
    }

    public void setRunning(boolean running){
        this.running = running;
    }
}



