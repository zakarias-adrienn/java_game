package model;

import java.util.ArrayList;

public class IceTower extends Tower {
    public IceTower()
    {
        super();
        this.life = 100;
        this.spot = null;
        this.price = 100;
        this.distance = 3;
        this.type = "ice";
        bullets = new ArrayList<>();
        
    }
    @Override
    public void shoot() {
        Bullet bullet = new Bullet(pos.x+1, pos.y+1);
        bullets.add(bullet);
        bullet.move(0, 0);

    }
}
