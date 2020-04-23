package model;

import java.io.IOException;
import java.util.ArrayList;
import view.Board;

public class IceTower extends Tower {
    public IceTower()
    {
        super();
        this.life = 100;
        this.spot = null;
        this.price = 60;
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
        this.life -= 5;
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
        bullet1.direction = "n";
        bullet2.direction = "e";
        bullet3.direction = "s";
        bullet4.direction = "w";
        bullet5.direction = "ne";
        bullet6.direction = "se";
        bullet7.direction = "sw";
        bullet8.direction = "nw";
        this.checkLife();
    }
}
