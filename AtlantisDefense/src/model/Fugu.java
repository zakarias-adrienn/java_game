package model;

import java.awt.Point;

public class Fugu extends Enemy {
    
    public Fugu()
    {
        this.pos = new Point(0, 0);
        this.life = 100;
        this.speed = 3;//5;
        this.strength = 4;
        this.type = "bubble";
        
    }
    
    @Override
    public String getType(){
        return "bubble";
    }
}
