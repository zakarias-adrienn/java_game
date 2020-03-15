package model;

import jdk.javadoc.internal.doclets.toolkit.util.Utils.Pair;

public class Eel extends Enemy {
    
    
    public Eel()
    {
        this.pos = new Pair(0, 0);
        this.life = 20;
        this.speed = 5;
        this.strength = 4;
        this.type = "electric";
        
    }
    public void move() {
        
    }
    
    public void attack() {
        
    }
}
