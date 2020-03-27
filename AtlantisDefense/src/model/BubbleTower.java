package model;

import java.awt.Color;
import javax.swing.JLabel;
import view.Menu;

public class BubbleTower extends Tower {

    public BubbleTower() {
        super();
        this.life = 100;
        this.spot = null;
        this.price = 80;
        this.distance = 3;
        this.type = "bubble";

    }

    public void shoot() {
        JLabel tmp = new javax.swing.JLabel();
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
        System.out.println("" + pos.x + ", " + pos.y);
    }
}
