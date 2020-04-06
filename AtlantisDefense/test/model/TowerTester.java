package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
}
