package model;

import java.awt.Component;
import java.awt.Dimension;
import view.Menu;

public class AtlantisDefense {

    public static Menu menu;
    static Component Menu;
    public static boolean level2Opened = false;
    public static boolean level3Opened = false;
    
    public static void main(String[] args) {
        menu = new Menu(); 
        menu.setPreferredSize(new Dimension(1397, 842));
        menu.pack();
        menu.setVisible(true);
    }
    
}
