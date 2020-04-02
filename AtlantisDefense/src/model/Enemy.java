package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JLabel;
import view.Menu;

public abstract class Enemy {

    protected Point pos; // x, y helyett
    public int life;
    protected int strength;
    protected int speed;
    protected String type;

    public abstract String getType();

    public abstract void move();

    public Point collosion() {
        //System.out.println("" + (116 + 28 + 57 * (pos.y - 1)) + ", " + (100 + 23 + 47 * (pos.x - 1)));
        //pos = new Point(116 + 28 + 57 * (towerY - 1), 100 + 23 + 47 * (towerX - 1));

        int precision = 15;
        Point enemyAbsoulutePos = new Point((116 + 28 + 57 * (pos.y - 1)), (100 + 23 + 47 * (pos.x - 1)));
//        System.out.println(Model.allBullets.size());
        for (int i = 0; i < Model.allBullets.size(); i++) {
            if (Math.abs(Model.allBullets.get(i).pos.x - enemyAbsoulutePos.x) < precision && Math.abs(Model.allBullets.get(i).pos.y - enemyAbsoulutePos.y) < precision) {
                System.out.println("HIT");
                this.life -= 5;
                JLabel hit = new javax.swing.JLabel();
                hit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/hit.png")));
                Menu.v.getContentPane().add(hit);
                hit.setBounds(98, 54, 30, 30);
                hit.setForeground(Color.WHITE);
                Menu.v.getContentPane().setComponentZOrder(hit, 0);
                hit.setLocation(Model.allBullets.get(i).pos.x, Model.allBullets.get(i).pos.y);
               
            }
        }
        return new Point((116 + 28 + 57 * (pos.y - 1)), (100 + 23 + 47 * (pos.x - 1)));
    }

    public void setPos(int x, int y) {
        this.pos.x = x;
        this.pos.y = y;
    }

    public int getXPos() {
        return this.pos.x;
    }

    public int getYPos() {
        return this.pos.y;
    }

}
