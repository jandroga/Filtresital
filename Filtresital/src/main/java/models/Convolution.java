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
        greyImage = toGrey(rawImage);
        this.image3d = imageToArray(greyImage);
        this.image2d = applyConvolution();
    }

    public int[][] getImage2d() {
        return image2d;
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

//    private int[][] setGreyScale(BufferedImage bufferedImage){
//
//        BufferedImage temp = this.rawImage;
//        for (int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//                int p = temp.getRGB(i,j);
//                int a = (p>>24)&0xff;
//                int r = (p>>16)&0xff;
//                int g = (p>>8)&0xff;
//                int b = p&0xff;
//
//                int avg = (r+g+b)/3;
//
//                p = (a<<24) | (avg<<16) | (avg<<8) | avg;
//                temp.setRGB(i, j, p);
//            }
//        }
//        return bufferedImage;
//    }

    private int tooBigRGB(double num){
        if (num > 255) return 255;
        else if (num < 0) return 0;
        else return (int) num;
    }

    public int[][][] imageToArray(BufferedImage bufferedImage) {
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

    private int[][] applyConvolution() {
        int[][] redConv = convolutionType2(image3d[0], width, height, 3, 3, 1);
        int[][] greenConv = convolutionType2(image3d[1], width, height, 3, 3, 1);
        int[][] blueConv = convolutionType2(image3d[2], width, height, 3, 3, 1);
        int[][] finalConv = new int[redConv.length][redConv[0].length];

        for (int i = 0; i < redConv.length; i++) {
            for (int j = 0; j < redConv[i].length; j++) {
                finalConv[i][j] = redConv[i][j] + greenConv[i][j] + blueConv[i][j];
            }
        }
        return finalConv;
    }

    private double singlePixelConvolution(int[][] input, int x, int y, double[][] k, int kernelWidth, int kernelHeight) {
        double output = 0;
        for (int i = 0; i < kernelWidth; ++i) {
            for (int j = 0; j < kernelHeight; ++j) {

                output = output + (input[getIJ(x + i)][getIJ(y + j)] * k[i][j]);
            }
        }
        return output;
    }

    private int getIJ(int num){
        if(num >= 260) num = 259;
        return num;
    }
    

    private int[][] convolution2D(int[][] input, int width, int height,
                                    double[][] kernel, int kernelWidth, int kernelHeight) {
        int smallWidth = width - kernelWidth + 1;
        int smallHeight = height - kernelHeight + 1;
        int[][] output = new int[smallWidth][smallHeight];
        for (int i = 0; i < smallWidth; ++i) {
            for (int j = 0; j < smallHeight; ++j) {
                output[i][j] = 0;
            }
        }
        for (int i = 0; i < smallWidth; ++i) {
            for (int j = 0; j < smallHeight; ++j) {
                output[i][j] = (int) singlePixelConvolution(input, i, j, kernel, kernelWidth, kernelHeight);
            }
        }
        return output;
    }

    private int[][] convolution2DPadded(int[][] input, int width, int height, int kernelWidth, int kernelHeight) {
        int smallWidth = width - kernelWidth + 1;
        int smallHeight = height - kernelHeight + 1;
        int top = kernelHeight / 2;
        int left = kernelWidth / 2;

        int[][] small = convolution2D(input, width, height,
                kernel, kernelWidth, kernelHeight);
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

    private int[][] convolutionType2(int[][] input, int width, int height, int kernelWidth, int kernelHeight, int iterations) {
        int[][] newInput = input.clone();
        int[][] output = input.clone();

        for (int i = 0; i < iterations; ++i) {
            output = convolution2DPadded(newInput, width, height, kernelWidth, kernelHeight);
            newInput = output.clone();
        }
        return output;
    }
}
