package model;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import view.Board;
import view.Menu;
import view.View;

public class Model {

    public static int money;
    public static int pearlPower;
    private int time; // ? kell ez ide?
    private ArrayList<Enemy> enemies;
    public static ArrayList<Tower> towers;
    private ArrayList<JLabel> towerSpots; // ? JLabel lesz?
    private Integer numberOfSpots;
    private int round;
    private ArrayList<Bullet> bullets;
    public static ArrayList<Point> route;
    private int length;
    private int width;
    private int table[][];
    public static LevelItem[][] level;
    public static ArrayList<EnemyReaded> readedEnemies;
    private ArrayList<String> readLines;

    public Model() {
        numberOfSpots = 0;
        pearlPower = 100;
        money = 100; // kezdetben mennyi legyen?
        enemies = new ArrayList<>();
        towers = new ArrayList<>();
        towerSpots = new ArrayList<>();
        bullets = new ArrayList<>();
        route = new ArrayList<>();
        readedEnemies = new ArrayList<>();
        round = 1;
        readLines = readFile("src/res/palya.txt");

        length = parseInt(readLines.get(1).split(" ")[0]);
        width = parseInt(readLines.get(1).split(" ")[1]);
        table = new int[length][width];
        for (int i = 2; i < 14; ++i) {
            String[] row = readLines.get(i).split(" ");
            for (int j = 0; j < width; ++j) {
                table[i - 2][j] = parseInt(row[j]);
            }
        }
        level = new LevelItem[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                switch (table[i][j]) {
                    case 0:
                        level[i][j] = LevelItem.SAND;
                        break;
                    case 1:
                        level[i][j] = LevelItem.ROUTE;
                        break;
                    case 2:
                        level[i][j] = LevelItem.TOWER_PLACE;
                        numberOfSpots++;
                        break;
                    case 3:
                        level[i][j] = LevelItem.PEARL;
                        break;
                }

            }
        }

        towers.clear(); //minden értéket null-ra állít

        String[] routeString = readLines.get(length + 1).split(" ");
        for (int i = 0; i < routeString.length - 1; i += 2) {
            Point p = new Point(0, 0);
            p.x = parseInt(routeString[i]);
            p.y = parseInt(routeString[i + 1]);
            route.add(p);
        }

        int enemyNumber = parseInt(readLines.get(length + 2));

        for (int i = 0; i < enemyNumber; ++i) {
            String[] s = readLines.get(length + 3 + i).split(" ");
            String type = s[0];
            int time = parseInt(s[1]);
            int speed = parseInt(s[2]);
            EnemyReaded e = new EnemyReaded(type, time, speed);
            readedEnemies.add(e);
        }

    }

    private ArrayList<String> readFile(String filename) {
        ArrayList<String> records = new ArrayList<String>();
        try {
            File f = new File(filename);
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line);
            }
            reader.close();
            return records;
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }

    public static LevelItem getItem(int row, int col) {
        return level[row][col];
    }

    public static boolean gameOver() {
        if (Pearl.getLife() <= 0) {
            View.timer.stop();
            View.timerForEnemies.stop();
            for(int i = 0; i<Board.timers.size(); ++i){
                Board.timers.get(i).stop();
            }
            System.out.println("GAME OVER");
            // ez lehet a view-ba kellene kerüljön
            int result = JOptionPane.showConfirmDialog(null,
                "A játék sajnos végetért.",
                "FIGYELEM!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION || result==JOptionPane.CLOSED_OPTION){
                Menu.v.setVisible(false);
                Menu.v.dispose();
                Menu menu = new Menu();
                menu.setPreferredSize(new Dimension(1397, 842));
                menu.pack();
                menu.setVisible(true);
            }
            return true;
        }
        return false;
    }

}
