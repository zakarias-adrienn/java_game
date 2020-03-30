package model;

import java.awt.Point;

public abstract class Enemy {
    protected Point pos; // x, y helyett
    protected int life;
    protected int strength;
    protected int speed;
    protected String type;
    
    public abstract String getType();
    
    public abstract void move();
    
    public void collosion() {
        
    }
    
    public void setPos(int x, int y){
        this.pos.x = x;
        this.pos.y = y;
    }
    
    public int getXPos(){
        return this.pos.x;
    }
    
    public int getYPos(){
        return this.pos.y;
    }
    
    
}
