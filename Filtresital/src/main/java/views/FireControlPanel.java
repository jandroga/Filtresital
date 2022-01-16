package views;

import javax.swing.*;

public class FireControlPanel extends JPanel {

    public FireControlPanel(){
        this.setSize(500,500);
        this.setVisible(true);
        JButton pauseButton = new JButton("Pausa");
        pauseButton.setVisible(true);
        System.out.println("aaaa");
        this.add(pauseButton);

        pauseButton.addActionListener(e -> System.out.println("asd"));
    }

}
