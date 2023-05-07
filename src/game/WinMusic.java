
package game;

import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.SoundFile;


public class WinMusic extends PApplet {
    PImage background;
    SoundFile music;

    @Override
    public void settings() {
        size(375, 650);
    }
    
    

    @Override
    public void setup() {
        background=loadImage("images/Pp.png");
        music = new SoundFile(this, "music/Dakiti.wav");
        music.play();

    }

    @Override
    public void draw() {
        background(background);
        
    }
    

    public void runApp() {
        String[] processingArgs = { this.getClass().getName() };
        PApplet.runSketch(processingArgs, this);
    }
}
