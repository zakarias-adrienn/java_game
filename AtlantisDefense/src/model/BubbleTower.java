package model;

import java.io.IOException;
import java.util.ArrayList;
import view.Board;

public class BubbleTower extends Tower {
    
    public BubbleTower() {
        super();
        this.life = 100;
        this.spot = null;
        this.price = 40;
        this.distance = 5;
        this.type = "bubble";
        bullets = new ArrayList<>();
        bullet_img = "/res/bubble_bullet.png";
        speed = 15;
    }
    
    @Override
    public String getType(){
        return "bubble";
    }

    @Override
    public void shoot() {
        Bullet bullet1 = new Bullet(pos.x+1, pos.y+1, bullet_img, speed, distance);
        Bullet bullet2 = new Bullet(pos.x+1, pos.y+1, bullet_img, speed, distance);
        Bullet bullet3 = new Bullet(pos.x+1, pos.y+1, bullet_img, speed, distance);
        Bullet bullet4 = new Bullet(pos.x+1, pos.y+1, bullet_img, speed, distance);
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
        Model.allBullets.addAll(bullets);
        bullet1.direction = "n";
        bullet2.direction = "e";
        bullet3.direction = "s";
        bullet4.direction = "w";
        this.checkLife();
    }
}
