package model;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JLabel;
import view.Menu;

public class Bullet {
    public Point pos;
    public int speed = 3;
    public int rate;
    public JLabel bullet;
    
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
        System.out.println("" + towerX + ", " + towerY);
    }
    
    public void move(int x, int y) {
        pos.x += speed;
        pos.y += speed;
        bullet.setLocation(pos.x, pos.y);
    }
}
