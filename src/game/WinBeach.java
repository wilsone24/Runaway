
package game;

import processing.core.PApplet;
import processing.core.PImage;


public class WinBeach extends PApplet {
    PImage background;
    PImage position;
    
    @Override
    public void settings() {
        size(375, 650);
    }
    
    

    @Override
    public void setup() {
        background=loadImage("images/GanarPlaya.png");
        position=loadImage("images/Primerox.png");
    }

    @Override
    public void draw() {
        background(background);
        image(position,60,30,120,120);
        if ( mouseX>=45 && mouseX<=320 && mouseY>=555 && mouseY<=605){
            if (mousePressed == true){
                MenuPrincipal inicio = new MenuPrincipal();
                inicio.runApp(); 
                this.dispose();
            }
        }
        
    }
    

    public void runApp() {
        String[] processingArgs = { this.getClass().getName() };
        PApplet.runSketch(processingArgs, this);
    }
}
