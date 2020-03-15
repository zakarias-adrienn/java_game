package model;

public abstract class Enemy {
    protected Pair pos; // x, y helyett
    protected int life;
    protected int strength;
    protected int speed;
    protected String type;
    
    public abstract void move();
    
    public abstract void attack();
    
    public void collosion() {
        
    }
    
    
}
