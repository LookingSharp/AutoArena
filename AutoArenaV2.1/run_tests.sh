#!/bin/bash

# AutoArena Test Runner Script
# This script compiles and runs all JUnit tests for the AutoArena project

set -e  # Exit on error

echo "AutoArena Test Suite"
echo "===================="
echo ""

# Navigate to project directory
cd "$(dirname "$0")"

# Colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${YELLOW}Step 1: Compiling source code...${NC}"
javac -d bin src/*.java
if [ $? -eq 0 ]; then
    echo -e "${GREEN}✓ Source compilation successful${NC}"
else
    echo -e "${RED}✗ Source compilation failed${NC}"
    exit 1
fi

echo ""
echo -e "${YELLOW}Step 2: Compiling test code...${NC}"
javac -cp "bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" -d bin test/java/*.java
if [ $? -eq 0 ]; then
    echo -e "${GREEN}✓ Test compilation successful${NC}"
else
    echo -e "${RED}✗ Test compilation failed${NC}"
    exit 1
fi

echo ""
echo -e "${YELLOW}Step 3: Running tests...${NC}"
echo ""

# Run all test classes
java -cp "bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" \
    org.junit.runner.JUnitCore \
    LlanowarElvesTest \
    ForestTest \
    IslandTest \
    AzureBeastbinderTest \
    FrenziedBalothTest \
    CreatureTest

if [ $? -eq 0 ]; then
    echo ""
    echo -e "${GREEN}✓ All tests passed!${NC}"
else
    echo ""
    echo -e "${RED}✗ Some tests failed${NC}"
    exit 1
fi
