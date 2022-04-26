package models;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Convolution {

    private int width;
    private int height;
    private int[][][] image3d;
    private BufferedImage greyImage;
    private int[][] image2d;

    private final BufferedImage rawImage;

    double[][] kernel = {
            {-1, -1, -1},
            {-1, 8, -1},
            {-1, -1, -1}};

    public Convolution(BufferedImage rawImage) {
        this.rawImage = rawImage;
        this.width = rawImage.getWidth();
        this.height = rawImage.getHeight();
        novaConvo();
//        greyImage = toGrey(rawImage);
//        this.image3d = getImageColors(greyImage);
//        this.image2d = applyConvolution();
    }

    public int[][] getImage2d() {
        return image2d;
    }

    private int[][] novaConvo(){
        int x = rawImage.getWidth();
        int y = rawImage.getHeight();
        int[][] arrayConvo = new int[x][y];
        for(int i=1;i<x-1;i++){
            for(int j=1;j<y-1;j++){
                int p=0,a=0,r=0,g=0,b=0;
                for(int k=-1;k<2;k++){
                    for(int l=-1;l<2;l++){
                        if(k==0 && l==0){
                            p = rawImage.getRGB(i+k,j+l);
                            r += 8*((p>>16) & 0xFF);
                            g += 8*((p>>8) & 0xFF);
                            b += 8*(p & 0xFF);
                            continue;
                        }
                        p = rawImage.getRGB(i+k,j+l);
                        r -= ((p>>16) & 0xFF);
                        g -= ((p>>8) & 0xFF);
                        b -= (p & 0xFF);
                    }
                }
                r = Math.min(255,r);
                r = Math.max(0,r);
                g = Math.min(255,g);
                g = Math.max(0,g);
                b = Math.min(255,b);
                b = Math.max(0,b);
                a = (rawImage.getRGB(i,j)>>24) & 0xFF;
                p = (a<<24) | (r<<16) | (g<<8) | b;
                rawImage.setRGB(i, j, p);
                arrayConvo[i][j] = rawImage.getRGB(i,j);
                System.out.println(arrayConvo[i][j]);
            }
        }
        return arrayConvo;
    }

    private BufferedImage toGrey(BufferedImage bufferedImage){

        BufferedImage temp = this.rawImage;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int p = temp.getRGB(i,j);
                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;

                int avg = (r+g+b)/3;

                p = (a<<24) | (avg<<16) | (avg<<8) | avg;
                temp.setRGB(i, j, p);
            }
        }
        return bufferedImage;
    }

    private int tooBigRGB(double num){
        if (num > 255) return 255;
        else if (num < 0) return 0;
        else return (int) num;
    }

    public int[][][] getImageColors(BufferedImage bufferedImage) {
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();

        int[][][] image = new int[3][height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color = new Color(bufferedImage.getRGB(j, i));
                image[0][i][j] = color.getRed();
                image[1][i][j] = color.getGreen();
                image[2][i][j] = color.getBlue();
            }
        }
        return image;
    }

    //Entra array 2d i surt s'array 2d multiplicada pes kernel
    private int[][] applyConvolution() {
        int[][] redConv = convolutionType2(image3d[0], 3, 3, 1);
        int[][] greenConv = convolutionType2(image3d[1], 3, 3, 1);
        int[][] blueConv = convolutionType2(image3d[2], 3, 3, 1);
        int[][] finalConv = new int[redConv.length][redConv[0].length];

        for (int i = 0; i < redConv.length; i++) {
            for (int j = 0; j < redConv[i].length; j++) {
                finalConv[i][j] = redConv[i][j] + greenConv[i][j] + blueConv[i][j];
            }
        }
        return finalConv;
    }

    private double singlePixelConvolution(int[][] input, int x, int y, double[][] k) {
        double output = 0;
        for (int i = 0; i < kernel.length; ++i) {
            for (int j = 0; j < kernel.length; ++j) {
                output = output + (input[x + i][y + j] * k[i][j]);
                System.out.println(output);
            }
        }
        return output;
    }

//    private int getIJ(int num){
//        if(num >= 260) num = 259;
//        return num;
//    }
    

    private int[][] convolution2D(int[][] input, int width, int height) {
        int smallWidth = width - kernel.length + 1;
        int smallHeight = height - kernel.length +1 ;
        int[][] output = new int[smallWidth][smallHeight];
        for (int i = 0; i < smallWidth; ++i) {
            for (int j = 0; j < smallHeight; ++j) {
                output[i][j] = 0;
            }
        }
        for (int i = 0; i < smallWidth; ++i) {
            for (int j = 0; j < smallHeight; ++j) {
                output[i][j] = (int) singlePixelConvolution(input, i, j, kernel);
            }
        }
        return output;
    }

    private int[][] convolution2DPadded(int[][] input, int width, int height) {
        int smallWidth = width - kernel.length + 1;
        int smallHeight = height - kernel.length + 1;
        int top = kernel.length / 2;
        int left = kernel.length / 2;

        int[][] small = convolution2D(input, width, height);
        int large[][] = new int[width][height];
        for (int j = 0; j < height; ++j) {
            for (int i = 0; i < width; ++i) {
                large[i][j] = 0;
            }
        }
        for (int j = 0; j < smallHeight; ++j) {
            for (int i = 0; i < smallWidth; ++i) {
                large[i + left][j + top] = small[i][j];
            }
        }
        return large;
    }

    private int[][] convolutionType2(int[][] input, int width, int height, int iterations) {
        int[][] newInput = input.clone();
        int[][] output = input.clone();

        for (int i = 0; i < iterations; i++) {
            output = convolution2DPadded(newInput, width, height);
            newInput = output.clone();
        }
        return output;
    }
}
