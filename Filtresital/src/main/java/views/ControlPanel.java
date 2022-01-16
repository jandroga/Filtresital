package views;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    FireControlPanel fireControlPanel = new FireControlPanel();
    GridBagConstraints constraints = new GridBagConstraints();

    public ControlPanel(){


        super(new GridBagLayout());
        this.setSize(400,400);
        this.setVisible(true);
        this.setBackground(Color.GREEN);
        this.add(fireControlPanel);


    }

}
