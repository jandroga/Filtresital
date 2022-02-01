package views;

import controllers.FireController;
import controllers.MyTask;

import javax.swing.*;
import java.awt.*;

public class FireControlPanel extends JPanel {

    private final FireController fc;
    private final boolean running = true;
    private final boolean buttonState = true;
    private boolean pitjat = false;
    GridBagConstraints constraints = new GridBagConstraints();
//    private int colors[];




    public FireControlPanel(MyTask mt){

        super(new GridBagLayout());

        this.setSize(500,500);
        this.setVisible(true);
        this.setBackground(Color.gray);

        constraints.fill = GridBagConstraints.BOTH;

        //Crear colors

        fc = new FireController(mt);



        JTextField textField = new JTextField("Foquet");

        //Creant botons

        //PAUSE
        JButton pauseButton = new JButton("Pausa");
        changeGridPos(constraints, 0, 1);
        pauseButton.setVisible(true);
        pauseButton.addActionListener(e -> fc.setFireState());
        this.add(pauseButton, constraints);

        //FONS DE PANTALLA
        JButton backgroundButton = new JButton("Canviar fons de pantalla");
        backgroundButton.setVisible(true);
        changeGridPos(constraints, 0, 2);
        backgroundButton.addActionListener(e -> fc.setBackground(e,backgroundButton));
        this.add(backgroundButton, constraints);

        JButton resolutionButton = new JButton("Selecciona resol·lució");
        resolutionButton.setVisible(false);
        changeGridPos(constraints,0,3);
        this.add(resolutionButton,constraints);

        //Fire Length
        JButton toogleFireLenghtButton = new JButton("Canvia entre foquet i focot");
        toogleFireLenghtButton.setVisible(true);
        changeGridPos(constraints,0,4);
        toogleFireLenghtButton.addActionListener(e -> fc.changeFireLength());
        this.add(toogleFireLenghtButton, constraints);

        JButton flameColorButton = new JButton("Canvia colors des foquet");
        flameColorButton.setVisible(true);
        changeGridPos(constraints,0,5);
        flameColorButton.addActionListener(e -> fc.changeFlameColor());
        this.add(flameColorButton,constraints);
    }

    private void changeGridPos(GridBagConstraints constraints, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
    }
}