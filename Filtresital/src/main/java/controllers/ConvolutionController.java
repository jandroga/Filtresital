package controllers;

import views.ConvolutionViewer;

import javax.swing.*;

public class ConvolutionController {

    private ConvolutionViewer cv;
    private MyTask mt;

    public ConvolutionController(MyTask mt){

        this.mt = mt;
    }

    public void addConvolutionFire(){
        mt.addConvolutionImage();
        SwingUtilities.updateComponentTreeUI(mt);

    }
}
