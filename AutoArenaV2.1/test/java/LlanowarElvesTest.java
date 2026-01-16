import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Unit tests for C_Llanowar_Elves class.
 * Tests basic creature functionality including combat, state changes, and counters.
 */
public class LlanowarElvesTest {
    private C_Llanowar_Elves elf;
    
    @Before
    public void setUp() {
        elf = new C_Llanowar_Elves();
    }
    
    @Test
    public void test_constructor_initializesPowerToOne() {
        assertEquals("Llanowar Elves should start with 1 power", 1, elf.getPower());
    }
    
    @Test
    public void test_constructor_initializesToughness() {
        assertEquals("Llanowar Elves should start with 1 toughness", 1, elf.getToughness());
    }
    
    @Test
    public void test_constructor_startsUntapped() {
        assertFalse("Llanowar Elves should start untapped", elf.isTapped());
    }
    
    @Test
    public void test_getMana_returnsGreenMana() {
        char[] mana = elf.getMana();
        assertEquals("Mana cost should have 1 symbol", 1, mana.length);
        assertEquals("Mana cost should be Green", 'G', mana[0]);
    }
    
    @Test
    public void test_getKeywords_containsManaDork() {
        String[] keywords = elf.getKeywords();
        boolean hasManaDork = false;
        for (String keyword : keywords) {
            if (keyword.equals("mana dork")) {
                hasManaDork = true;
                break;
            }
        }
        assertTrue("Keywords should contain 'mana dork'", hasManaDork);
    }
    
    @Test
    public void test_getKeywords_containsGreenManaAbility() {
        String[] keywords = elf.getKeywords();
        boolean hasGreenMana = false;
        for (String keyword : keywords) {
            if (keyword.equals(":G")) {
                hasGreenMana = true;
                break;
            }
        }
        assertTrue("Keywords should contain ':G' ability", hasGreenMana);
    }
    
    @Test
    public void test_declareAttacker_whenUntapped_returnsCorrectPower() {
        int damage = elf.declareAttacker();
        assertEquals("Untapped elf should deal 1 damage when attacking", 1, damage);
    }
    
    @Test
    public void test_declareAttacker_tapsCreature() {
        elf.declareAttacker();
        assertTrue("Declaring attacker should tap the creature", elf.isTapped());
    }
    
    @Test
    public void test_declareAttacker_whenTapped_returnsZero() {
        elf.declareAttacker(); // First attack taps it
        int secondAttack = elf.declareAttacker();
        assertEquals("Tapped elf cannot attack and should return 0", 0, secondAttack);
    }
    
    @Test
    public void test_putCounters_increasePower() {
        elf.putCounters(2);
        assertEquals("Adding 2 counters should increase power to 3", 3, elf.getPower());
    }
    
    @Test
    public void test_putCounters_increaseToughness() {
        elf.putCounters(2);
        assertEquals("Adding 2 counters should increase toughness to 3", 3, elf.getToughness());
    }
    
    @Test
    public void test_putCounters_multipleCounters() {
        elf.putCounters(1);
        elf.putCounters(1);
        assertEquals("Adding counters twice should stack", 3, elf.getPower());
        assertEquals("Adding counters twice should stack", 3, elf.getToughness());
    }
    
    @Test
    public void test_putCounters_affectsCombatDamage() {
        elf.putCounters(3);
        int damage = elf.declareAttacker();
        assertEquals("Elf with +3/+3 counters should deal 4 damage", 4, damage);
    }
    
    @Test
    public void test_getValue_returnsZero() {
        // Current implementation returns 0
        assertEquals("getValue should return 0", 0, elf.getValue());
    }
}
