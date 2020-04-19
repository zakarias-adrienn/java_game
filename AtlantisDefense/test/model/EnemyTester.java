package model;

import javax.swing.ImageIcon;
import javax.swing.Timer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import view.Board;
import view.Cell;
import view.Menu;


public class EnemyTester {
    
    public EnemyTester() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Menu menu = new Menu();
        Menu.level1ButtonClicked = true;
        Model model = new Model(menu);
        Board.cells = new Cell[1][1];
        ImageIcon icon = null;
        Board.cells[0][0] = new Cell(icon, 0,0);
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
    
    @Test
    public void getTypeTest(){
        Enemy e = new GoldFish();
        assertTrue("In case of GoldFish type is gold", e.getType().equals("gold"));
        e = new SwordFish();
        assertTrue("In case of SwordFish type is ice", e.getType().equals("ice"));
        e = new Eel();
        assertTrue("In case of Eel type is electric", e.getType().equals("electric"));
        e = new Fugu();
        assertTrue("In case of Fugu type is bubble", e.getType().equals("bubble"));
    }
    
    @Test
    public void checkLifeTest(){
        Enemy e = new GoldFish();
        e.setPos(1, 1);
        e.enemyTimer = new Timer(100, null);
        e.life = 50;
        Model.enemies.add(e);
        e.checkLife();
        assertTrue("Model.enemies shold contain enemy after checklife", Model.enemies.contains(e));
        e.life = 0;
        e.checkLife();
        assertFalse("Model.enemies shold not contain enemy after checklife", Model.enemies.contains(e));
        assertEquals("Model.deadEnemy number should be 1", Model.deadEnemyNum, 1);
        assertEquals("Place of the enemy should become a piece of route", Board.cells[0][0].getLife(), -1);
    }
    
    // collosion metódus tesztje kell még
    public void collosionTest(){
        //nem tudom hogyan teszteljem, mivel nézzel az ütközést?
    }
}
