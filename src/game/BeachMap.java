package game;

import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class BeachMap extends PApplet {

    Random numeroRandom = new Random();
    
    PImage beginning;
    PImage countLives;
    PImage countBonus;
    PImage countTimeOn;
    PImage background;
    PImage mainCharacter;
    PImage secondCharacter;
    PImage obstacle;
    PImage imagePoints; 
    PImage obstacle2;
    PImage first;
    PImage second;
    PImage hollow;
   
    PVector positionCharacter;
    PVector positionCharacter2;
    PVector positionObstacle;
    PVector positionObstacle2;
    PVector positionPoints;
    PVector positionHollow;
    PVector positionBeginning;
    PVector positionFinal;
   
    int mov =10;
    int numRandom;
    int points = 0;
    int lives = 3; 
    int seconds = 0;
    int gameTime= 0; // count the time of the game when the instruction is repeated
    int by = 0;  // Y position of background 1
    int by2 = -650;  // Y position of background 2
    int speedGame = 4; // Speed game 
    int characterSize = 70;
    int pointsSize = 50; 
    int obstacleSize = 50 ;
    int inmunidad = 70;
    int archerImmuneCounter = 0;
    int[] vidasNumero2 = new int[6];
    
    
    @Override
    public void settings() {
        size(375, 650); // Size of the window
        positionCharacter = new PVector(100 , 500); // initial position of the character
        positionCharacter2 = new PVector(40 , 500); // initial position of the character #2
        positionObstacle = new PVector(90 , 0); // initial position of the obstacle
        positionObstacle2 = new PVector(200 , 0); // initial position of the obstacle #2
        positionPoints = new PVector(270 , 0); // initial position of the points
        positionHollow = new PVector(150 , 0); // initial position of the hollows
        positionBeginning = new PVector(-90 , 300); // initial position of the character
        positionFinal = new PVector(-90 , -100); // initial position of the character
        vidasNumero2[0]=1;//declaration of the player's first 3 lives (1 is life in the array and 0 is nothing)
        vidasNumero2[1]=1;
        vidasNumero2[2]=1;
    }
    

    @Override
    public void setup() {
   
        frameRate(60);
        
        background=loadImage("images/MapaPlaya.png");
        mainCharacter=loadImage("images/Pez.png");
        countLives = loadImage("images/ContadorVidas.png");
        countBonus = loadImage("images/ContadorPuntos.png");
        countTimeOn = loadImage("images/ContadorTiempo.png");
        obstacle = loadImage("images/Obstaculop.png");
        obstacle2 = loadImage("images/Obstaculop2.png");
        imagePoints = loadImage("images/Filete.png");
        secondCharacter = loadImage("images/CpuPez.png");
        first=loadImage("images/Primero.png");
        second=loadImage("images/Segundo.png");
        hollow=loadImage("images/SaltoP.png");
        beginning = loadImage("images/Meta.png");
    }
    
    
    @Override
    public void draw() {
        
        infinityBackground(speedGame);
        image(mainCharacter,positionCharacter.x,positionCharacter.y,70,70);
        image(countLives,40,15,85,35);
        image(countBonus,130,15,85,35);
        image(countTimeOn,260,15,85,35);
        image(obstacle,positionObstacle.x,positionObstacle.y,50,50);
        

        if (seconds>0.3){  // Seconds needed to add the Bonus
            
           image(imagePoints,  positionPoints.x, positionPoints.y,50,50);
           positionPoints.y += speedGame;
        }
        
        if (seconds>1.7){  // Seconds needed to add the Obstacle #2
            
           image(obstacle2,positionObstacle2.x,positionObstacle2.y,50,50);
           positionObstacle2.y += speedGame;
        }
 
        positionObstacle.y += speedGame;
        
        if (seconds>2.7){  // Seconds needed to add the Hollow
            
           image(hollow,positionHollow.x,positionHollow.y,50,50);
           positionHollow.y += speedGame;
        }
        
      

        if(CollisionhollowCpu()){ // Movement of the Cpu
            image(secondCharacter,positionCharacter2.x-35,positionCharacter2.y-10,150,150);
        }else{
            image(secondCharacter,positionCharacter2.x,positionCharacter2.y,70,70);
        }
        
        if (CollisionObs1Cpu()|| CollisionObs2Cpu()){
            if (positionCharacter2.x<=40){
                mov = 10;
            }    
            if (positionCharacter2.x>=280){
                mov = -10;
            }   
            positionCharacter2.x = positionCharacter2.x + mov; 
        }      
        
        
        if (keyPressed == true && key == 'd') { // Movement of the Main Character
            mainCharacter = loadImage("images/Pez2.png");
            if (positionCharacter.x==280){ // Range Movement
                positionCharacter.x=280;
            }else{
                positionCharacter.x += 5;
            }
   
        } else if (keyPressed == true && key == 'a'){
           mainCharacter = loadImage("images/Pez3.png"); 
           if (positionCharacter.x==40){ // Range Movement
                positionCharacter.x=40;
            }else{
                positionCharacter.x -= 5;
            }
           
        } else if (Collisionhollow()){
            image(mainCharacter,positionCharacter.x-35,positionCharacter.y-10,150,150);
        }else{
            mainCharacter = loadImage("images/Pez.png"); 
        }
        

        image(beginning,  positionBeginning.x,positionBeginning.y,600,150);
        positionBeginning.y += speedGame;
        
        if (seconds<=20){
            inmunidad=70;
        }else if(seconds<=40){
            inmunidad=60;
        }else if(seconds<=60){
            inmunidad=50;
        }else if(seconds<=80){
            inmunidad=40;
        }else if(seconds<=100){
            inmunidad=30;
        }
        
        checkCollision(positionCharacter,
                positionObstacle,
                characterSize, 
                obstacleSize, 
                inmunidad);
        checkCollision(positionCharacter,
                positionObstacle2,
                characterSize, 
                obstacleSize,
                inmunidad);
 
        
        fill(90);// Color 
        textSize(15);// Size of the text
        String counterTime= String.valueOf(seconds);// pass seconds to string 
        text(counterTime,298,38);// position
        
        
        int temp=0;
        for (int i = 0; i < vidasNumero2.length; i++) {//convert life of array to variable
            if (vidasNumero2[i]==1){
            temp = temp + vidasNumero2[i];
            }
        }
        lives=temp;
        String counterLives= String.valueOf(lives);// pass lives to string 
        text(counterLives,78,38);// position
        
        
        fill(90);// Color 
        textSize(15);// Size of the text
        String counterPoints= String.valueOf(points);// pass points to string 
        text(counterPoints,168,38);// position
        
       
        
        if (keyPressed == true && key == 's') {
            fill(255);// Color 
            textSize(30);// Size of the text
            String plusone= "+1 point";// pass points to string 
            text(plusone,positionCharacter.x-25,positionCharacter.y-100);// position
        }
            
            
        
        
        numRandom = numeroRandom.nextInt(2);
        repeatPosition(positionObstacle,numRandom);
        repeatPosition(positionPoints,numRandom);
        repeatPosition(positionObstacle2,numRandom);
        repeatPosition(positionHollow,numRandom);
        
        
        
        gameTime++; // Increase game time
        if (gameTime%60==0){ // Increase Seconds
            seconds++;
        }
        if (gameTime%1200==0){ // Increase Speedgame in a factor
            speedGame = speedGame + 2;
        }
        
        
        
        if (positionCharacter2.y<positionCharacter.y){
            image(second,300,550,65,65);
        
        } else{
            image(first,300,550,65,65);
        }
        
        
        
        if (seconds>=79){
            image(beginning,  positionFinal.x,positionFinal.y,600,150);
            positionFinal.y += speedGame;
        }
        
        
        
        if (lives<=0){
            LoseBeachv loseBv = new LoseBeachv();
            loseBv.runApp(); 
            this.dispose();
        }else{
            if (seconds>=80){
                if (positionCharacter2.y<positionCharacter.y){
                    LoseBeach2 loseB2 = new LoseBeach2();
                    loseB2.runApp(); 
                    this.dispose();
                }else{
                    WinBeach winb = new WinBeach();
                    winb.runApp();
                    this.dispose();
                }
            }
        }     
    }
    
   
    
    @Override
    public void keyPressed() {
        
        
        if (keyPressed == true && key == 's') {
            if (CollisionPoints()){
                positionPoints.y = background.height ;
                points++;
                if (points%2==0){
                    for (int i = 0; i < vidasNumero2.length; i++) {
                        if (vidasNumero2[i]==0) {
                            vidasNumero2[i]=1;
                            break;
                        }
                    }
                    
                }
                if (points%1==0){
                    positionCharacter2.y+=20;
                }
                    
            }
        }
    }
    
    
    public void repeatPosition(PVector element, int x){
        if (element.y>=height){// add fillet and garbage every time they pass the length 
            if (x==1){
                element.x=  66+numeroRandom.nextInt(140-66);// random throughout the game
            }else{
                element.x=  200+numeroRandom.nextInt(280-200);// random throughout the game   
            }
        element.y =-5;
       } 
    }
    
    public void infinityBackground(int speed){ // Repeat the background always
        image(background,0,by); 
        image(background,0,by2);
        by = by + speedGame;     // Simulates the movement of the background #1
        by2 = by2 + speedGame;   // Simulates the movement of the background #2
  
        if(by>650){    // set background #1 to the initial position
            by = -650;
        }
        if (by2>650){   // set background #2 to the initial position
            by2 = -650;
        }
    }
    
    public void checkCollision(PVector playerPosition, PVector squarePosition, int playerSize, int squareSize, int m) {
        boolean isColliding = Collision(playerPosition, squarePosition, playerSize, squareSize);
        if(isColliding && !isArcherImmune()) {
            archerImmuneCounter = m;
            for (int i = 0; i < vidasNumero2.length; i++) {// Remove live of the array
                if (vidasNumero2[i]==1) {
                    vidasNumero2[i]=0;
                    break;
                }else{
               
                }
            }
            positionCharacter2.y-=80;
        }

        if (archerImmuneCounter != 0){
            archerImmuneCounter -= 1;
        }
            
    }
       
    public boolean isArcherImmune() {
        return archerImmuneCounter != 0;
    }
    
    public static boolean Collision(PVector obj1Position,
            PVector obj2Position,
            int obj1Size,
            int obj2Size) {
        return !(
            obj1Position.y + obj1Size < obj2Position.y 
            || obj1Position.y > obj2Position.y + obj2Size
            || obj1Position.x > obj2Position.x + obj2Size 
            || obj1Position.x + obj1Size < obj2Position.x
        );  
    }
    
    
    
    public boolean CollisionPoints() { 
       return  Collision(positionCharacter, positionPoints, characterSize,pointsSize);
    }
    
    public boolean Collisionhollow() { 
       return  Collision(positionCharacter, positionHollow, characterSize,pointsSize);
    }
    
    public boolean CollisionhollowCpu() { 
       return  Collision(positionCharacter2, positionHollow, characterSize,pointsSize);
    }
    
    public boolean CollisionObs1Cpu() { 
       return  Collision(positionCharacter2, positionObstacle, characterSize,pointsSize);
    }
    
    public boolean CollisionObs2Cpu() { 
       return  Collision(positionCharacter2, positionObstacle2, characterSize,pointsSize);
    }
    

    public void runApp() {  
        String[] processingArgs = { this.getClass().getName() };
        PApplet.runSketch(processingArgs, this);
    }
}
