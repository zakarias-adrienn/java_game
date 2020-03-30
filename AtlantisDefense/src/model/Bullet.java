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
    
    public Bullet()
    {
        pos = new Point();
        bullet = new javax.swing.JLabel();
    }
    
    public Bullet(Integer towerX, Integer towerY)
    {
        pos = new Point(116+28+57*(towerY-1), 100+23+47*(towerX-1));
        //116,100: ott kezdődik a táblázat
        //57, 47: cellák magasság szélessége
        //28, 27: küzépre igazítja a golyót (alapból bal felső sarokba rakná
        bullet = new javax.swing.JLabel();
        bullet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/bubble_bullet.png")));
        Menu.v.getContentPane().add(bullet);
        bullet.setBounds(98, 54, 30, 30);
        bullet.setForeground(Color.WHITE);
        Menu.v.getContentPane().setComponentZOrder(bullet, 0);
        bullet.setLocation(pos.x, pos.y); // Golyót a toronyra igazítja)
    }
    
    public void move(int x, int y) {
//        System.out.println("itt kellene mozogjon a golyó");
        timerForBullet = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pos.x += speed;
                pos.y += speed;
                bullet.setLocation(pos.x, pos.y);
            }
        });
        timerForBullet.start();
        Model.bulletTimers.add(timerForBullet);
        // meg kellene állítani, ha a pályáról leért vagy ha eltalált egy ellenséget
    }
}

// pénz nem áll le!
// GoldTower ne termeljen annyi pénzt
// több életereje legyen
// lövés? -> moveTo fgv -> 
// bulletHell
