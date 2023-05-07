
package game;

import processing.core.PApplet;
import processing.core.PImage;


public class LoseBeach2 extends PApplet {
    PImage background;
    PImage position;
    
    @Override
    public void settings() {
        size(375, 650);
    }
    
    

    @Override
    public void setup() {
        background=loadImage("images/PerderPlaya.png");
        position=loadImage("images/Segundox.png");
        

    }

    @Override
    public void draw() {
        
        background(background);
        image(position,60,30,120,120);
        if ( mouseX>=50 && mouseX<=325 && mouseY>=465 && mouseY<=515){
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
