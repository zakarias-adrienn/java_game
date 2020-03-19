package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import res.ResourceLoader;
import model.LevelItem;
import model.Model;
import model.Tower;
import model.GoldTower;
import model.BubbleTower;
import model.ElectricTower;
import model.IceTower;

public class Board extends JPanel {

    public final Image sand, route, towerPlace, ice, bubble, gold, electric, pearl;
    public Cell[][] cells;
    public static ArrayList<Cell> routeCells;
    View view = null;
    int spotIndex = 0;
    

    public Board(View view) throws IOException {
        this.view = view;
        sand = ResourceLoader.loadImage("res/homok.png");
        route = ResourceLoader.loadImage("res/ut.png");
        towerPlace = ResourceLoader.loadImage("res/toronyhely.png");
        ice = ResourceLoader.loadImage("res/ice_bg.png");
        bubble = ResourceLoader.loadImage("res/bubble_bg.png");
        gold = ResourceLoader.loadImage("res/gold_bg.png");
        electric = ResourceLoader.loadImage("res/electric_bg.png");
        pearl = ResourceLoader.loadImage("res/pearl_small.png");
        cells = new Cell[13][15];
        routeCells = new ArrayList<>();
        setLayout(new GridLayout(13, 15));
        int w = 15;
        int h = 13;
        boolean r;
        boolean kagylo;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                kagylo = false;
                r = false;
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
                        r = true;
                        img = route;
                        break;
                    case PEARL:
                        img = pearl;
                        kagylo = true;
                        break;
                }
                ImageIcon icon = new ImageIcon(img);
                Cell thumb = new Cell(icon, y, x);
                thumb.setIcon(icon);
                if(kagylo){
                    thumb.kagylo = true;
                }
                if (r) {
                    routeCells.add(thumb);
                }
                cells[y][x] = thumb;
                cells[y][x].setSize(58, 58);

                if (img == towerPlace) {
                    thumb.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            System.out.println("he");
                            int imageNumber = View.checkClick();
                            if (imageNumber != 0) {
                                ImageIcon icon;
                                Tower tmp;
                                if (imageNumber == 1) {
                                    icon = new ImageIcon(gold);
                                    tmp = new GoldTower();
                                } else if (imageNumber == 2) {
                                    icon = new ImageIcon(bubble);
                                    tmp = new BubbleTower();
                                } else if (imageNumber == 3) {
                                    icon = new ImageIcon(electric);
                                    tmp = new ElectricTower();
                                } else {
                                    icon = new ImageIcon(ice);
                                    tmp = new IceTower();
                                }
                                if (Model.money - tmp.price >= 0) {
                                    thumb.setIcon(icon);
                                    thumb.toronyLe = true;
                                    Model.money -= tmp.price;
                                    System.out.println(Model.money);
                                    View.moneyView.setText(Integer.toString(Model.money));
                                    Model.towers.add(spotIndex, tmp);
                                    System.out.println(Model.towers);
                                    spotIndex++;
                                }
                                view.resetBorder();
                            }
                        }
                    });
                    //View.resetBorder();
                }
                this.add(thumb);
            }
        }
    }
    
    /*public static void resetBorder(){
        View.resetBorder();
    }*/

    public static void enemyComes(Image img, int speed) {
        // itt kellene haladjon az enemy adott sebességgel a routeCells cellákon
        // a sebessége fgvében haladjon végig az úton -> routeCells celláinak ikonjait kell lecserélni
        ImageIcon icon = new ImageIcon(img);
        routeCells.get(0).setIcon(icon);
    }

}
