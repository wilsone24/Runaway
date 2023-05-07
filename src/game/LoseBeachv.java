
package game;

import processing.core.PApplet;
import processing.core.PImage;


public class LoseBeachv extends PApplet {
    PImage background;
    
    @Override
    public void settings() {
        size(375, 650);
    }
    
    

    @Override
    public void setup() {
        background=loadImage("images/PerderPlaya.png");
        

    }

    @Override
    public void draw() {
        
        background(background);

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
