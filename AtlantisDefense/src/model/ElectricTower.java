package model;

import java.io.IOException;
import java.util.ArrayList;
import view.Board;

public class ElectricTower extends Tower {

    public ElectricTower() {
        super();
        this.life = 100;
        this.spot = null;
        this.price = 100;
        this.distance = 3;
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
        for (int i = 0; i<distance; i++)
        {
            for (int j = 0; j<distance; j++)
            {
                if (!(i == distance/2 && j == distance/2))
                {
                    bullets.add(new Bullet(pos.x+i+1-distance/2, pos.y+j+1-distance/2, bullet_img, speed, distance));
                    
                }
            }
        }
        Model.allBullets.addAll(bullets);
        this.life -= 10;
        try {
            Board.cells[this.pos.x][this.pos.y].setLife(this.life);
            Board.cells[this.pos.x][this.pos.y].repaint();
        } catch (IOException ex) {
            System.out.println("Nem sikerült a torony életerejét frissíteni a healthbaron.");
        }
        for (int i = 0; i<bullets.size(); i++)
        {
            bullets.get(i).direction = "stay";
        }
        this.checkLife();
    }
}
