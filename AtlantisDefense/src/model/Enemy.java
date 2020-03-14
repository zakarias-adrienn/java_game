package model;

public abstract class Enemy {
    private Pair p; // x, y helyett
    private int life;
    private int strength;
    private int speed;
    private String type;
    
    public abstract void move();
    
    public abstract void attack();
    
    public void collosion() {
        
    }
    
    
}
