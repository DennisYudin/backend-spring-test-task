package com.yourcodereview.java01.task4.part2;

import java.util.Scanner;

import com.yourcodereview.java01.task4.part2.charcountlogic.CharCountCacheProxy;
import com.yourcodereview.java01.task4.part2.charcountlogic.CharCount;


public class CharCountApp {
    private static final int CACHE_SIZE = 32;

    public static void main(String[] args) {

        CharCount charCount = new CharCountCacheProxy(CACHE_SIZE);

        try (Scanner scan = new Scanner(System.in)) {
            try {
                System.out.println("Enter a string:");
                String input = scan.nextLine();
                System.out.println(charCount.calculate(input));
            } catch (IllegalArgumentException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
