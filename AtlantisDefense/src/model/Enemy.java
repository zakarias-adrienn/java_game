package model;

import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.Timer;
import view.Board;
import view.Menu;
import static view.View.moneyView;

public abstract class Enemy {

    protected Point pos; // x, y helyett
    public int life;
    protected int strength;
    protected int speed;
    protected String type;
    public Timer enemyTimer;

    public abstract String getType();

    public Point collosion() {
        Point ret = null;
        int precision = 15;
        Point enemyAbsoulutePos = new Point((116 + 28 + 57 * (pos.y - 1)), (100 + 23 + 47 * (pos.x - 1)));
        for (int i = 0; i < Model.allBullets.size(); i++) {
            if (Math.abs(Model.allBullets.get(i).pos.x - enemyAbsoulutePos.x) < precision && Math.abs(Model.allBullets.get(i).pos.y - enemyAbsoulutePos.y) < precision) {
//                System.out.println("HIT");
                this.life -= 10;
                try {
                    // itt kell a cellán a healthbart változtatni
                    Board.cells[this.pos.x-1][this.pos.y-1].setLife(this.life);
                } catch (IOException ex) {
                    System.out.println("Nem sikerült az ellenség életerejét frissíteni a healthbaron.");
                }
                Board.cells[this.pos.x-1][this.pos.y-1].repaint();
                this.checkLife();
                ret = new Point(Model.allBullets.get(i).pos.x, Model.allBullets.get(i).pos.y); 
            }
        }
        return ret;
    }
    
    // ez fogja eltűntetni a tábláról
    public void checkLife(){
        if(this.life==0){
            Model.money += 5;
            moneyView.setText("" + Model.money);
            Model.enemies.remove(this);
            Model.deadEnemyNum++;
            Model.checkWin();
            Board.resetCellAfterEnemyDeath(this.pos.x-1, this.pos.y-1);
            try {
                Board.cells[this.pos.x-1][this.pos.y-1].setLife(-1);
            } catch (IOException ex) {
                System.out.println("Ellenség nem tűnt el ha elfogyott az életereje.");
            }
            this.enemyTimer.stop();
        }
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
