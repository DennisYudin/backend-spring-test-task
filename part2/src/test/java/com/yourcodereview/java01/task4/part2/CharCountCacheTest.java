package com.yourcodereview.java01.task4.part2;

import com.yourcodereview.java01.task4.part2.charcountlogic.CharCount;
import com.yourcodereview.java01.task4.part2.charcountlogic.CharCountCacheProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CharCountCacheTest {
    private static final int CACHE_SIZE = 32;
    private CharCount charCountLogic;

    @BeforeEach
    void setup() {
        this.charCountLogic = new CharCountCacheProxy(CACHE_SIZE);
    }

    @Test
    void test_getFrequencyOutput_ShouldReturnCachedResult_WhenPutSameStringsSequencially() {
        String newResult = this.charCountLogic.getFrequencyOutput("str1");
        String cachedResult = this.charCountLogic.getFrequencyOutput("str1");
        assert (newResult == cachedResult);
    }

    @Test
    void test_getFrequencyOutput_ShouldDropCachedResult_WhenCacheOverflow() {
        String beforeOverflow = this.charCountLogic.getFrequencyOutput("str1");
        for (int i = 0; i < CACHE_SIZE; i++) {
            String dummy = "str #" + i;
            this.charCountLogic.getFrequencyOutput(dummy);
        }
        String afterOverflow = this.charCountLogic.getFrequencyOutput("str1");
        assert (beforeOverflow != afterOverflow);
    }
}
