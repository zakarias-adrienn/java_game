package model;

import java.awt.Point;

public abstract class Enemy {
    protected Point pos; // x, y helyett
    protected int life;
    protected int strength;
    protected int speed;
    protected String type;
    
    public abstract void move();
    
    public abstract void attack();
    
    public void collosion() {
        
    }
    
    
}
