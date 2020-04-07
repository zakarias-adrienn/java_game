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

/**
 *
 * @author ZA
 */
public class EnemyTester {
    
    public EnemyTester() {
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
}
