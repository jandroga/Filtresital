/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author NitroPC
 */
public class FirePalette {
    int[] colors;
    private ArrayList<TargetColor> targetArray = new ArrayList<>();
  
    
    public FirePalette(){
        createColors();
    }
    
    
    
    public void createColors(){//Método per crear es colors que després aniran
        colors = new int[256];
        
        targetArray.add(new TargetColor(255, new Color(255,255,0,255)));//255 és sa "posició"(temp) i lo altre és es color k surt
        targetArray.add(new TargetColor(200, new Color(255,180,0,255)));
        targetArray.add(new TargetColor(155, new Color(255,145,0,255)));
        targetArray.add(new TargetColor(100, new Color(255,100,0,255)));
        targetArray.add(new TargetColor(50, new Color(255,0,0,255)));
        targetArray.add(new TargetColor(0, new Color(0,0,0,255)));
             
        for(int i = 0; i < targetArray.size()-1; i++){//index 0 groc
            TargetColor current = targetArray.get(i);
            TargetColor next = targetArray.get(i+1);
            for(int j = current.temp; j >= next.temp; j--){
                colors[Math.abs(0-j)] = interpolateColor(current, next, current.temp - next.temp, current.temp-j);
            }
        }
    }
    
    public int interpolateColor(TargetColor current, TargetColor next, int totalIterations, int currentIteration){
    
        
        int r = current.color.getRed() - (current.color.getRed() - next.color.getRed())/totalIterations*currentIteration;       //pillam diferència entre colors
        int g = current.color.getGreen() - (current.color.getGreen() - next.color.getGreen())/totalIterations*currentIteration;   //que ficarem a array
        int b = current.color.getBlue() - (current.color.getBlue() - next.color.getBlue())/totalIterations*currentIteration;
        int a = current.color.getAlpha() - (current.color.getAlpha() - next.color.getAlpha())/totalIterations*currentIteration;
        
        Color result = new Color(r, g, b, a);
        
        return result.getRGB();
        }

        
        public int getColor(int temp){ //Pillar color
        if (colors == null) createColors();//si la paleta és buida, createColors()
        return colors[temp];//buida
        }
//        public void createColors(){
//            
//            colors = new int[256];
//            for(int i=0; i<)
//        }

    public class TargetColor {
        
        public int temp;
        public Color color;
        
        public TargetColor(int temp, Color color){
        
            this.temp = temp;
            this.color = color;
        }

        private void addColorFinal(TargetColor targetColor){
            targetArray.add(targetColor);
        }
    }
        FirePalette firePalette;
        
        public void setFlamePalette(FirePalette addColorFinal) {
            createColors();
            firePalette = new FirePalette();
        }
    }
        
        

