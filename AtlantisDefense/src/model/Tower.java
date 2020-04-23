package model;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Timer;
import view.Board;

public abstract class Tower {

    protected Integer life;
    protected Integer spot;
    public Integer price;
    protected Integer distance;
    protected int raiseMoneyValue = 0; // GoldTowerhez kell csak
    protected String type;
    public Timer towerTimer;
    protected Point pos;
    public boolean onlyMyEnemiesShooting;
    public ArrayList<Bullet> bullets;
    String bullet_img;
    int speed;

    public Tower() {
        this.onlyMyEnemiesShooting = false;
        this.pos = new Point();
    }

    public abstract String getType();

    public int getRaiseMoneyValue() {
        return raiseMoneyValue;
    }

    public void setRaiseMoneyValue(int value) {
        this.raiseMoneyValue = value;
    }

    // toronynál nem talál az index
    public boolean enemyGotIn(int index) {
        if(Model.enemies.isEmpty() || index>=Model.enemies.size() || index<0){
            return false;
        }
        if ((Model.enemies.get(index).getXPos() == this.pos.x + 2
                || Model.enemies.get(index).getXPos() == this.pos.x
                || Model.enemies.get(index).getXPos() == this.pos.x + 1)
                && (Model.enemies.get(index).getYPos() == this.pos.y + 2
                || Model.enemies.get(index).getYPos() == this.pos.y
                || Model.enemies.get(index).getYPos() == this.pos.y + 1)) {
            return true;
        } else {
            return false;
        }
    }

    public void beforeShoot() {
        if (!(this instanceof GoldTower)) {
            if (Model.enemies.size() > 0) {
                if (!this.onlyMyEnemiesShooting) {
                    for (int i = 0; i < Model.enemies.size(); ++i) {
                        if (enemyGotIn(i)) {
                            System.out.println("Ellenség a hatókörömbe ért!");
                            shoot(); // több golyó is létrejön egy ellenséghez
                        }
                    }
                } else {
                    for (int i = 0; i < Model.enemies.size(); ++i) {
                        if (Model.enemies.get(i).getType().equals(this.getType())) {
                            if (enemyGotIn(i)) {
                                shoot();
                            }
                        }
                    }
                }

            }
        }
    }

    public abstract void shoot();


    public void setPos(int xx, int yy) {
        this.pos.x = xx;
        this.pos.y = yy;
    }

    public Point getPos() {
        return pos;
    }

    public void increaseLife() {
        this.life = 100;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getLife() {
        return this.life;
    }

    public void decreaseLife() {
        this.life -= 10;
    }
    
    public void setLife(int life){
        this.life = life;
    }

    public void checkLife() {
        if (this.life == 0) {
            Model.towers.remove(this);
            try {
                Board.cells[this.pos.x][this.pos.y].setLife(-1);
            } catch (IOException ex) {
                System.out.println("Torony nem tűnt el ha elfogyott az életereje.");
            }
            Board.resetCellAfterTowerDeath(this.pos.x, this.pos.y);
            Board.cells[this.pos.x][this.pos.y].unsetIsTower();
        }
    }
}
