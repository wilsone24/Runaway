
package game;

import processing.core.PApplet;
import processing.core.PImage;

public class Preguntas  extends PApplet{
        PImage background;
        String[] rutasImagenes = {
                                    "images/extend2.png",
                                    "images/append3.png",
                                    "images/insert2.png",
                                    "images/remove2.png",
                                    "images/pop2.png",
                                    "images/count2.png",
                                    "images/sort2.png",
                                    "images/reverse2.png",
                                  };
    
    @Override
    public void settings() {
        size(375, 650);
    }

    @Override
    public void setup() {
        int indiceAleatorio = floor(random(rutasImagenes.length));
        background= loadImage(rutasImagenes[indiceAleatorio]);
        
    }

    @Override
    public void draw() {
        
        background(background); 
        //rect(23, 375, 165, 50);
        
        if ( mouseX>=23 && mouseX<=188 && mouseY>=375 && mouseY<=425){
           if (mousePressed == true){
               ChooseMap chooseMaps = new ChooseMap();
               chooseMaps.runApp();
               this.dispose();
           } 
       }
    }
        public void runApp() {
        String[] processingArgs = { this.getClass().getName() };
        PApplet.runSketch(processingArgs, this);
    }
}
