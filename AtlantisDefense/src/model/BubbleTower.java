package model;

import java.io.IOException;
import java.util.ArrayList;
import view.Board;

public class BubbleTower extends Tower {
    
    public BubbleTower() {
        super(false);
        this.life = 100;
        this.spot = null;
        this.price = 80;
        this.distance = 3;
        this.type = "bubble";
        bullets = new ArrayList<>();

    }
    
    @Override
    public String getType(){
        return "bubble";
    }

    @Override
    public void shoot() {
        Bullet bullet = new Bullet(pos.x+1, pos.y+1);
        this.life -= 10;
        try {
            Board.cells[this.pos.x][this.pos.y].setLife(this.life);
            Board.cells[this.pos.x][this.pos.y].repaint();
        } catch (IOException ex) {
            System.out.println("Nem sikerült a torony életerejét frissíteni a healthbaron.");
        }
        bullets.add(bullet);
        bullet.move("nw");
        if(this.life==0){
            Model.towers.remove(this);
            Board.resetCellAfterTowerDeath(this.pos.x, this.pos.y);
            Board.cells[this.pos.x][this.pos.y].unsetIsTower();
            this.towerTimer.stop();
        }
        /*JLabel tmp = new javax.swing.JLabel();
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
        System.out.println("" + pos.x + ", " + pos.y);*/
    }
}
