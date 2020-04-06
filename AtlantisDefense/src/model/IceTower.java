package model;

import java.io.IOException;
import java.util.ArrayList;
import view.Board;

public class IceTower extends Tower {
    public IceTower()
    {
        super(false);
        this.life = 100;
        this.spot = null;
        this.price = 100;
        this.distance = 4;
        this.type = "ice";
        bullets = new ArrayList<>();
        bullet_img = "/res/ice_bullet.png";
        speed = 15;
        
    }
    
    @Override
    public String getType(){
        return "ice";
    }
    
    @Override
    public void shoot() {
        Bullet bullet1 = new Bullet(pos.x+1, pos.y+1, bullet_img, speed, distance);
        Bullet bullet2 = new Bullet(pos.x+1, pos.y+1, bullet_img, speed, distance);
        Bullet bullet3 = new Bullet(pos.x+1, pos.y+1, bullet_img, speed, distance);
        Bullet bullet4 = new Bullet(pos.x+1, pos.y+1, bullet_img, speed, distance);
        Bullet bullet5 = new Bullet(pos.x+1, pos.y+1, bullet_img, speed, distance);
        Bullet bullet6 = new Bullet(pos.x+1, pos.y+1, bullet_img, speed, distance);
        Bullet bullet7 = new Bullet(pos.x+1, pos.y+1, bullet_img, speed, distance);
        Bullet bullet8 = new Bullet(pos.x+1, pos.y+1, bullet_img, speed, distance);
        this.life -= 10;
        try {
            Board.cells[this.pos.x][this.pos.y].setLife(this.life);
            Board.cells[this.pos.x][this.pos.y].repaint();
        } catch (IOException ex) {
            System.out.println("Nem sikerült a torony életerejét frissíteni a healthbaron.");
        }
        bullets.add(bullet1);
        bullets.add(bullet2);
        bullets.add(bullet3);
        bullets.add(bullet4);
        bullets.add(bullet5);
        bullets.add(bullet6);
        bullets.add(bullet7);
        bullets.add(bullet8);
        Model.allBullets.addAll(bullets);
        bullet1.move("n");
        bullet2.move("e");
        bullet3.move("s");
        bullet4.move("w");
        bullet5.move("ne");
        bullet6.move("se");
        bullet7.move("sw");
        bullet8.move("nw");
        if(this.life==0){
            Model.towers.remove(this);
            try {
                Board.cells[this.pos.x][this.pos.y].setLife(-1);
            } catch (IOException ex) {
                System.out.println("Torony nem tűnt el ha elfogyott az életereje.");
            }
            Board.resetCellAfterTowerDeath(this.pos.x, this.pos.y);
            Board.cells[this.pos.x][this.pos.y].unsetIsTower();
            this.towerTimer.stop();
        }

    }
}
