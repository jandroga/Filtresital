package views;

import controllers.MyTask;
import models.Fire;
import models.FirePalette;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FireControlPanel extends JPanel {

    private MyTask mt;
    private Viewer v;
    private Fire f;
    private FirePalette firePalette;
    private volatile boolean running = true;
    private boolean buttonState = true;
    public FireControlPanel(){
        this.setSize(500,500);
        this.setVisible(true);
        this.setBackground(Color.gray);


        //Creant botons
        JButton pauseButton = new JButton("Pausa");
        pauseButton.setVisible(true);
        this.add(pauseButton);

        JButton backgroundButton = new JButton("Canviar fons de pantalla");
        backgroundButton.setVisible(true);
        this.add(backgroundButton);

        JButton resolutionButton = new JButton("Selecciona resol·lució");
        resolutionButton.setVisible(true);
        this.add(resolutionButton);

        JButton toogleFireLenghtButton = new JButton("Canvia entre foquet i focot");
        toogleFireLenghtButton.setVisible(true);
        this.add(toogleFireLenghtButton);

        firePalette = new FirePalette();
        f = new Fire(500, 500, firePalette);
        v = new Viewer(f);


        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(buttonState){
                    System.out.println("Pausat");
                    updateButtonState();
                    System.out.println(buttonState);
                    f.setRunning(false);
                }
                else{
                    System.out.println("Reanudat");
                    updateButtonState();
                    System.out.println(buttonState);
                    f.setRunning(true);
                }

            }
        });

        backgroundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Revisar com crear-ne una nova i punt

                mt.getContentPane().setBackground(Color.black);
            }
        });

        resolutionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openResolutionMenu();
            }
        });
    }
    private void updateButtonState(){
        buttonState = !buttonState;
    }


    private void openResolutionMenu(){
        JMenuItem resMenu = new JMenuItem("asd");
        resMenu.setBackground(Color.cyan);
        resMenu.setVisible(true);
        this.add(resMenu);



    }
}



