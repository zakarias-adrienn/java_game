package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import res.ResourceLoader;
import model.LevelItem;
import model.Model;

public class Board extends JPanel {

    public final Image sand, route, towerPlace, ice, bubble, gold, electric;

    public Board() throws IOException {
        sand = ResourceLoader.loadImage("res/homok.png");
        route = ResourceLoader.loadImage("res/ut.png");
        towerPlace = ResourceLoader.loadImage("res/toronyhely.png");
        ice = ResourceLoader.loadImage("res/ice_bg.png");
        bubble = ResourceLoader.loadImage("res/bubble_bg.png");
        gold = ResourceLoader.loadImage("res/gold_bg.png");
        electric = ResourceLoader.loadImage("res/electric_bg.png");
        setLayout(new GridLayout(13, 15));
        int w = 15;
        int h = 13;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Image img = null;
                LevelItem li = Model.getItem(y, x);
                switch (li) {
                    case SAND:
                        img = sand;
                        break;
                    case TOWER_PLACE:
                        img = towerPlace;
                        break;
                    case ROUTE:
                        img = route;
                        break;
                }
                ImageIcon icon = new ImageIcon(img);
                JLabel thumb = new JLabel();
                thumb.setIcon(icon);
                if (img == towerPlace) {
                    thumb.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            System.out.println("he");
                            int imageNumber = View.checkClick();
                            if(imageNumber!=0){
                                ImageIcon icon;
                                if(imageNumber==1){
                                    icon = new ImageIcon(gold);
                                }
                                else if(imageNumber==2){
                                    icon = new ImageIcon(bubble);
                                }
                                else if(imageNumber==3){
                                    icon = new ImageIcon(electric);
                                }
                                else {
                                    icon = new ImageIcon(ice);
                                }
                                thumb.setIcon(icon);
                            }
                        }
                    });
                }
                this.add(thumb);
            }
        }
    }

//    public boolean refresh() {
//        repaint();
//        return true;
//    }
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D gr = (Graphics2D) g;
//        int w = 15;
//        int h = 13;
//        for (int y = 0; y < h; y++) {
//            for (int x = 0; x < w; x++) {
//                Image img = null;
//                LevelItem li = Model.getItem(y, x);
//                switch (li) {
//                    case SAND:
//                        img = sand;
//                        break;
//                    case TOWER_PLACE:
//                        img = towerPlace;
//                        break;
//                    case ROUTE:
//                        img = route;
//                        break;
//                }
//                if (img == null) {
//                    continue;
//                }
//                gr.drawImage(img, x * 58, y * 58, 58, 58, null);
//            }
//        }
//    }
}
