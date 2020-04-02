package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        Menu.v.getContentPane().add(bullet);
        bullet.setBounds(98, 54, 30, 30);
        bullet.setForeground(Color.WHITE);
        Menu.v.getContentPane().setComponentZOrder(bullet, 0);
        bullet.setLocation(pos.x, pos.y); // Golyót a toronyra igazítja)
    }

    public void move(String direction) {
//        System.out.println("itt kellene mozogjon a golyó");
        timerForBullet = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (elapsedDistance > distance) {
                    pos.y = -50;
                    pos.x = -50;
                    bullet.setLocation(pos.x, pos.y); //csak kihelyezi a golyót a látómezőből. nem elegáns, valahogy meg kéne szüntetni az objektumot. viszont stabilan működik.
                    return;
                }
                if (direction.equals("n")) {
                    //pos.x -= speed;
                    pos.y -= speed;
                } else if (direction.equals("ne")) {
                    pos.x += speed;
                    pos.y -= speed;
                } else if (direction.equals("e")) {
                    pos.x += speed;
                    //pos.y += speed;
                } else if (direction.equals("se")) {
                    pos.x += speed;
                    pos.y += speed;
                } else if (direction.equals("s")) {
                    //pos.x -= speed;
                    pos.y += speed;
                } else if (direction.equals("sw")) {
                    pos.x -= speed;
                    pos.y += speed;
                } else if (direction.equals("w")) {
                    pos.x -= speed;
                    //pos.y -= speed;
                } else if (direction.equals("nw")) {
                    pos.x -= speed;
                    pos.y -= speed;
                }
                else if (direction.equals("stop"))
                {
                    //pos.x = pos.y;
                    //pos.y = pos.x
                    bullet.setLocation(pos.x, pos.y);
                }
                bullet.setLocation(pos.x, pos.y);
                elapsedDistance++;
            }
        });
        timerForBullet.start();
        Model.bulletTimers.add(timerForBullet);
        // meg kellene állítani, ha a pályáról leért vagy ha eltalált egy ellenséget
    }
}
