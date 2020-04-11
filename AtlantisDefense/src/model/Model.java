package model;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import view.Menu;
import view.View;

public class Model {

    public static final int moneyDefaultValue = 400;

    public static int money;
    public static int pearlPower;
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static ArrayList<Tower> towers = new ArrayList<>();
    private static int enemyNumber = 0;
    public static ArrayList<Point> route = new ArrayList<>();
    private int length;
    private int width;
    private int table[][];
    public static LevelItem[][] level;
    public static ArrayList<EnemyReaded> readedEnemies = new ArrayList<>();
    private ArrayList<String> readLines;
    public static ArrayList<Bullet> allBullets = new ArrayList<>();
    public static int deadEnemyNum;
    public static int arrivedEnemyNum;
    public static Menu menu = null;
    public static int l;
    
    public Model(Menu menu) {
        allBullets = new ArrayList<>();
        pearlPower = 100;
        money = moneyDefaultValue; // kezdetben mennyi legyen?
        enemies = new ArrayList<>();
        towers = new ArrayList<>();
        route = new ArrayList<>();
        readedEnemies = new ArrayList<>();
        deadEnemyNum = 0;
        arrivedEnemyNum = 0;
        this.menu = menu;
 
        if(Menu.level1ButtonClicked){
            readLines = readFile("src/res/palya.txt");
            l = 1;
        }else if(Menu.level2ButtonClicked){
            readLines = readFile("src/res/palya2.txt");
            l = 2;
        }else if(Menu.level3ButtonClicked) {
            readLines = readFile("src/res/palya3.txt");
            l = 3;
        }

        length = parseInt(readLines.get(1).split(" ")[0]);
//        System.out.println("length: "+length);
        width = parseInt(readLines.get(1).split(" ")[1]);
        table = new int[length][width];
        for (int i = 2; i < 15; ++i) {
            String[] row = readLines.get(i).split(" ");
            for (int j = 0; j < width; ++j) {
//                System.out.println(parseInt(row[j]));
                table[i - 2][j] = parseInt(row[j]);
            }
        }
        /*
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.println("i: " + i);
                System.out.println("j: " + j);
                System.out.println(table[i][j]);
            }
        }*/
        
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

        enemyNumber = parseInt(readLines.get(length + 3));

        for (int i = 0; i < enemyNumber; ++i) {
            String[] s = readLines.get(length + 4 + i).split(" ");
            String type = s[0];
            int time = parseInt(s[1]);
            int speed = parseInt(s[2]);
            EnemyReaded e = new EnemyReaded(type, time, speed);
            readedEnemies.add(e);
        }

    }

    // private metódus, ezért nem kell tesztelni
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
    
    public static int getEnemyNum(){
        return Model.enemyNumber;
    }

    public static LevelItem getItem(int row, int col) {
        return level[row][col];
    }
    
    public static Tower findTower(int x, int y) {
        for (int i = 0; i < Model.towers.size(); ++i) {
            if (Model.towers.get(i).getPos().x == x && Model.towers.get(i).getPos().y == y) {
                return Model.towers.get(i);
            }
        }
        return null;
    }

    public static boolean gameOver() {
        if (Pearl.getLife() <= 0) {
            View.createGameOverDialog();
            Model.money = moneyDefaultValue;
            if(View.moneyView!=null){
                View.moneyView.setText("" + Model.money);
            }
            Pearl.setLife(100);
            return true;
        }
        return false;
    }
    
    public static boolean checkWin(){
        if (Pearl.getLife() > 0  && deadEnemyNum+arrivedEnemyNum == enemyNumber){
            View.createWinDialog();
            //menu.setButton(l+1);
            Model.money = moneyDefaultValue;
            if(View.moneyView!=null){
                View.moneyView.setText("" + Model.money);
            }
            Pearl.setLife(100);
            return true;
        }
        return false;
    }

}
