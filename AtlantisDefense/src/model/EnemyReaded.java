package model;

import java.awt.Image;
import java.io.IOException;
import res.ResourceLoader;

public class EnemyReaded {
    public String type;
    public int startTime;
    public int speed;
    private Image img;
    
    public EnemyReaded(String type, int startTime, int speed){
        this.type = type;
        this.startTime = startTime;
        this.speed = speed;
    }
    
    public Image getImage() throws IOException{
        /// TODO: különböző képeket rakni
        if("electric".equals(type)){
            img = ResourceLoader.loadImage("res/eel_small.png");
            return img;
        }
        else if("ice".equals(type)){
            img = ResourceLoader.loadImage("res/swordfish_small.png");
            return img;
        }
        else if("bubble".equals(type)){
            img = ResourceLoader.loadImage("res/fugu_small.png");
            return img;
        }
        else{ //gold
            img = ResourceLoader.loadImage("res/goldfish_small.png");
            System.out.println("gold");
            return img;
        }
        
    }
}
