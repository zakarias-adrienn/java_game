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
    
    public Point collosion() {
        //System.out.println("" + (116 + 28 + 57 * (pos.y - 1)) + ", " + (100 + 23 + 47 * (pos.x - 1)));
        //pos = new Point(116 + 28 + 57 * (towerY - 1), 100 + 23 + 47 * (towerX - 1));
        return new Point((116 + 28 + 57 * (pos.y - 1)), (100 + 23 + 47 * (pos.x - 1))); 
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
