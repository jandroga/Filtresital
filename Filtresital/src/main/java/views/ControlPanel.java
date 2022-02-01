package views;

import controllers.MyTask;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel{


    public ControlPanel(MyTask mt){

        super(new GridBagLayout());
        FireControlPanel fireControlPanel = new FireControlPanel(mt);
//        ConvolutionControlPanel convolutionControlPanel = new ConvolutionControlPanel();
        this.setSize(500,500);
        this.setVisible(true);
        this.add(fireControlPanel);
//        this.add(convolutionControlPanel);

    }

}
