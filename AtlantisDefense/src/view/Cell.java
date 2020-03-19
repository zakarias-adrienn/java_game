package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import res.ResourceLoader;

public class Cell extends JLabel {
    private ImageIcon icon;
    private int xPos, yPos;
    public int majom;
    public Image img;
    public boolean toronyLe = false;
    public boolean kagylo = false;
    
    Cell(ImageIcon icon, int x, int y){
        this.icon = icon;
        this.xPos = x;
        this.yPos = y;
        try {
            img = ResourceLoader.loadImage("res/hb100.png");
        } catch (IOException ex) {
            System.out.println("Kép betöltés sikertelen!");
        }
    }
    
    public int getXPos(){
        return xPos+1;
    }
    
    public int getYPos(){
        return yPos+1;
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        if(toronyLe || kagylo){
            super.paintComponent(g);
            g.drawImage(img, 0, 0, null);
        } else {
            super.paintComponent(g);
        }
        
    }
    
    
    
    
}
