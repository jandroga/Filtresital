package views;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel implements Runnable{

    FireControlPanel fireControlPanel = new FireControlPanel();

    public ControlPanel(){


        super(new GridBagLayout());
        this.setSize(400,400);
        this.setVisible(true);
        this.add(fireControlPanel);


    }

    @Override
    public void run() {

    }
}
