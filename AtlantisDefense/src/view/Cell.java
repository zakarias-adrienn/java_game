package view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Cell extends JLabel {
    private ImageIcon icon;
    private int xPos, yPos;
    
    Cell(ImageIcon icon, int x, int y){
        this.icon = icon;
        this.xPos = x;
        this.yPos = y;
    }
    
    public int getXPos(){
        return xPos;
    }
    
    public int getYPos(){
        return yPos;
    }
    
    
}
