package model;

import java.awt.Dimension;
import view.Menu;
import view.View;

public class AtlantisDefense {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu menu = new Menu(); 
        menu.setPreferredSize(new Dimension(1397, 842));
        menu.pack();
        menu.setVisible(true);
        
        Model model = new Model();
    }
    
}
