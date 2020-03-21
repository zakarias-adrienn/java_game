package model;

import java.awt.Dimension;
import view.Menu;

public class AtlantisDefense {

    /**
     * @param args the command line arguments
     */
    public static Menu menu;
    public static Model model;
    
    public static void main(String[] args) {
        menu = new Menu(); 
        menu.setPreferredSize(new Dimension(1397, 842));
        menu.pack();
        menu.setVisible(true);
        
        model = new Model();
    }
    
}
