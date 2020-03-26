package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;
import view.Menu;

public abstract class Tower {

    protected Integer life;
    protected Integer spot;
    public Integer price;
    protected Integer distance;
    protected String type;
    private Timer towerTimer;
    protected Point pos;

    public Tower() {
        this.pos = new Point();
        this.towerTimer = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO!!!!!!! LÖVÉS MEGVALÓSÍTÁSA
                // az összes ellenségeen végig kell menni vagy csak azokon amiket támad
                // kellene tudjon lőni
                if ((Model.enemies.get(0).getXPos() == pos.x + 1 || Model.enemies.get(0).getXPos() == pos.x - 1 || Model.enemies.get(0).getXPos() == pos.x)
                        && (Model.enemies.get(0).getYPos() == pos.y + 1 || Model.enemies.get(0).getYPos() == pos.y - 1 || Model.enemies.get(0).getYPos() == pos.y)) {
                    System.out.println("Ellenség a hatókörömbe ért!");
                    JLabel tmp = new javax.swing.JLabel();
                    tmp.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                    Menu.v.getContentPane().add(tmp);
                    tmp.setBounds(98, 54, 503, 23);
                    tmp.setForeground(Color.WHITE);
                    Menu.v.getContentPane().setComponentZOrder(tmp, 0);
                    tmp.setLocation(15, 200);
                    System.out.println("" + tmp.getX());
                }
            }
        });
    }

    public void startTimer() {
        this.towerTimer.start();
    }

    public abstract void shoot();

    public Timer getTimer() {
        return towerTimer;
    }

    public void setPos(int xx, int yy) {
        this.pos.x = xx;
        this.pos.y = yy;
    }
}
