package model;

import java.io.IOException;
import java.util.ArrayList;
import view.Board;

public class ElectricTower extends Tower {

    public ElectricTower() {
        super(false);
        this.life = 100;
        this.spot = null;
        this.price = 50;
        this.distance = 5;
        this.type = "electric";
        bullets = new ArrayList<>();
        bullet_img = "/res/electric_bullet.png";
        speed = 2;
    }
    
    @Override
    public String getType(){
        return "electric";
    }
    
    @Override
    public void shoot() {
        Bullet bullet = new Bullet(pos.x+1, pos.y+1, bullet_img, speed);
        this.life -= 10;
        try {
            Board.cells[this.pos.x][this.pos.y].setLife(this.life);
            Board.cells[this.pos.x][this.pos.y].repaint();
        } catch (IOException ex) {
            System.out.println("Nem sikerült a torony életerejét frissíteni a healthbaron.");
        }
        bullets.add(bullet);
        bullet.move("w");
        if(this.life==0){
            Model.towers.remove(this);
            Board.resetCellAfterTowerDeath(this.pos.x, this.pos.y);
            Board.cells[this.pos.x][this.pos.y].unsetIsTower();
            this.towerTimer.stop();
        }
    }
}
