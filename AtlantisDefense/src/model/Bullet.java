package model;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.Timer;
import view.Menu;

public class Bullet {

    public Point pos;
    public int speed = 3;
    public int rate;
    public JLabel bullet;
    public Timer timerForBullet;
    public int distance = 10;
    public int elapsedDistance = 0;
    public String direction = null;

    public Bullet() {
        pos = new Point();
        bullet = new javax.swing.JLabel();
    }

    public Bullet(Integer towerX, Integer towerY, String bullet_img, Integer speed, Integer distance) {
        this.speed = speed;
        this.distance = distance;
        pos = new Point(116 + 28 + 57 * (towerY - 1), 100 + 23 + 47 * (towerX - 1));
        //116,100: ott kezdődik a táblázat
        //57, 47: cellák magasság szélessége
        //28, 27: küzépre igazítja a golyót (alapból bal felső sarokba rakná
        bullet = new javax.swing.JLabel();
        bullet.setIcon(new javax.swing.ImageIcon(getClass().getResource(bullet_img)));
        if(Menu.v!=null){
            Menu.v.getContentPane().add(bullet);
            bullet.setBounds(98, 54, 30, 30);
            bullet.setForeground(Color.WHITE);
            Menu.v.getContentPane().setComponentZOrder(bullet, 0);
            bullet.setLocation(pos.x, pos.y); // Golyót a toronyra igazítja)
        }
    }

    public void move() {
        if (elapsedDistance > distance) {
            pos.y = -50;
            pos.x = -50;
            bullet.setLocation(pos.x, pos.y);
            Model.allBullets.remove(this);
            return;
        }
        if (this.direction.equals("n")) {
            //pos.x -= speed;
            pos.y -= speed;
        } else if (this.direction.equals("ne")) {
            pos.x += speed;
            pos.y -= speed;
        } else if (this.direction.equals("e")) {
            pos.x += speed;
            //pos.y += speed;
        } else if (this.direction.equals("se")) {
            pos.x += speed;
            pos.y += speed;
        } else if (this.direction.equals("s")) {
            //pos.x -= speed;
            pos.y += speed;
        } else if (this.direction.equals("sw")) {
            pos.x -= speed;
            pos.y += speed;
        } else if (this.direction.equals("w")) {
            pos.x -= speed;
            //pos.y -= speed;
        } else if (this.direction.equals("nw")) {
            pos.x -= speed;
            pos.y -= speed;
        } else if (this.direction.equals("stop")) {
            bullet.setLocation(pos.x, pos.y);
        }
        bullet.setLocation(pos.x, pos.y);
        elapsedDistance++;
    }
}
