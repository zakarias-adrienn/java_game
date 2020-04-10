package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.ImageIcon;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import view.Board;
import view.Cell;
import view.Menu;

/**
 *
 * @author ZA
 */
public class TowerTester {
    
    public TowerTester() {
    }
    
    // annotációk -> @Test
    // assertek elvárt eredmények tesztelése
    // beszédes neve legyen a teszteknek
    
    // összes teszteset előtt
    @BeforeClass
    public static void setUpClass() {
        Menu menu = new Menu();
        Menu.level1ButtonClicked = true;
        // Boardot is valahogy be kell hozni majd
        Model model = new Model(menu);
        Board.cells = new Cell[1][1];
        ImageIcon icon = null;
        Board.cells[0][0] = new Cell(icon, 0,0);
    }
    
    // összes teszteset után
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
    public void TowerCreationTest(){
        Tower t = new GoldTower();
        assertFalse("Newly created tower's onlyMyEnemyShooting is false", t.onlyMyEnemiesShooting);
        assertTrue("Newly created tower is instanceof GoldTower", t instanceof GoldTower);
        t = new IceTower();
        assertTrue("Newly created tower is instanceof IceTower", t instanceof IceTower);
        t = new ElectricTower();
        assertTrue("Newly created tower is instanceof ElectricTower", t instanceof ElectricTower);
        t = new BubbleTower();
        assertTrue("Newly created tower is instanceof BubbleTower", t instanceof BubbleTower);
    }
    
    @Test
    public void increaseLifeTest(){
        Tower t = new IceTower();
        t.increaseLife();
        assertTrue("Value of life becomes 100 after increaseLife method", t.life==100);
    }
    
    @Test
    public void decreaseLifeTest(){
        Tower t = new IceTower();
        t.setLife(60);
        t.decreaseLife();
        assertTrue("Value of life becomes 40 after decreaseLife method", t.life==40);
    }
    
    @Test
    public void checkLifeOfTowerTest(){
        Tower t = new IceTower();
        Model.towers.add(t);
        t.setPos(0, 0);
        t.setLife(0);
        t.checkLife();
        assertTrue("Tower is removed from Model.towers array when its life becomes 0", Model.towers.contains(t)==false);
        assertFalse("On the board the place of tower should not be a towerplace", Board.cells[0][0].isTower());
        assertEquals("On the board the life of the cell of the tower should become -1", Board.cells[0][0].getLife(), -1);
    }
    
    @Test
    public void enemyGotInTest(){
        Tower t = new BubbleTower();
        Enemy e = new GoldFish();
        Model.enemies.add(e);
        e.setPos(2, 3);
        t.setPos(2, 2);
        assertTrue("Enemy should be in the distance of the tower", t.enemyGotIn(0));
        e.setPos(4, 6);
        assertFalse("Enemy should not be in the distance of the tower", t.enemyGotIn(0));
        assertFalse("Enemy should not be in the distance of the tower, too big index", t.enemyGotIn(1));
        assertFalse("Enemy should not be in the distance of the tower, too small index", t.enemyGotIn(-1));
        Model.enemies.remove(e);
        assertFalse("Enemy should not be in the distance of the tower, Model.enemies is empty", t.enemyGotIn(0));
    }
    
    @Test
    public void getTypeTest(){
        Tower t = new GoldTower();
        assertTrue("In case of GoldTower type is gold", t.getType().equals("gold"));
        t = new IceTower();
        assertTrue("In case of IceTower type is ice", t.getType().equals("ice"));
        t = new ElectricTower();
        assertTrue("In case of ElectricTower type is electric", t.getType().equals("electric"));
        t = new BubbleTower();
        assertTrue("In case of BubbleTower type is bubble", t.getType().equals("bubble"));
    }
    
    @Test
    public void goldTowerShootTest() {
        Tower t = new GoldTower();
        Model.money = 10;
        t.shoot();
        assertEquals("After GoldTower shoot money should be 15", Model.money, 15);
    }
    
    // beforeShoot-ot hogy teszteljük? -> shoot-ot hívja
    // BubbleTower - shoot metódusát is tesztelni kell
    // ElectricTower shoot metódusát is
    // IceTower shoot metódusát is
}
