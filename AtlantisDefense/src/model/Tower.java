package model;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public abstract class Tower {

    protected Integer life;
    protected Integer spot;
    public Integer price;
    protected Integer distance;
    protected String type;
    protected Timer towerTimer;
    protected Point pos;
    public ArrayList<Bullet> bullets;

    public Tower() {
        this.pos = new Point();
        this.towerTimer = new Timer(500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (Model.enemies.size() > 0) {
                    for (int i = 0; i < Model.enemies.size(); ++i) {
                        if (enemyGotIn(i)) {
                            System.out.println("Ellenség a hatókörömbe ért!");
                            shoot(); // több golyó is létrejön egy ellenséghez
                        }
                    }
                }
            }
        });
    }

    public boolean enemyGotIn(int index) {
        if ((Model.enemies.get(index).getXPos() == pos.x + 1
                || Model.enemies.get(index).getXPos() == pos.x - 1
                || Model.enemies.get(index).getXPos() == pos.x)
                && (Model.enemies.get(index).getYPos() == pos.y + 1
                || Model.enemies.get(index).getYPos() == pos.y - 1
                || Model.enemies.get(index).getYPos() == pos.y)) {
            return true;
        } else {
            return false;
        }
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
