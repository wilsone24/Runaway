
package game;

import processing.core.PApplet;
import processing.core.PImage;

public class MenuPrincipal extends PApplet{

    PImage background;
    PImage credits;
    PImage keys;
    
    @Override
    public void settings() {
        size(375, 650);
    }

    @Override
    public void setup() {
        background=loadImage("images/Pp.png");
        credits =loadImage("images/PostCreditos.png");
        keys =loadImage("images/Teclas.png");

    }

    @Override
    public void draw() {
        background(background); 
        
        if(mouseX>=295 && mouseX<=365 && mouseY>=550 && mouseY<=610){
            image(credits,70,30,300,250);// fija
        }
        
        if(mouseX>=10 && mouseX<=90 && mouseY>=550 && mouseY<=620){
            image(keys,90,420,200,200);// fija
        }
        
        
        if ( mouseX>=50 && mouseX<=320 && mouseY>=150 && mouseY<=500){
           if (mousePressed == true){
               Preguntas pre = new Preguntas();
               pre.runApp();
               this.dispose();
            } 
        }
    }
    
    
        public void runApp() {
        String[] processingArgs = { this.getClass().getName() };
        PApplet.runSketch(processingArgs, this);
    }
    
}
