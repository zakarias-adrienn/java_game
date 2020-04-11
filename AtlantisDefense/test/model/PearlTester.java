package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PearlTester {
    
    public PearlTester() {
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
    public void decreaseLifeTest() {
        Pearl.setLife(70);
        Pearl.decreaseLife();
        assertEquals("After decreaseLife Pearl's life should be 60", Pearl.getLife(), 60);
        Pearl.setLife(10);
        Pearl.decreaseLife();
        assertEquals("After decreaseLife Pearl's life should be 100", Pearl.getLife(), 100);
    }
}
