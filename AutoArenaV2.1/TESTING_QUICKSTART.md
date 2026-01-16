# Quick Start: Adding New Tests

## For a New Card Class

1. **Create a new test file** in `test/java/` named `<CardName>Test.java`

2. **Use this template:**

```java
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class MyNewCardTest {
    private C_MyNewCard card;
    
    @Before
    public void setUp() {
        card = new C_MyNewCard();
    }
    
    @Test
    public void test_constructor_initializesPower() {
        assertEquals("MyNewCard should start with expected power", 
                     expectedPower, card.getPower());
    }
    
    @Test
    public void test_constructor_initializesToughness() {
        assertEquals("MyNewCard should start with expected toughness", 
                     expectedToughness, card.getToughness());
    }
    
    @Test
    public void test_constructor_startsUntapped() {
        assertFalse("MyNewCard should start untapped", card.isTapped());
    }
    
    @Test
    public void test_getMana_returnsCorrectCost() {
        char[] mana = card.getMana();
        // Add assertions for mana cost
    }
    
    @Test
    public void test_getKeywords_containsExpectedKeywords() {
        String[] keywords = card.getKeywords();
        // Add assertions for keywords
    }
    
    @Test
    public void test_declareAttacker_whenUntapped() {
        int damage = card.declareAttacker();
        assertEquals("Should deal damage equal to power", 
                     card.getPower(), damage);
    }
    
    @Test
    public void test_putCounters_increasesStats() {
        card.putCounters(2);
        // Add assertions for power/toughness changes
    }
    
    // Add card-specific ability tests
}
```

3. **Add the test to run_tests.sh:**

Edit line with `org.junit.runner.JUnitCore` and add your test:
```bash
java -cp "bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" \
    org.junit.runner.JUnitCore \
    LlanowarElvesTest \
    ForestTest \
    MyNewCardTest \  # Add this line
    ...
```

4. **Run your tests:**
```bash
./run_tests.sh
```

## Common Test Patterns

### Testing Array Parameters
```java
@Test
public void test_getValue_withBoardState() {
    CREATURE[] myField = {new C_Llanowar_Elves(), new C_Azure_Beastbinder()};
    CREATURE[] oppField = {new C_Llanowar_Elves()};
    
    int value = card.getValue(oppField, myField);
    assertEquals("Should calculate correct value", expectedValue, value);
}
```

### Testing for Specific Instances
```java
@Test
public void test_checkForSpecificCard() {
    OTHER[] permanents = {new E_Innkeepers_Talent()};
    
    boolean found = false;
    for (OTHER other : permanents) {
        if (other instanceof E_Innkeepers_Talent) {
            found = true;
            break;
        }
    }
    assertTrue("Should find Innkeeper's Talent", found);
}
```

### Testing Edge Cases
```java
@Test
public void test_withEmptyArrays() {
    CREATURE[] empty = {};
    int value = card.getValue(empty, empty);
    // Test behavior with empty arrays
}

@Test
public void test_withLargeValues() {
    card.putCounters(100);
    // Test behavior with extreme values
}
```

## Assertions Cheat Sheet

```java
// Equality
assertEquals("message", expected, actual);
assertNotEquals("message", unexpected, actual);

// Boolean conditions
assertTrue("message", condition);
assertFalse("message", condition);

// Null checking
assertNull("message", object);
assertNotNull("message", object);

// Array equality
assertArrayEquals("message", expectedArray, actualArray);

// Object identity
assertSame("message", expected, actual);
assertNotSame("message", unexpected, actual);
```

## Running Individual Tests

```bash
# Compile
javac -cp "bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" -d bin test/java/MyNewCardTest.java

# Run
java -cp "bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore MyNewCardTest
```

## Tips

1. **Test one thing per test** - Each test should verify a single behavior
2. **Use descriptive names** - `test_method_scenario_expectedResult()`
3. **Add comments** - Explain complex test logic
4. **Test edge cases** - Empty arrays, null values, boundary conditions
5. **Keep tests independent** - Each test should work regardless of others
6. **Use @Before** - Set up common test fixtures
7. **Don't test implementation** - Test behavior, not internal state

## Need More Help?

See [TESTING_GUIDE.md](TESTING_GUIDE.md) for comprehensive guidance.
