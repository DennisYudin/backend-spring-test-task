package com.yourcodereview.java01.task4.part2.charcountlogic;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CharCountLogic implements CharCount {
    private static final String DELIMITER = "\n";

    @Override
    public String calculate(String input) {
        return getFormattedOutput(input);
    }
    
    private String getFormattedOutput(String input) {
        return calculateFrequency(input).entrySet().stream()
                    .map(entry -> QUOTES + entry.getKey() + QUOTES + " - " + entry.getValue())
                    .collect(Collectors.joining(DELIMITER)
                    );
    }
    
    private Map<String, Integer> calculateFrequency(String input) {
        return input.chars().mapToObj(c -> String.valueOf((char)c))
                    .collect(Collectors.toMap(
                    key -> key,
                    value -> 1,
                    Integer::sum,
                    LinkedHashMap<String, Integer>::new
                    ));
    }
}

