
package game;

import processing.core.PApplet;
import processing.core.PImage;


public class ChooseMap extends PApplet{
    PImage background;
    
    @Override
    public void settings() {
        size(375, 650);
    }

    @Override
    public void setup() {
        background=loadImage("images/ElegirMapa.png");

    }

    @Override
    public void draw() {
        
        background(background); 
        
        
        if ( mouseX>=0 && mouseX<=375 && mouseY>=0 && mouseY<=328){
           if (mousePressed == true){
               Streetmap mapZombies = new Streetmap();
               mapZombies.runApp(); 
               this.dispose();
           } 
        }else if (mouseX>=0 && mouseX<=375 && mouseY>=329 && mouseY<=650){
           if(mousePressed==true){
                BeachMap mapBeach = new BeachMap();
                mapBeach.runApp(); 
                this.dispose();
           }
       }
    }
        public void runApp() {
        String[] processingArgs = { this.getClass().getName() };
        PApplet.runSketch(processingArgs, this);
    }
}
