
package game;

import processing.core.PApplet;
import processing.core.PImage;


public class LoseZombiev extends PApplet {
    PImage background;
    
    @Override
    public void settings() {
        size(375, 650);
    }
    
    

    @Override
    public void setup() {
        background=loadImage("images/PerderZombie.png");
        

    }

    @Override
    public void draw() {
        
        background(background);

        if ( mouseX>=50 && mouseX<=325 && mouseY>=525 && mouseY<=575){
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
