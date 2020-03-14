package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.swing.JLabel;

public class Model {

    private int money;
    private int time; // ? kell ez ide?
    private ArrayList<Enemy> enemies;
    private ArrayList<Tower> towers;
    private ArrayList<JLabel> towerSpots; // ? JLabel lesz?
    private int round;
    private ArrayList<Bullet> bullets;
    private ArrayList<Pair> route;
    
    private ArrayList<String> readLines;

    public Model() {
        money = 100; // kezdetben mennyi legyen?
        enemies = new ArrayList<>();
        towers = new ArrayList<>();
        towerSpots = new ArrayList<>();
        bullets = new ArrayList<>();
        route = new ArrayList<>();
        round = 1;
        readLines = readFile("src/res/palya.txt");
// sikerült-e a fájlbeolvasás
//        for (int i = 0; i < readLines.size(); i++) {
//            System.out.println(readLines.get(i));
//        }
        int length = parseInt(readLines.get(1).split(" ")[0]);
        int width = parseInt(readLines.get(1).split(" ")[1]);
        int[][] table = new int[length][width];
        for(int i = 2; i<14; ++i){
            String[] row = readLines.get(i).split(" ");
            for(int j = 0; j<width; ++j){
                table[i-2][j] = parseInt(row[j]);
            }
        }
//        for(int i = 0; i<length; ++i){
//            for(int j = 0; j<width; ++j){
//               System.out.print(table[i][j]);
//               System.out.print(" ");
//           }
//           System.out.println("\n");
//        }
        String[] routeString = readLines.get(length+1).split(" ");
        for(int i = 0; i<routeString.length-1; i+=2){
            Pair p = new Pair();
            p.x = parseInt(routeString[i]);
            p.y = parseInt(routeString[i+1]);
            route.add(p);
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
    
    

}
