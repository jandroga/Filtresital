package views;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background extends BufferedImage {

    private int width;
    private int height;
    public BufferedImage image;
    public BufferedImage tempImage;

    public Background(int width, int height, int imageType) {
        super(width, height, imageType);
        this.width = width;
        this.height = height;
        loadImage();
    }

    public void loadImage(){
        try {
            tempImage = ImageIO.read(new File("res/samurai.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.image = tempImage;
    }

    public BufferedImage getBg(){
        return image;
    }
}
