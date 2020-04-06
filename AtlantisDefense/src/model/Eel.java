package model;

import java.awt.Point;

public class Eel extends Enemy {
    
    
    public Eel()
    {
        this.pos = new Point(0, 0);
        this.life = 100;
        this.speed = 15;//5;
        this.strength = 4;
        this.type = "electric";
        
    }
    
    @Override
    public String getType(){
        return "electric";
    }

}
