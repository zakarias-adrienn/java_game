package model;

public abstract class Tower {
    private int life;
    private int spot;
    private int price;
    private int distance;
    private String type;
    
    public abstract void shoot();
}
