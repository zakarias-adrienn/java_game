package model;

import javax.swing.JDialog;
import javax.swing.Timer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import view.Menu;
import view.View;

public class ModelTester {
    
    public ModelTester() {
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
    public void findTowerTest(){
        Tower t1 = new GoldTower();
        Tower t2 = new IceTower();
        t1.setPos(1,2);
        t2.setPos(3,4);
        Model.towers.add(t1);
        Model.towers.add(t2);
        assertEquals("findTower should return t1", t1, Model.findTower(1, 2));
        Model.towers.remove(t1);
        assertEquals("findTower should return null", null, Model.findTower(1, 2));
    }
    
    // kell még konstruktor, readFile, gameOver, checkWin metódusok tesztjei
    
    @Test
    public void gameOverTest(){
        Pearl.setLife(10);
        assertFalse("GameOver method should return false", Model.gameOver());
        Pearl.setLife(0);
        assertTrue("GameOver method should return true", Model.gameOver());
    }
}
