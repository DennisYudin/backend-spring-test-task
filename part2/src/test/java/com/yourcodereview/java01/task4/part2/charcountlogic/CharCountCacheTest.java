package com.yourcodereview.java01.task4.part2.charcountlogic;

import com.yourcodereview.java01.task4.part2.charcountlogic.CharCount;
import com.yourcodereview.java01.task4.part2.charcountlogic.CharCountCacheProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CharCountCacheTest {
    private static final int CACHE_SIZE = 32;
    private CharCount charCount;

    @BeforeEach
    void setup() {
        this.charCount = new CharCountCacheProxy(CACHE_SIZE);
    }

    @Test
    void calculate_ShouldThrowIllegalArgumentException_WhenInputIsEmptyStringOrNull() {

        assertThrows(IllegalArgumentException.class, () -> charCount.calculate(null));
    }
}
