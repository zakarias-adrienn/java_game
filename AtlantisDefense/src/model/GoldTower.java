package model;

public class GoldTower extends Tower {
    
    public GoldTower()
    {
        super();
        this.raiseMoneyValue = 8;
        this.life = 100;
        this.spot = null;
        this.price = 20;
        this.distance = 0;
        this.type = "gold";
    }
    
    
    @Override
    public String getType(){
        return "gold";
    }
    
    @Override
    public void shoot() {
        Model.money += this.raiseMoneyValue;
    }
}
