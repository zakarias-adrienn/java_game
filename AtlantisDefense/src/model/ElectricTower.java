package model;

import java.util.ArrayList;

public class ElectricTower extends Tower {

    public ElectricTower() {
        super();
        this.life = 100;
        this.spot = null;
        this.price = 50;
        this.distance = 5;
        this.type = "electric";
        bullets = new ArrayList<>();
    }

    @Override
    public void shoot() {
        Bullet bullet = new Bullet(pos.x+1, pos.y+1);
        bullets.add(bullet);
        bullet.move(0, 0);
    }
}
