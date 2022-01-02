package com.yourcodereview.java01.task4.part2.charcountlogic;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CharCountLogic implements CharCount {

    @Override
    public String getFrequencyOutput(String input) {
        // Почему нету проверки на нал? или пустую строку

        return getCharsCount(input);
    }
    //мне кажется не очень хорошее имя метода лучше бы getFormattedOutput или что то вроде того...
    private String getCharsCount(String input) {
        return calculateFrequancy(input).entrySet().stream()
                //entry.getKey() и entry.getValue() можно было бы вынести в отдельные переменные
                // как character и numberOfTimes для большей понятности...
                    .map(entry -> QUOTES + entry.getKey() + QUOTES + " - " + entry.getValue())
                    .collect(Collectors.joining("\n")
                    );
    }
    // опечатка в слове Frequancy
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
