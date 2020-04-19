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
        Model.allBullets.remove(b);
        
        // ne
        b.elapsedDistance = 60;
        Model.allBullets.add(b);
        b.move();
        assertFalse("Bullet should not be in allBullets array", Model.allBullets.contains(b));
        b.direction = "ne";
        b.elapsedDistance = 5;
        Model.allBullets.add(b);
        b.pos.x = 10;
        b.pos.y = 10;
        b.move();
        assertTrue("Bullet x position should change", b.pos.x==13);
        assertTrue("Bullet y position should change", b.pos.y==7);
        assertTrue("ElapsedDistance should grow", b.elapsedDistance==6);
        Model.allBullets.remove(b);
        
        // e
        b.elapsedDistance = 60;
        Model.allBullets.add(b);
        b.move();
        assertFalse("Bullet should not be in allBullets array", Model.allBullets.contains(b));
        b.direction = "e";
        b.elapsedDistance = 5;
        Model.allBullets.add(b);
        b.pos.x = 10;
        b.pos.y = 10;
        b.move();
        assertTrue("Bullet x position should change", b.pos.x==13);
        assertTrue("ElapsedDistance should grow", b.elapsedDistance==6);
        Model.allBullets.remove(b);
        
        // se
        b.elapsedDistance = 60;
        Model.allBullets.add(b);
        b.move();
        assertFalse("Bullet should not be in allBullets array", Model.allBullets.contains(b));
        b.direction = "se";
        b.elapsedDistance = 5;
        Model.allBullets.add(b);
        b.pos.x = 10;
        b.pos.y = 10;
        b.move();
        assertTrue("Bullet x position should change", b.pos.x==13);
        assertTrue("Bullet y position should change", b.pos.y==13);
        assertTrue("ElapsedDistance should grow", b.elapsedDistance==6);
        Model.allBullets.remove(b);
        
        // s
        b.elapsedDistance = 60;
        Model.allBullets.add(b);
        b.move();
        assertFalse("Bullet should not be in allBullets array", Model.allBullets.contains(b));
        b.direction = "s";
        b.elapsedDistance = 5;
        Model.allBullets.add(b);
        b.pos.x = 10;
        b.pos.y = 10;
        b.move();
        assertTrue("Bullet y position should change", b.pos.y==13);
        assertTrue("ElapsedDistance should grow", b.elapsedDistance==6);
        Model.allBullets.remove(b);
        
        // sw
        b.elapsedDistance = 60;
        Model.allBullets.add(b);
        b.move();
        assertFalse("Bullet should not be in allBullets array", Model.allBullets.contains(b));
        b.direction = "sw";
        b.elapsedDistance = 5;
        Model.allBullets.add(b);
        b.pos.x = 10;
        b.pos.y = 10;
        b.move();
        assertTrue("Bullet x position should change", b.pos.x==7);
        assertTrue("Bullet y position should change", b.pos.y==13);
        assertTrue("ElapsedDistance should grow", b.elapsedDistance==6);
        Model.allBullets.remove(b);
        
        // w
        b.elapsedDistance = 60;
        Model.allBullets.add(b);
        b.move();
        assertFalse("Bullet should not be in allBullets array", Model.allBullets.contains(b));
        b.direction = "w";
        b.elapsedDistance = 5;
        Model.allBullets.add(b);
        b.pos.x = 10;
        b.pos.y = 10;
        b.move();
        assertTrue("Bullet x position should change", b.pos.x==7);
        assertTrue("ElapsedDistance should grow", b.elapsedDistance==6);
        Model.allBullets.remove(b);
        
        // nw
        b.elapsedDistance = 60;
        Model.allBullets.add(b);
        b.move();
        assertFalse("Bullet should not be in allBullets array", Model.allBullets.contains(b));
        b.direction = "nw";
        b.elapsedDistance = 5;
        Model.allBullets.add(b);
        b.pos.x = 10;
        b.pos.y = 10;
        b.move();
        assertTrue("Bullet x position should change", b.pos.x==7);
        assertTrue("Bullet y position should change", b.pos.y==7);
        assertTrue("ElapsedDistance should grow", b.elapsedDistance==6);
        Model.allBullets.remove(b);
        
        // stop
        b.elapsedDistance = 60;
        Model.allBullets.add(b);
        b.move();
        assertFalse("Bullet should not be in allBullets array", Model.allBullets.contains(b));
        b.direction = "stop";
        b.elapsedDistance = 5;
        Model.allBullets.add(b);
        b.pos.x = 10;
        b.pos.y = 10;
        b.move();
        assertTrue("Bullet x position shouldn't change", b.pos.x==10);
        assertTrue("Bullet y position shouldn't change", b.pos.y==10);
        assertTrue("ElapsedDistance should grow", b.elapsedDistance==6);
        Model.allBullets.remove(b);
    }
}
