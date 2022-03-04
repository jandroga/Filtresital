package com.mycompany.convotest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageToConvolve extends BufferedImage {

    public BufferedImage img = null;

    public ImageToConvolve(int width, int height, int imageType) {
        super(width, height, imageType);
    }

    public BufferedImage loadImage() {
        try {
            img = ImageIO.read(new File("src/res/samurai.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}