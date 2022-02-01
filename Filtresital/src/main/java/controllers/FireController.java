package controllers;

import models.FirePalette;
import views.FireControlPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class FireController {

    private boolean pitjat;
    private final ArrayList<FirePalette> firePaletteArrayList;
    private FireControlPanel fcp;
    private MyTask mt;
    private int fireColorState = 0;

    private boolean isFirePaused = false;

    public FireController(MyTask mt){
        this.mt = mt;

        firePaletteArrayList = new ArrayList<FirePalette>(createFirePalettes(mt));
    }

    //E TU
    //No me cotilleis es pc
    public void setFireState() {
        mt.getF().setRunning(!mt.getF().getRunning());
    }

    public void setBackground(ActionEvent e, JButton button) {
        if(e.getSource()==button){
            JColorChooser colorChooser = new JColorChooser();
            Color color = JColorChooser.showDialog(null,"Tria un color", Color.CYAN);
            mt.getContentPane().setBackground(color);
        }
    }
    public void changeFireLength() {

        if(!pitjat){
            mt.getF().setFireLength((float) 0.75);
            System.out.println("foquet");
            pitjat = true;
        }else{
            mt.getF().setFireLength((float) 0.93);
            System.out.println("focot");
            pitjat = false;
        }
        System.out.println(mt.getF().getFireLenght());

    }

    public void changeFlameColor() {
        fireColorState++;
        switch (fireColorState){
            case 1:
                mt.getF().setFirePalette(firePaletteArrayList.get(1));
                System.out.println("a");

                break;
            case 2:
                mt.getF().setFirePalette(firePaletteArrayList.get(2));
                System.out.println("b");
                break;
            case 3:
                mt.getF().setFirePalette(firePaletteArrayList.get(3));
                System.out.println("c");
                break;
            case 4:
                mt.getF().setFirePalette(firePaletteArrayList.get(4));
                System.out.println("d");
                break;
            case 5:
                mt.getF().setFirePalette(firePaletteArrayList.get(0));
                System.out.println("darrer");
                fireColorState = 0;
        }
    }

    //Ignore pls
    private ArrayList<FirePalette> createFirePalettes(MyTask mt) {

        ArrayList<FirePalette> firePaletteArrayList = new ArrayList<>();

        firePaletteArrayList.add(mt.getF().getFirePalette());

        firePaletteArrayList.add(new FirePalette(new Color(0, 50, 255, 255),
                new Color(0, 50, 200, 255),
                new Color(0, 50, 150, 255),
                new Color(0, 50, 100, 255),
                new Color(0, 0, 45, 255),
                new Color(0, 0, 0, 255)));

        firePaletteArrayList.add(new FirePalette(new Color(0, 255, 0, 255),
                new Color(0, 200, 0, 255),
                new Color(0, 180, 0, 255),
                new Color(0, 100, 0, 255),
                new Color(0, 70, 0, 255),
                new Color(0, 0, 0, 255)));

        firePaletteArrayList.add(new FirePalette(new Color(255, 0, 255, 255),
                new Color(155, 0, 155, 255),
                new Color(100, 0, 100, 255),
                new Color(75, 0, 75, 255),
                new Color(50, 0, 50, 100),
                new Color(0, 0, 0, 255)));

        firePaletteArrayList.add(new FirePalette(new Color(255, 255, 255, 255),
                new Color(200, 200, 200, 255),
                new Color(150, 150, 150, 255),
                new Color(100, 100, 100, 255),
                new Color(50, 50, 50, 255),
                new Color(0, 0, 0, 255)));

        System.out.println("Array de colors carregada");

        return firePaletteArrayList;
    }
}
