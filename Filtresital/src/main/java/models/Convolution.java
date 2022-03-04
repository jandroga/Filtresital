package models;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Convolution {

    int width;
    int height;

    int [][] kernel = {
            {-1, -1, -1},
            {-1, 8, -1},
            {-1, -1, -1}};

    public Convolution(){
    }


    public double[][][] imageToArray(BufferedImage bufferedImage) {
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();

        double[][][] image = new double[3][height][width];
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

//    private double[][] convolve(double[][] input, int width, int height,
//                                double[][] kernel, int kernelWidth,
//                                int kernelHeight, int iterations){
//
//
//
//    }

    private double[][][] applyConv(int width, int height, double[][][] image, double[][] filter){


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

            }

        }
        return image;
    }


    public double singlePixelConvolution(double[][] input,
                                                int x, int y,
                                                double[][] k,
                                                int kernelWidth,
                                                int kernelHeight) {
        double output = 0;
        for (int i = 0; i < kernelWidth; ++i) {
            for (int j = 0; j < kernelHeight; ++j) {
                output = output + (input[x + i][y + j] * k[i][j]);
            }
        }
        return output;
    }

    public double[][] convolution2D(double[][] input,
                                           int width, int height,
                                           double[][] kernel,
                                           int kernelWidth,
                                           int kernelHeight) {
        int smallWidth = width - kernelWidth + 1;
        int smallHeight = height - kernelHeight + 1;
        double[][] output = new double[smallWidth][smallHeight];
        for (int i = 0; i < smallWidth; ++i) {
            for (int j = 0; j < smallHeight; ++j) {
                output[i][j] = 0;
            }
        }
        for (int i = 0; i < smallWidth; ++i) {
            for (int j = 0; j < smallHeight; ++j) {
                output[i][j] = singlePixelConvolution(input, i, j, kernel,
                        kernelWidth, kernelHeight);
            }
        }
        return output;
    }
    public double[][] convolution2DPadded(double[][] input,
                                                 int width, int height,
                                                 double[][] kernel,
                                                 int kernelWidth,
                                                 int kernelHeight) {
        int smallWidth = width - kernelWidth + 1;
        int smallHeight = height - kernelHeight + 1;
        int top = kernelHeight / 2;
        int left = kernelWidth / 2;

        double[][] small = convolution2D(input, width, height,
                kernel, kernelWidth, kernelHeight);
        double large[][] = new double[width][height];
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

    public double[][] convolutionType2(double[][] input,
                                       int width, int height,
                                       double[][] kernel,
                                       int kernelWidth, int kernelHeight,
                                       int iterations) {
        double[][] newInput = input.clone();
        double[][] output = input.clone();

        for (int i = 0; i < iterations; ++i) {
            output = convolution2DPadded(newInput, width, height,
                    kernel, kernelWidth, kernelHeight);
            newInput = output.clone();
        }
        return output;
    }
    private int fixOutOfRangeRGBValues(double value) {
        if (value < 0.0) {
            value = -value;
        }
        if (value > 255) {
            return 255;
        } else {
            return (int) value;
        }
    }
}
