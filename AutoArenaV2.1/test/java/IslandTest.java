import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Unit tests for L_Island class.
 * Tests basic land functionality for Island (blue mana source).
 */
public class IslandTest {
    private L_Island island;
    
    @Before
    public void setUp() {
        island = new L_Island();
    }
    
    @Test
    public void test_constructor_startsUntapped() {
        assertFalse("Island should start untapped", island.isTapped());
    }
    
    @Test
    public void test_getMana_returnsBlueMana() {
        char[] mana = island.getMana();
        assertEquals("Mana production should have 1 symbol", 1, mana.length);
        assertEquals("Island should produce Blue mana", 'U', mana[0]);
    }
    
    @Test
    public void test_getBasicLandType_returnsBlue() {
        char[] landType = island.getBasicLandType();
        assertEquals("Basic land type should have 1 symbol", 1, landType.length);
        assertEquals("Island basic land type should be Blue", 'U', landType[0]);
    }
    
    @Test
    public void test_tapLand_setsTappedToTrue() {
        island.tapLand();
        assertTrue("tapLand() should set tapped state to true", island.isTapped());
    }
    
    @Test
    public void test_untapLand_setsTappedToFalse() {
        island.tapLand();
        island.untapLand();
        assertFalse("untapLand() should set tapped state to false", island.isTapped());
    }
    
    @Test
    public void test_getValue_returnsZero() {
        assertEquals("getValue should return 0", 0, island.getValue());
    }
}
