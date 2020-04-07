/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import view.Menu;

/**
 *
 * @author ZA
 */
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
}
