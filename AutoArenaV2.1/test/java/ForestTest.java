import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Unit tests for L_Forest class.
 * Tests basic land functionality including tap/untap mechanics and mana production.
 */
public class ForestTest {
    private L_Forest forest;
    
    @Before
    public void setUp() {
        forest = new L_Forest();
    }
    
    @Test
    public void test_constructor_startsUntapped() {
        assertFalse("Forest should start untapped", forest.isTapped());
    }
    
    @Test
    public void test_getMana_returnsGreenMana() {
        char[] mana = forest.getMana();
        assertEquals("Mana production should have 1 symbol", 1, mana.length);
        assertEquals("Forest should produce Green mana", 'G', mana[0]);
    }
    
    @Test
    public void test_getBasicLandType_returnsGreen() {
        char[] landType = forest.getBasicLandType();
        assertEquals("Basic land type should have 1 symbol", 1, landType.length);
        assertEquals("Forest basic land type should be Green", 'G', landType[0]);
    }
    
    @Test
    public void test_tapLand_setsTappedToTrue() {
        forest.tapLand();
        assertTrue("tapLand() should set tapped state to true", forest.isTapped());
    }
    
    @Test
    public void test_untapLand_setsTappedToFalse() {
        forest.tapLand();
        forest.untapLand();
        assertFalse("untapLand() should set tapped state to false", forest.isTapped());
    }
    
    @Test
    public void test_tapLand_multipleTimes() {
        forest.tapLand();
        forest.tapLand(); // Tapping already tapped land
        assertTrue("Land should remain tapped", forest.isTapped());
    }
    
    @Test
    public void test_untapLand_multipleTimes() {
        forest.untapLand(); // Untapping already untapped land
        assertFalse("Land should remain untapped", forest.isTapped());
    }
    
    @Test
    public void test_tapUntapCycle() {
        // Simulate multiple turn cycles
        for (int i = 0; i < 5; i++) {
            forest.tapLand();
            assertTrue("Land should be tapped after tapLand()", forest.isTapped());
            forest.untapLand();
            assertFalse("Land should be untapped after untapLand()", forest.isTapped());
        }
    }
    
    @Test
    public void test_getValue_returnsZero() {
        // Current implementation returns 0
        assertEquals("getValue should return 0", 0, forest.getValue());
    }
}
