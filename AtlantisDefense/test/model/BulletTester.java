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
        Bullet b = new Bullet();
        b.elapsedDistance = 60;
        Model.allBullets.add(b);
        b.move();
        assertFalse("Bullet should not be in allBullets array", Model.allBullets.contains(b));
        b.direction = "n";
        b.elapsedDistance = 5;
        Model.allBullets.add(b);
        b.pos.x = 10;
        b.pos.y = 10;
        b.move();
        assertTrue("Bullet y position should change", b.pos.y==7);
        assertTrue("ElapsedDistance should grow", b.elapsedDistance==6);
        // a többi esetet is ez alapján le kellene tesztelni - ne, e, se, stb az iránya a golyónak
    }
}
