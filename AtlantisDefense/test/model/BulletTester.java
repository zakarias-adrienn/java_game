package model;

import java.awt.Point;
import javax.swing.JLabel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class BulletTester {
    
    public BulletTester() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    // kell a move metódus tesztje és a konstruktor -> 
    @Test
    public void BulletConstructor() {
        Point pos;
        JLabel bullet;
        pos = new Point();
        bullet = new javax.swing.JLabel();
    }
    
    @Test
    public void moveTest(){
        //Bullet.move(); //static miatt nem tudom hogyan hívjam, mit teszteljek rajta?
    }
}
