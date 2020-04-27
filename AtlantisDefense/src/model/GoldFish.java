package model;

import java.awt.Point;

public class GoldFish extends Enemy {
    
    
    public GoldFish()
    {
        this.pos = new Point(0, 0);
        this.life = 100;
        this.speed = 3;
        this.strength = 4;
        this.type = "gold";
        
    }
    
    @Override
    public String getType(){
        return "gold";
    }
}
