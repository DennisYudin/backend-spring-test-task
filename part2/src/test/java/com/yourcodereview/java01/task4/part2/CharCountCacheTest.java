package com.yourcodereview.java01.task4.part2;

import com.yourcodereview.java01.task4.part2.charcountlogic.CharCount;
import com.yourcodereview.java01.task4.part2.charcountlogic.CharCountCacheProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//тесты лежат не в том пакете
class CharCountCacheTest {
    private static final int CACHE_SIZE = 32;
    private CharCount charCountLogic;

    @BeforeEach
    void setup() {
        this.charCountLogic = new CharCountCacheProxy(CACHE_SIZE);
    }

    //данный тест не проверяет работоспособность кеша
    //слово test_в названии можно не писать и так понятно что это такое
    @Test
    void test_getFrequencyOutput_ShouldReturnCachedResult_WhenPutSameStringsSequencially() {
        String newResult = this.charCountLogic.getFrequencyOutput("str1");
        String cachedResult = this.charCountLogic.getFrequencyOutput("str1");
        // нужно использовать assertEquals
        // == проверяет равество ссылок а не сам обьект
        // где expected value где actual?
        //что за assert?!
        assert (newResult == cachedResult);
    }
    //название не соотвествует тесту
    //по факту мы не сбрасываем весь кеш до нуля
    // а удаляем по одному элементу если вышли за допустимые пределы
    //слово test_в названии можно не писать и так понятно что это такое
    @Test
    void test_getFrequencyOutput_ShouldDropCachedResult_WhenCacheOverflow() {
        String beforeOverflow = this.charCountLogic.getFrequencyOutput("str1");
        for (int i = 0; i < CACHE_SIZE; i++) {
            String dummy = "str #" + i;
            this.charCountLogic.getFrequencyOutput(dummy);
        }
        String afterOverflow = this.charCountLogic.getFrequencyOutput("str1");
        // оператор != не докажет не равество
        //что за assert?!
        assert (beforeOverflow != afterOverflow);
    }
}
