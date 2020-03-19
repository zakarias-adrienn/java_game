package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.Timer;
import res.ResourceLoader;
import model.LevelItem;
import model.Model;
import model.Tower;
import model.GoldTower;
import model.BubbleTower;
import model.ElectricTower;
import model.IceTower;

public class Board extends JPanel {

    public final Image sand, towerPlace, ice, bubble, gold, electric, pearl;
    public static Image route;
    public static Cell[][] cells;
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
        boolean r = false;
        boolean kagylo;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                kagylo = false;
                Image img = null;
                LevelItem li = Model.getItem(y, x);
                switch (li) {
                    case SAND:
                        r = false;
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
                        r = false;
                        img = pearl;
                        kagylo = true;
                        break;
                }
                ImageIcon icon = new ImageIcon(img);
                Cell thumb = new Cell(icon, y, x);
                thumb.setIcon(icon);
                if (kagylo) {
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
                }
                this.add(thumb);
            }
        }
    }

    // routeCell-t jó sorrendbe kellene tenni!
    public static ArrayList<Cell> orderRouteCells() {
        ArrayList<Cell> routeCopies = new ArrayList<>();
        int k = 0;
        while (k < Model.route.size()) {
           for(int i = 0; i<routeCells.size(); ++i){
               if(routeCells.get(i).getXPos()==Model.route.get(k).x && routeCells.get(i).getYPos()==Model.route.get(k).y){
                   routeCopies.add(routeCells.get(i));
                   k = k+1;
                   break;
               }
           }
        }
        return routeCopies;
    }

    public static void enemyComes(Image img, int speed) {
        // itt kellene haladjon az enemy adott sebességgel a routeCells cellákon
        // a sebessége fgvében haladjon végig az úton -> routeCells celláinak ikonjait kell lecserélni
        ArrayList<Cell> routeCells2 = orderRouteCells();
        ImageIcon icon = new ImageIcon(img);
        ImageIcon icon2 = new ImageIcon(route);
        routeCells2.get(0).setIcon(icon);
        Timer timerForEnemy = new Timer(speed, new ActionListener() {
            int i = 1;

            @Override
            public void actionPerformed(ActionEvent e) {
                // ha nincs megállítva!
                if (i >= routeCells2.size()) {
                    ((Timer) e.getSource()).stop();
                } else {
                    routeCells2.get(i - 1).setIcon(icon2);
                    routeCells2.get(i).setIcon(icon);
                    i = i + 1;
                }

            }
        });
        timerForEnemy.start();
    }
}
