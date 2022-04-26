package com.mycompany.convotest;

import java.awt.*;

public class Viewer extends Canvas {

    private ImageToConvolve imageToConvolve;

    public Viewer(){
        imageToConvolve = new ImageToConvolve(imageToConvolve.getWidth(), imageToConvolve.getHeight(), imageToConvolve.getType());
    }
}
