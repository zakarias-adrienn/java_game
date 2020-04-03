package model;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Timer;
import view.Board;

public abstract class Tower {

    protected Integer life;
    protected Integer spot;
    public Integer price;
    protected Integer distance;
    protected String type;
    public Timer towerTimer;
    protected Point pos;
    public boolean onlyMyEnemiesShooting;
    public ArrayList<Bullet> bullets;
    String bullet_img;
    int speed;

    public Tower(boolean isGoldTower) {
        this.onlyMyEnemiesShooting = false;
        this.pos = new Point();
        if (!isGoldTower) {
            this.towerTimer = new Timer(500, new ToronyTimer(this));
        } else {
            System.out.println("gold");
        }
    }

    public abstract String getType();
    
    // toronynál nem talál az index
    public boolean enemyGotIn(int index) {
        if ((Model.enemies.get(index).getXPos() == this.pos.x + 2
                || Model.enemies.get(index).getXPos() == this.pos.x 
                || Model.enemies.get(index).getXPos() == this.pos.x +1)
                && (Model.enemies.get(index).getYPos() == this.pos.y + 2
                || Model.enemies.get(index).getYPos() == this.pos.y
                || Model.enemies.get(index).getYPos() == this.pos.y+1)) {
//            System.out.println(Model.enemies.get(index).getXPos());
//            System.out.println(Model.enemies.get(index).getYPos());
//            System.out.println(this.pos.x);
//            System.out.println(this.pos.y);
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

    public Point getPos() {
        return pos;
    }

    public void increaseLife() {
        this.life = 100;
    }

    public int getLife() {
        return this.life;
    }
    
    public void decreaseLife(){
        this.life -= 20;
    }
    
    // csak a goldTowernél használom egyelőre, a többinél a shoot metódusban van, de lehet jó lenne a többinél is kiszedni onnan
    public void checkLife(){
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
    }

    class ToronyTimer implements ActionListener {
        private Tower t;
        
        public ToronyTimer(Tower tower) {
            t = tower;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (Model.enemies.size() > 0) {
                if (!t.onlyMyEnemiesShooting) {
                    for (int i = 0; i < Model.enemies.size(); ++i) {
                        if (enemyGotIn(i)) {
//                            System.out.println("Ellenség a hatókörömbe ért!");
                            shoot(); // több golyó is létrejön egy ellenséghez
                        }
                    }
                } else {
                    for (int i = 0; i < Model.enemies.size(); ++i) {
                        if (Model.enemies.get(i).getType().equals(t.getType())) {
                            if (enemyGotIn(i)) {
                                shoot();
                            }
                        }
                    }
                }
                
            }
        }
    }
}
