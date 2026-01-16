import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Generic tests for CREATURE class implementations.
 * Tests common creature functionality across different creature types.
 */
public class CreatureTest {
    
    @Test
    public void test_multipleCreatures_independentStates() {
        C_Llanowar_Elves elf1 = new C_Llanowar_Elves();
        C_Llanowar_Elves elf2 = new C_Llanowar_Elves();
        
        elf1.declareAttacker();
        
        assertTrue("First elf should be tapped", elf1.isTapped());
        assertFalse("Second elf should remain untapped", elf2.isTapped());
    }
    
    @Test
    public void test_multipleCreatures_independentCounters() {
        C_Llanowar_Elves elf1 = new C_Llanowar_Elves();
        C_Llanowar_Elves elf2 = new C_Llanowar_Elves();
        
        elf1.putCounters(3);
        
        assertEquals("First elf should have 4 power", 4, elf1.getPower());
        assertEquals("Second elf should still have 1 power", 1, elf2.getPower());
    }
    
    @Test
    public void test_differentCreatureTypes_differentStats() {
        C_Llanowar_Elves elf = new C_Llanowar_Elves();
        C_Azure_Beastbinder beastbinder = new C_Azure_Beastbinder();
        C_Frenzied_Baloth baloth = new C_Frenzied_Baloth();
        
        // Verify each creature has unique stats
        assertEquals("Elf should be 1/1", 1, elf.getPower());
        assertEquals("Elf should be 1/1", 1, elf.getToughness());
        
        assertEquals("Beastbinder should be 1/3", 1, beastbinder.getPower());
        assertEquals("Beastbinder should be 1/3", 3, beastbinder.getToughness());
        
        assertEquals("Baloth should be 3/2", 3, baloth.getPower());
        assertEquals("Baloth should be 3/2", 2, baloth.getToughness());
    }
    
    @Test
    public void test_creatureArray_canHoldMultipleTypes() {
        CREATURE[] battlefield = new CREATURE[3];
        battlefield[0] = new C_Llanowar_Elves();
        battlefield[1] = new C_Azure_Beastbinder();
        battlefield[2] = new C_Frenzied_Baloth();
        
        assertEquals("Array should hold 3 creatures", 3, battlefield.length);
        assertTrue("First creature should be Llanowar Elves", battlefield[0] instanceof C_Llanowar_Elves);
        assertTrue("Second creature should be Azure Beastbinder", battlefield[1] instanceof C_Azure_Beastbinder);
        assertTrue("Third creature should be Frenzied Baloth", battlefield[2] instanceof C_Frenzied_Baloth);
    }
    
    @Test
    public void test_emptyCreatureArray() {
        CREATURE[] empty = {};
        assertEquals("Empty array should have length 0", 0, empty.length);
    }
    
    @Test
    public void test_counters_largeValues() {
        C_Llanowar_Elves elf = new C_Llanowar_Elves();
        elf.putCounters(100);
        
        assertEquals("Should handle large counter values", 101, elf.getPower());
        assertEquals("Should handle large counter values", 101, elf.getToughness());
    }
    
    @Test
    public void test_polymorphism_creatureAsCard() {
        CARD card = new C_Llanowar_Elves();
        assertTrue("Creature should be instance of CARD", card instanceof CARD);
        assertTrue("Should be able to cast to CREATURE", card instanceof CREATURE);
    }
    
    @Test
    public void test_keywords_notNull() {
        C_Llanowar_Elves elf = new C_Llanowar_Elves();
        C_Azure_Beastbinder beastbinder = new C_Azure_Beastbinder();
        C_Frenzied_Baloth baloth = new C_Frenzied_Baloth();
        
        assertNotNull("Elf keywords should not be null", elf.getKeywords());
        assertNotNull("Beastbinder keywords should not be null", beastbinder.getKeywords());
        assertNotNull("Baloth keywords should not be null", baloth.getKeywords());
    }
    
    @Test
    public void test_mana_notNull() {
        C_Llanowar_Elves elf = new C_Llanowar_Elves();
        C_Azure_Beastbinder beastbinder = new C_Azure_Beastbinder();
        C_Frenzied_Baloth baloth = new C_Frenzied_Baloth();
        
        assertNotNull("Elf mana cost should not be null", elf.getMana());
        assertNotNull("Beastbinder mana cost should not be null", beastbinder.getMana());
        assertNotNull("Baloth mana cost should not be null", baloth.getMana());
    }
    
    @Test
    public void test_combat_sequence() {
        C_Llanowar_Elves elf = new C_Llanowar_Elves();
        
        // Turn 1: Attack
        assertFalse("Should start untapped", elf.isTapped());
        int damage1 = elf.declareAttacker();
        assertEquals("Should deal 1 damage", 1, damage1);
        assertTrue("Should be tapped after attacking", elf.isTapped());
        
        // Turn 2: Can't attack (still tapped)
        int damage2 = elf.declareAttacker();
        assertEquals("Tapped creature can't attack", 0, damage2);
        
        // Untap step simulation would happen here in actual game
        // This test just verifies the tapped state persists
    }
    
    @Test
    public void test_instanceof_checks() {
        C_Llanowar_Elves elf = new C_Llanowar_Elves();
        C_Azure_Beastbinder beastbinder = new C_Azure_Beastbinder();
        
        CREATURE[] myField = {elf, beastbinder};
        
        int beastbinderCount = 0;
        for (CREATURE creature : myField) {
            if (creature instanceof C_Azure_Beastbinder) {
                beastbinderCount++;
            }
        }
        
        assertEquals("Should count 1 beastbinder", 1, beastbinderCount);
    }
}
