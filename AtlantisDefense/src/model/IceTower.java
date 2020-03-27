package model;

public class IceTower extends Tower {
    public IceTower()
    {
        super();
        this.life = 100;
        this.spot = null;
        this.price = 100;
        this.distance = 3;
        this.type = "ice";
        
    }
    @Override
    public Bullet shoot() {
        bullet = new Bullet(pos.x, pos.y);
        return bullet;

    }
}
