# Testing Guide for AutoArena

## Overview
This document provides comprehensive guidance on testing the AutoArena MTG: Arena automation project.

## Current Testing Infrastructure

### Setup
- **Testing Framework**: JUnit 4.13.2
- **Test Location**: `test/java/` directory
- **Dependencies**: 
  - junit-4.13.2.jar
  - hamcrest-core-1.3.jar

### Running Tests

#### Compile and Run All Tests
```bash
# From the AutoArenaV2.1 directory:

# Compile source code
javac -d bin src/*.java

# Compile test code
javac -cp "bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" -d bin test/java/*.java

# Run all tests
java -cp "bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore LlanowarElvesTest ForestTest CreatureTest AzureBeastbinderTest FrenziedBalothTest
```

#### Run Individual Test
```bash
java -cp "bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore <TestClassName>
```

## Test Categories

### 1. Unit Tests for Card Classes

#### Base Classes
- **CARD**: Abstract base class - test concrete implementations
- **CREATURE**: Test common creature functionality
- **LAND**: Test land tap/untap mechanics
- **OTHER**: Test enchantments and other permanent types

#### Concrete Card Implementations
Each card class should have tests for:
- Constructor initialization (power, toughness, mana cost, keywords)
- Getter methods (getPower, getToughness, getKeywords, etc.)
- State changes (tapping, counters, etc.)
- Card-specific abilities

**Example Test Cases for C_Llanowar_Elves**:
- ✅ Test initial power is 1
- ✅ Test initial toughness is 1
- ✅ Test initial tapped state is false
- ✅ Test mana cost is 'G'
- ✅ Test keywords include "mana dork"
- ✅ Test declareAttacker() taps the creature
- ✅ Test declareAttacker() returns 0 when already tapped
- ✅ Test putCounters() increases power and toughness

**Example Test Cases for L_Forest**:
- ✅ Test initial tapped state is false
- ✅ Test tapLand() sets tapped to true
- ✅ Test untapLand() sets tapped to false
- ✅ Test getMana() returns correct mana type
- ✅ Test getBasicLandType() returns correct type

### 2. Integration Tests for Game Logic

#### Base_Phase_Tracker
Test cases should cover:
- Turn progression
- Phase transitions
- Stack management
- Combat phase decisions
- Beginning of combat triggers
- Declare attackers logic
- After blockers declared logic
- Damage step calculations

**Suggested Test Cases**:
- Test turn counter increments properly in firstMain()
- Test turn counter increments properly in secondMain()
- Test declareAttackers() with various creature configurations
- Test beginningOfCombat() trigger ordering
- Test stack state changes

#### Base_Take_Actions
Test cases should cover:
- Card casting (lands vs spells)
- Choice selection
- Priority passing
- Mulligan decisions

**Suggested Test Cases**:
- Test casting a land card
- Test casting a creature card
- Test making choices
- Test priority passing

### 3. Complex Card Interaction Tests

#### C_Azure_Beastbinder
- Test getValue() with different board states
- Test unblockable condition checking
- Test target selection logic
- Test interaction with opponent creatures

#### C_Frenzied_Baloth
- Test getValue() calculation with different opponent fields
- Test enters tapped with E_Authority_of_the_Consul
- Test combat value calculation with E_Innkeepers_Talent
- Test haste allowing immediate attacks

#### E_Innkeepers_Talent
- Test level system
- Test beginning of combat trigger
- Test counter distribution logic
- Test ward keyword granting

### 4. Edge Cases and Boundary Tests

**Common Edge Cases to Test**:
- Empty arrays (no creatures, no lands, etc.)
- Null safety
- Attacking with 0 creatures
- Multiple copies of the same card
- Maximum creature counts
- Counter overflow (very high power/toughness values)
- Tapping already tapped creatures
- Untapping already untapped lands

### 5. State Management Tests

**Board State Tests**:
- Test array manipulation for creatures entering/leaving battlefield
- Test tracking multiple creature states simultaneously
- Test land availability for mana production
- Test keyword tracking and application

