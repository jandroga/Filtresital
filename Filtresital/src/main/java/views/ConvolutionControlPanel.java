package views;

import controllers.ConvolutionController;
import controllers.MyTask;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ConvolutionControlPanel extends JPanel {

    private ConvolutionController cc;
    private MyTask mt;
    private Background bg;
    GridBagConstraints constraints = new GridBagConstraints();

    public ConvolutionControlPanel(MyTask mt){
        this.mt = mt;

        cc = new ConvolutionController(mt);
        bg = new Background(500, 500, BufferedImage.TYPE_INT_ARGB);
        this.setBackground(Color.GREEN);
        this.setVisible(true);

        JTextArea textArea = new JTextArea("Convolució");
        changeGridPos(constraints, 0, 0);
//        this.add(textArea, constraints);
        //Buttons
        JButton button1 = new JButton("1");
        changeGridPos(constraints, 0,1);
        this.add(button1, constraints);
        button1.addActionListener(e -> cc.addConvolutionFire());

        JButton button2 = new JButton("2");
        changeGridPos(constraints, 0,2);
        this.add(button2, constraints);
        button2.addActionListener(e -> mt.afegirBg());
    }


    private void changeGridPos(GridBagConstraints constraints, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
    }
}
