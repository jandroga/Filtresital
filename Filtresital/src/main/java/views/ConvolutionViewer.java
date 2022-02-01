package views;

import models.Fire;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ConvolutionViewer extends Canvas implements Runnable{

    private Fire fuego;
    private boolean isFilterActive;

    //Afegir es filtre com a array? Nosesae
    public ConvolutionViewer(Fire fuego){
        this.fuego = fuego;
        setSize(fuego.getWidth(), fuego.getHeight());
        setVisible(true);

        //FER UNCOMENT EN VOLER QUE FURULI
//        Thread cv = new Thread(this);
    }

    private double[][][] imageToArray(BufferedImage bufferedImage){
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        double[][][] image = new double[3][height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color = new Color(bufferedImage.getRGB(j,i));

                image[0][i][j] = color.getRed();
                image[1][i][j] = color.getGreen();
                image[2][i][j] = color.getBlue();
            }

        }
        return image;
    }

//    private double[][] applyConv(int width, int height, double[][][] image, double[][] filter){
//        Convolution convolution = new Convolution();
//    }
    public void paint(Graphics g)
    {
    g.drawImage(fuego, 0, 0, this);
    }

    @Override
    public void run() {
        while (true) {
            if (this.getGraphics() != null){
                paint(this.getGraphics());
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}