### 6. Value Calculation Tests

Many cards have getValue() methods with complex logic:
- Test base values
- Test value adjustments based on board state
- Test value calculations with opponent's board
- Test value calculations with your board
- Test interaction between multiple permanents

## Testing Best Practices

### 1. **Test Naming Convention**
Use descriptive test names: `test_[methodName]_[scenario]_[expectedResult]`
- Example: `test_declareAttacker_whenTapped_returnsZero()`

### 2. **Arrange-Act-Assert Pattern**
```java
@Test
public void test_putCounters_increasePowerAndToughness() {
    // Arrange
    C_Llanowar_Elves elf = new C_Llanowar_Elves();
    
    // Act
    elf.putCounters(2);
    
    // Assert
    assertEquals(3, elf.getPower());
    assertEquals(3, elf.getToughness());
}
```

### 3. **Test Independence**
Each test should be independent and not rely on other tests.

### 4. **Use Setup and Teardown**
```java
@Before
public void setUp() {
    // Initialize test objects
}

@After
public void tearDown() {
    // Clean up after tests
}
```

### 5. **Test Both Success and Failure Paths**
- Test normal operation
- Test error conditions
- Test boundary conditions

## Recommended Test Coverage Goals

### Priority 1 (Critical - Implement First)
- [ ] All concrete CREATURE classes (C_*.java)
- [ ] All concrete LAND classes (L_*.java)
- [ ] Basic combat mechanics (declare attackers, tapping)
- [ ] Counter mechanics (putCounters)

### Priority 2 (Important - Implement Second)
- [ ] All OTHER classes (E_*.java, I_*.java, S_*.java)
- [ ] Base_Phase_Tracker combat logic
- [ ] Value calculation methods
- [ ] Complex card interactions

### Priority 3 (Nice to Have)
- [ ] Base_Take_Actions integration
- [ ] Complete game flow scenarios
- [ ] Performance tests
- [ ] Input validation tests

## Example Test Structure

```java
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ExampleCardTest {
    private ExampleCard card;
    
    @Before
    public void setUp() {
        card = new ExampleCard();
    }
    
    @Test
    public void test_constructor_initializesCorrectly() {
        assertEquals(expectedPower, card.getPower());
        assertEquals(expectedToughness, card.getToughness());
        assertFalse(card.isTapped());
    }
    
    @Test
    public void test_ability_worksAsExpected() {
        // Test implementation
    }
}
```

## Future Testing Enhancements

### 1. **Mocking Framework**
Consider adding Mockito for mocking complex interactions:
- Mock opponent's board state
- Mock Arena screen input
- Mock mouse/keyboard actions

### 2. **Integration Testing**
- Test complete game scenarios
- Test multi-turn sequences
- Test trigger stacks

### 3. **Performance Testing**
- Test decision-making speed
- Test memory usage with large board states
- Test array manipulation efficiency

### 4. **Property-Based Testing**
Consider using QuickCheck-style testing for:
- Random board states
- Random card combinations
- Fuzzing input values

### 5. **Continuous Integration**
Set up automated testing:
- Run tests on every commit
- Generate coverage reports
- Fail builds on test failures

## Common Testing Pitfalls to Avoid

1. **Don't test implementation details** - Test behavior, not internal state
2. **Don't create fragile tests** - Tests shouldn't break with minor refactoring
3. **Don't skip edge cases** - Empty arrays, null values, boundary conditions
4. **Don't test multiple things in one test** - One assertion concept per test
5. **Don't forget to test error conditions** - Not just the happy path

## Resources

- [JUnit 4 Documentation](https://junit.org/junit4/)
- [JUnit Best Practices](https://github.com/junit-team/junit4/wiki/Getting-started)
- [Effective Unit Testing](https://www.artima.com/weblogs/viewpost.jsp?thread=126923)

## Questions or Suggestions?

If you have questions about testing or suggestions for additional test cases, please open an issue on the repository.
