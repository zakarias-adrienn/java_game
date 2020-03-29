package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import res.ResourceLoader;
import model.LevelItem;
import model.Model;
import model.Tower;
import model.GoldTower;
import model.BubbleTower;
import model.ElectricTower;
import model.Enemy;
import model.IceTower;
import model.Pearl;

public class Board extends JPanel {

    public final Image sand, ice, bubble, electric, pearl;
    public static Image route;
    public static Image gold;
    public static Image towerPlace;
    public static Cell[][] cells;
    public static ArrayList<Cell> routeCells;
    View view = null;
    int spotIndex = 0;
    public static ArrayList<Timer> timers;

    public Board(View view) throws IOException {
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.view = view;
        timers = new ArrayList<>();
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
        setVisible(true);
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
                    thumb.setLife(Pearl.getLife());
                    Pearl.setX(y);
                    Pearl.setY(x);
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
                                    tmp.shoot(); // elindul az aranytermelés
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
                                    tmp.setPos(thumb.getXPos() - 1, thumb.getYPos() - 1);
                                    tmp.startTimer();
                                    thumb.setIcon(icon);
                                    thumb.setIsTower();
                                    try {
                                        thumb.setLife(100);
                                    } catch (IOException ex) {
                                        System.out.println("Képbetöltés sikertelen");
                                    }
                                    if (thumb.isTower()) {
                                        thumb.addMouseListener(new MouseAdapter() {
                                            @Override
                                            public void mouseClicked(MouseEvent e) {
                                                System.out.println("Toronyra kattintottak");
                                                if(thumb.isTower()){ //még torony van itt
                                                    View.createDialogForTower();
                                                }
                                            }
                                        });
                                    }
                                    Model.money -= tmp.price;
                                    View.moneyView.setText(Integer.toString(Model.money));
                                    Model.towers.add(spotIndex, tmp);
                                    timers.add(tmp.getTimer());
                                    System.out.println(Model.towers.size());
                                    spotIndex++;
                                } else {
                                    view.createMoneyNotEnoughDialog();
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

    public static void resetCellAfterTowerDeath(int x, int y) {
        ImageIcon icon = new ImageIcon(towerPlace);
        cells[x][y].setIcon(icon);
    }

    // routeCell-t jó sorrendbe kellene tenni!
    public static ArrayList<Cell> orderRouteCells() {
        ArrayList<Cell> routeCopies = new ArrayList<>();
        int k = 0;
        while (k < Model.route.size()) {
            for (int i = 0; i < routeCells.size(); ++i) {
                if (routeCells.get(i).getXPos() == Model.route.get(k).x && routeCells.get(i).getYPos() == Model.route.get(k).y) {
                    routeCopies.add(routeCells.get(i));
                    k = k + 1;
                    break;
                }
            }
        }
        return routeCopies;
    }

    public static void enemyComes(Image img, int speed, Enemy enemy) throws IOException {
        // itt kellene haladjon az enemy adott sebességgel a routeCells cellákon
        // a sebessége fgvében haladjon végig az úton -> routeCells celláinak ikonjait kell lecserélni
        if (!View.paused) {
            System.out.println("ellenség jön");
            ArrayList<Cell> routeCells2 = orderRouteCells();
            ImageIcon icon = new ImageIcon(img);
            ImageIcon icon2 = new ImageIcon(route);
            routeCells2.get(0).setIcon(icon);
            routeCells2.get(0).setLife(100);
            enemy.setPos(routeCells2.get(0).getXPos(), routeCells2.get(0).getYPos());
            Timer timerForEnemy = new Timer(speed, new ActionListener() {
                int i = 1;

                @Override
                public void actionPerformed(ActionEvent e) {
                    // ha nincs megállítva!
                    if (!View.paused) {
                        if (i >= routeCells2.size()) {
                            try {
                                routeCells2.get(i - 1).setIcon(icon2);
                                routeCells2.get(i - 1).setLife(-1);
                                Pearl.decreaseLife();
                                cells[Pearl.getX()][Pearl.getY()].setLife(Pearl.getLife());
                                cells[Pearl.getX()][Pearl.getY()].repaint();
                                Model.enemies.remove(enemy);
                                ((Timer) e.getSource()).stop();
                            } catch (IOException ex) {
                                System.out.println("Képbetöltés nem sikerült!");
                            }
                        } else {
                            routeCells2.get(i - 1).setIcon(icon2);
                            try {
                                routeCells2.get(i - 1).setLife(-1);
                            } catch (IOException ex) {
                                System.out.println("Képbetöltés sikertelen!");
                            }
                            routeCells2.get(i).setIcon(icon);
                            enemy.setPos(routeCells2.get(i).getXPos(), routeCells2.get(i).getYPos());
                            try {
                                routeCells2.get(i).setLife(100);
                            } catch (IOException ex) {
                                System.out.println("Képbetöltés sikertelen!");
                            }
                            i = i + 1;
                        }
                    }
                }
            });
            timerForEnemy.start();
            timers.add(timerForEnemy);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        // NEM AKAR VONALAT RAJZOLNIIII :(
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        g.drawLine(100, 100, 600, 600);

    }
}
