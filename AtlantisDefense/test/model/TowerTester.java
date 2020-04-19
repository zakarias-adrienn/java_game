package model;


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
        Model model = new Model(menu);
        Board.cells = new Cell[4][4];
        ImageIcon icon = null;
        Board.cells[0][0] = new Cell(icon, 0,0);
        Board.cells[2][2] = new Cell(icon, 2,2);
        Board.cells[2][3] = new Cell(icon, 2,3);
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
    
    @Test
    public void beforeShootTest(){
        Tower t = new IceTower();
        t.setLife(80);
        Enemy ee = new Fugu();
        Enemy f = new SwordFish();
        Model.enemies.add(ee);
        Model.enemies.add(f);
        t.onlyMyEnemiesShooting = false;
        t.setPos(2,2);
        ee.setPos(2,3);
        f.setPos(2,3);
        t.beforeShoot();
        assertEquals("Tower's life decreases after shooting", t.getLife(), 60);
        assertTrue("Both enemies are shooted with 16 bullets", t.bullets.size()==16);
        t.onlyMyEnemiesShooting = true;
        t.beforeShoot();
        assertEquals("Only sword fish is shooted with 8 bullets", t.bullets.size(), 24);
        t = new BubbleTower();
        t.setLife(20);
        t.onlyMyEnemiesShooting = true;
        t.setPos(2,2);
        t.beforeShoot();
        assertEquals("Tower's life decreases after shooting", t.getLife(), 10);
        assertEquals("Only Fugu is shooted with 4 bullets", t.bullets.size(), 4);
        t.onlyMyEnemiesShooting = false;
        t.beforeShoot();
        assertEquals("Both enemies are shooted with 8 bullets", t.bullets.size(), 12);
        t = new ElectricTower();
        t.setLife(30);
        t.setPos(2,2);
        t.beforeShoot();
        assertEquals("Tower's life decreases after shooting", t.getLife(), 10);
        assertEquals("Both enemies are shooted with 16 bullets", t.bullets.size(), 16);
        t.onlyMyEnemiesShooting = true;
        t.beforeShoot();
        assertEquals("None of the enemies is shooted with bullets", t.bullets.size(), 16);
        Model.enemies.remove(ee);
        Model.enemies.remove(f);
        
    }
    
    // BubbleTower - shoot metódusát is tesztelni kell
    @Test
    public void bubbleTowerShootTest() {
        Tower t = new BubbleTower();
        t.setPos(0, 0);
        t.life=70;
        Model.allBullets.clear();
        t.shoot();
        assertTrue("Life of tower should decrease", t.life==60);
        assertEquals("4 bullets should be in Model.allBullets after shooting", Model.allBullets.size(),4);
    }
    
    // ElectricTower shoot metódusát is
    @Test
    public void electricTowerShootTest() {
        Tower t = new ElectricTower();
        t.setPos(0, 0);
        t.life=70;
        Model.allBullets.clear();
        t.shoot();
        assertTrue("Life of tower should decrease", t.life==60);
        // assertEquals("4 bullets should be in Model.allBullets after shooting", Model.allBullets.size(),4);
        // itt is kellene nézni hogy hány golyó lesz
    }
    
    // IceTower shoot metódusát is
    @Test
    public void iceTowerShootTest() {
        Tower t = new IceTower();
        t.setPos(0, 0);
        t.life=70;
        Model.allBullets.clear();
        t.shoot();
        assertTrue("Life of tower should decrease", t.life==60);
        assertEquals("4 bullets should be in Model.allBullets after shooting", Model.allBullets.size(),8);
    }
}
