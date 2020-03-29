package model;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.swing.JLabel;
import view.Board;
import view.View;

public class Model {
    
    public static final int moneyDefaultValue = 200; 

    public static int money;
    public static int pearlPower;
    private int time; // ? kell ez ide?
    public static ArrayList<Enemy> enemies;
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
        money = 200; // kezdetben mennyi legyen?
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

        String[] routeString = readLines.get(length + 2).split(" ");
        for (int i = 0; i < routeString.length - 1; i += 2) {
            Point p = new Point(0, 0);
            p.x = parseInt(routeString[i]);
            p.y = parseInt(routeString[i + 1]);
            route.add(p);
        }

        int enemyNumber = parseInt(readLines.get(length + 3));

        for (int i = 0; i < enemyNumber; ++i) {
            String[] s = readLines.get(length + 4 + i).split(" ");
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
            View.createGameOverDialog();
            Model.money = moneyDefaultValue;
            View.moneyView.setText("" + Model.money);
            Pearl.setLife(100);
            return true;
        }
        return false;
    }

}
