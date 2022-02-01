package views;

import controllers.MyTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConvolutionControlPanel extends JPanel {

    GridBagConstraints constraints = new GridBagConstraints();

    public ConvolutionControlPanel(MyTask mt){
        this.setBackground(Color.GREEN);
        this.setVisible(true);

        JTextArea textArea = new JTextArea("Convolució");
        changeGridPos(constraints, 0, 0);
//        this.add(textArea, constraints);
        //Buttons
        JButton button1 = new JButton("1");
        changeGridPos(constraints, 0,1);
        this.add(button1, constraints);

        JButton button2 = new JButton("2");
        changeGridPos(constraints, 0,2);
        this.add(button2, constraints);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }


    private void changeGridPos(GridBagConstraints constraints, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
    }
}
