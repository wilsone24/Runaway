
package game;

import processing.core.PApplet;
import processing.core.PImage;


public class LoseZombie22 extends PApplet {
    PImage background;
    PImage position;
    
    @Override
    public void settings() {
        size(375, 650);
    }
    
    

    @Override
    public void setup() {
        background=loadImage("images/PerderZombie.png");
        position=loadImage("images/Segundox.png");
        

    }

    @Override
    public void draw() {
        
        background(background);
        image(position,50,15,110,110);
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
