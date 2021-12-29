package com.yourcodereview.java01.task4.part2.charcountlogic;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CharCountLogic implements CharCount {

    @Override
    public String getFrequencyOutput(String input) {
        return getCharsCount(input);
    }
    
    private String getCharsCount(String input) {
        return calculateFrequancy(input).entrySet().stream()
                    .map(entry -> QUOTES + entry.getKey() + QUOTES + " - " + entry.getValue())
                    .collect(Collectors.joining("\n")
                    );
    }
    
    private Map<String, Integer> calculateFrequancy(String input) {
        return input.chars().mapToObj(c -> String.valueOf((char)c))
                    .collect(Collectors.toMap(
                    key -> key,
                    value -> 1,
                    Integer::sum,
                    LinkedHashMap<String, Integer>::new
                    ));
    }
    
}
