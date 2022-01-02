package com.yourcodereview.java01.task4.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.yourcodereview.java01.task4.part2.charcountlogic.CharCountCacheProxy;
import com.yourcodereview.java01.task4.part2.charcountlogic.CharCount;
import com.yourcodereview.java01.task4.part2.charcountlogic.CharCountLogic;

public class CharCountApp {
    //кто ловит IOException выше только виртуальная машина
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CharCount charCountLogic = new CharCountLogic();
        System.out.println("Enter a string:");
        System.out.println(charCountLogic.getFrequencyOutput(reader.readLine()));

        //кеш никогда не достигнет максимального значения
        //зачем разделять кеш и расчет??
        // максиальный размер в константы
        CharCount cachedCharCountLogic = new CharCountCacheProxy(32);
        System.out.println("Enter a string:");
        System.out.println(cachedCharCountLogic.getFrequencyOutput(reader.readLine()));
    }

}
