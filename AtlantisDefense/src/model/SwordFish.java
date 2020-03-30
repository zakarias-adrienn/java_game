package model;

import java.awt.Point;

public class SwordFish extends Enemy {
    
    
    public SwordFish()
    {
        this.pos = new Point(0, 0);
        this.life = 100;
        this.speed = 20;//10;
        this.strength = 4;
        this.type = "ice";
        
    }
    
    @Override
    public void move() {
        
    }
    
    @Override
    public String getType(){
        return "ice";
    }
    
}
