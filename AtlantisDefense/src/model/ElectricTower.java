package model;

public class ElectricTower extends Tower {

    public ElectricTower() {
        super();
        this.life = 100;
        this.spot = null;
        this.price = 50;
        this.distance = 5;
        this.type = "electric";

    }

    public void shoot() {

    }
}
