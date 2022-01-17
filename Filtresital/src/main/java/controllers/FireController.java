package controllers;

import models.Fire;

public class FireController {

    private Fire foc;

    public FireController(Fire foc){
        this.foc = foc;


    }

    public void cositaFire(Boolean state) {
        foc.setRunning(state);
    }
}
