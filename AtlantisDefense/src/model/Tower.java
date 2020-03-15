package model;

public abstract class Tower {
    protected Integer life;
    protected Integer spot;
    protected Integer price;
    protected Integer distance;
    protected String type;
    
    public abstract void shoot();
}
