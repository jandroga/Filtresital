package views;

import models.Fire;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class ConvolutionViewer extends Canvas implements Runnable{

    private Fire fuego2;
    private boolean isFilterActive;

    //Afegir es filtre com a array? Nosesae
    public ConvolutionViewer(Fire fuego){
        this.fuego2 = fuego;
        setSize(fuego.getWidth(), fuego.getHeight());
        setVisible(true);


        Thread threadf2 = new Thread(fuego2);
        Thread threadcv = new Thread(this);
        threadf2.start();
        threadcv.start();
    }

    public Fire getFuego2(){
        return fuego2;
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
        }return image;
    }

    private void filterOp(){
        float blurFloat = 1f/9f;
        float [] blurKernel = {
                blurFloat, blurFloat, blurFloat,
                blurFloat, blurFloat, blurFloat,
                blurFloat, blurFloat, blurFloat
        };
        BufferedImageOp blur = new ConvolveOp(new Kernel(3, 3, blurKernel));
    }

    public void paint(Graphics g)
    {
    g.drawImage(fuego2, 0, 0, this);
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
