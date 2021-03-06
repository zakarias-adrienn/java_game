package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import res.ResourceLoader;

public class Cell extends JLabel {

    private ImageIcon icon;
    private int xPos, yPos;
    public Image img;
    private int life = -1;
    private boolean isTower = false;
    public boolean wasJustPlaced = false;
    public boolean isMouseListenerActive = false;
    public boolean isPearl = false;

    public Cell(ImageIcon icon, int x, int y) {
        this.icon = icon;
        this.xPos = x;
        this.yPos = y;
    }

    public void getImage() throws IOException {
        if(this.life==-1){
            this.img = null;
        }else if (this.life == 0 && (this.isTower || this.wasJustPlaced || this.isPearl)) {
            this.img = ResourceLoader.loadImage("res/toronyhely.png");
        } else if (this.life == 0) {
            this.img = ResourceLoader.loadImage("res/ut.png");
        } else if (this.life > 0 && this.life < 10) {
            this.img = ResourceLoader.loadImage("res/hb0.png");
        } else if (this.life >= 10 && this.life < 20) {
            this.img = ResourceLoader.loadImage("res/hb10.png");
        } else if (this.life >= 20 && this.life < 30) {
            this.img = ResourceLoader.loadImage("res/hb20.png");
        } else if (this.life >= 30 && this.life < 40) {
            this.img = ResourceLoader.loadImage("res/hb30.png");
        } else if (this.life >= 40 && this.life < 50) {
            this.img = ResourceLoader.loadImage("res/hb40.png");
        } else if (this.life >= 50 && this.life < 60) {
            this.img = ResourceLoader.loadImage("res/hb50.png");
        } else if (this.life >= 60 && this.life < 70) {
            this.img = ResourceLoader.loadImage("res/hb60.png");
        } else if (this.life >= 70 && this.life < 80) {
            this.img = ResourceLoader.loadImage("res/hb70.png");
        } else if (this.life >= 80 && this.life < 90) {
            this.img = ResourceLoader.loadImage("res/hb80.png");
        } else if (this.life >= 90 && this.life < 100) {
            this.img = ResourceLoader.loadImage("res/hb90.png");
        } else {
            this.img = ResourceLoader.loadImage("res/hb100.png");
        }
    }

    public int getXPos() {
        return xPos + 1;
    }

    public int getYPos() {
        return yPos + 1;
    }

    public void setLife(int life) throws IOException {
        this.life = life;
        getImage();
    }
    
    public int getLife(){
        return this.life;
    }
    
    public void setIsTower(){
        this.isTower = true;
    }
    
    public void unsetIsTower(){
        this.isTower = false;
    }
    
    public boolean isTower(){
        return this.isTower;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        if (this.life>=0) {
            super.paintComponent(g);
            g.drawImage(this.img, 0, 0, null);
        } else {
            super.paintComponent(g);
        }
    }
}
