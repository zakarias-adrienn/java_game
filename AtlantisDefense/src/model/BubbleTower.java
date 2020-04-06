package model;

import java.io.IOException;
import java.util.ArrayList;
import view.Board;

public class BubbleTower extends Tower {
    
    public BubbleTower() {
        super(false);
        this.life = 100;
        this.spot = null;
        this.price = 80;
        this.distance = 20;
        this.type = "bubble";
        bullets = new ArrayList<>();
        bullet_img = "/res/bubble_bullet.png";
        speed = 5;
    }
    
    @Override
    public String getType(){
        return "bubble";
    }

    @Override
    public void shoot() {
//        System.out.println(bullets.size());
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
        bullet1.move("n");
        bullet2.move("e");
        bullet3.move("s");
        bullet4.move("w");
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
        /*JLabel tmp = new javax.swing.JLabel();
        //tmp.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        tmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/bubble_bullet.png")));
        Menu.v.getContentPane().add(tmp);
        tmp.setBounds(98, 54, 30, 30);
        tmp.setForeground(Color.WHITE);
        Menu.v.getContentPane().setComponentZOrder(tmp, 0);
        tmp.setLocation(116+28+57*(pos.y-1), 100+23+47*(pos.x-1)); // Golyót a toronyra igazítja
        //116,100: ott kezdődik a táblázat
        //57, 47: cellák magasság szélessége
        //28, 27: küzépre igazítja a golyót (alapból bal felső sarokba rakná)
        System.out.println("" + pos.x + ", " + pos.y);*/
    }
}
