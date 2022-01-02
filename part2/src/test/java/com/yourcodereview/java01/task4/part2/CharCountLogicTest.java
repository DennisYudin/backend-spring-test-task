package com.yourcodereview.java01.task4.part2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.yourcodereview.java01.task4.part2.charcountlogic.CharCount;
import com.yourcodereview.java01.task4.part2.charcountlogic.CharCountLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class CharCountLogicTest {
    private static final char QUOTES = '"';
    private CharCount charCountLogic;

    @BeforeEach
    void setup() {
        this.charCountLogic = new CharCountLogic();
    }
    //слово test_в названии можно не писать и так понятно что это такое
    @Test
    void test_getFrequencyOutput_ShouldReturnCorrectFreq_WhenInput_a() {
        String input = "a";
        String expect = QUOTES + "a" + QUOTES + " - 1";
        //charCountLogic.getFrequencyOutput(input) -> это actual значение и указывается assertEquals(expect, actual);
        assertEquals(expect, charCountLogic.getFrequencyOutput(input));
    }

    
    @Test
    void test_getFrequencyOutput_ShouldReturnCorrectFreq_WhenInput_aaaaa() {
        String input = "aaaaa";
        String expect = QUOTES + "a" + QUOTES + " - 5";        
        assertEquals(expect, charCountLogic.getFrequencyOutput(input));
    }
    
    @Test
    void test_getFrequencyOutput_ShouldReturnCorrectFreq_WhenInput_AAAAA() {
        String input = "AAAAA";
        String expect = QUOTES + "A" + QUOTES + " - 5";        
        assertEquals(expect, charCountLogic.getFrequencyOutput(input));
    }
    
    @Test
    void test_getFrequencyOutput_ShouldReturnCorrectFreq_WhenInput_a_A() {
        String input = "a A";
        String expect = QUOTES + "a" + QUOTES + " - 1\n"
                      + QUOTES + " " + QUOTES + " - 1\n"
                      + QUOTES + "A" + QUOTES + " - 1";        
        assertEquals(expect, charCountLogic.getFrequencyOutput(input));
    }
    
    @Test
    void test_getFrequencyOutput_ShouldReturnCorrectFreq_WhenInput_qwee() {
        String input = "qwee";
        String expect = QUOTES + "q" + QUOTES + " - 1\n"
                      + QUOTES + "w" + QUOTES + " - 1\n"
                      + QUOTES + "e" + QUOTES + " - 2";        
        assertEquals(expect, charCountLogic.getFrequencyOutput(input));
    }
    
    
    @Test
    void test_getFrequencyOutput_ShouldReturnCorrectFreq_WhenInput_aAa_BbB_333() {
        String input = "aAa BbB 333";
        String expect = QUOTES + "a" + QUOTES + " - 2\n"
                      + QUOTES + "A" + QUOTES + " - 1\n"
                      + QUOTES + " " + QUOTES + " - 2\n"
                      + QUOTES + "B" + QUOTES + " - 2\n"
                      + QUOTES + "b" + QUOTES + " - 1\n"
                      + QUOTES + "3" + QUOTES + " - 3";        
        assertEquals(expect, charCountLogic.getFrequencyOutput(input));
    }
    
    @Test
    void test_getFrequencyOutput_ShouldReturnCorrectFreq_WhenInputNumbers() {
        String input = "1234567890";
        String expect = QUOTES + "1" + QUOTES + " - 1\n"
                      + QUOTES + "2" + QUOTES + " - 1\n"
                      + QUOTES + "3" + QUOTES + " - 1\n"
                      + QUOTES + "4" + QUOTES + " - 1\n"
                      + QUOTES + "5" + QUOTES + " - 1\n"
                      + QUOTES + "6" + QUOTES + " - 1\n"
                      + QUOTES + "7" + QUOTES + " - 1\n"
                      + QUOTES + "8" + QUOTES + " - 1\n"
                      + QUOTES + "9" + QUOTES + " - 1\n"
                      + QUOTES + "0" + QUOTES + " - 1";       
        assertEquals(expect, charCountLogic.getFrequencyOutput(input));
    }
    
    @Test
    void test_getFrequencyOutput_ShouldReturnCorrectFreq_WhenInputSpecialSymbols() {
        String input = "!@#$%^&";
        String expect = QUOTES + "!" + QUOTES + " - 1\n"
                      + QUOTES + "@" + QUOTES + " - 1\n"
                      + QUOTES + "#" + QUOTES + " - 1\n"
                      + QUOTES + "$" + QUOTES + " - 1\n"
                      + QUOTES + "%" + QUOTES + " - 1\n"
                      + QUOTES + "^" + QUOTES + " - 1\n"
                      + QUOTES + "&" + QUOTES + " - 1";        
        assertEquals(expect, charCountLogic.getFrequencyOutput(input));
    }  
    
    @Test
    void test_getFrequencyOutput_ShouldReturnCorrectFreq_WhenInputCyrillicSymbols_LowerCase() {
        String input = "йцукен";
        String expect = QUOTES + "й" + QUOTES + " - 1\n"
                      + QUOTES + "ц" + QUOTES + " - 1\n"
                      + QUOTES + "у" + QUOTES + " - 1\n"
                      + QUOTES + "к" + QUOTES + " - 1\n"
                      + QUOTES + "е" + QUOTES + " - 1\n"
                      + QUOTES + "н" + QUOTES + " - 1";
        assertEquals(expect, charCountLogic.getFrequencyOutput(input));
    }  
    
    
    @Test
    void test_getFrequencyOutput_ShouldReturnTrue_WhenInputCyrillicSymbols_UpperCase() {
        String input = "ЙЦУКЕН";
        String expect = QUOTES + "Й" + QUOTES + " - 1\n"
                      + QUOTES + "Ц" + QUOTES + " - 1\n"
                      + QUOTES + "У" + QUOTES + " - 1\n"
                      + QUOTES + "К" + QUOTES + " - 1\n"
                      + QUOTES + "Е" + QUOTES + " - 1\n"
                      + QUOTES + "Н" + QUOTES + " - 1";
        assertEquals(expect, charCountLogic.getFrequencyOutput(input));
    } 
}
