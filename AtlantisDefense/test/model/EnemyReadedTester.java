package model;

import java.awt.Image;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import res.ResourceLoader;


public class EnemyReadedTester {
    
    public EnemyReadedTester() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void getImageTest() throws IOException{
        Image gold, electric, ice, bubble;
        gold = ResourceLoader.loadImage("res/goldfish_small.png");
        electric = ResourceLoader.loadImage("res/eel_small.png");
        ice = ResourceLoader.loadImage("res/swordfish_small.png");
        bubble = ResourceLoader.loadImage("res/fugu_small.png");
        EnemyReaded e = new EnemyReaded("electric", 100, 200);
        // hogy lehet képek EGYENLŐSÉGÉT VIZSGÁLNI?
//        assertTrue("Image should be electric", e.getImage()==electric);
//        e = new EnemyReaded("ice", 100, 200);
//        assertEquals("Image should be ice", e.getImage(), ice);
//        e = new EnemyReaded("gold", 100, 200);
//        assertEquals("Image should be gold", e.getImage(), gold);
//        e = new EnemyReaded("bubble", 100, 200);
//        assertEquals("Image should be bubble", e.getImage(), bubble);
//        
    }
}
