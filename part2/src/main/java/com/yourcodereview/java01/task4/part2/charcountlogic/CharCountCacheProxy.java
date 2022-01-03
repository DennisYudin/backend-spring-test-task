package com.yourcodereview.java01.task4.part2.charcountlogic;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class CharCountCacheProxy implements CharCount {
    private CharCount charCountLogic;
    private int maxSize;
    private Map<String, String> cache;

    public CharCountCacheProxy(int maxSize) {
        this.maxSize = maxSize;
        this.cache = new LinkedHashMap<>();
        this.charCountLogic = new CharCountLogic();
    }

    @Override
    public String calculate(String input) {

        validate(input);

        if (this.cache.containsKey(input)) {
            return this.cache.get(input);
        } 
        String result = charCountLogic.calculate(input);

        cleanCache();

        cache.put(input, result);

        return cache.get(input);
    }

    private void cleanCache() {
        Iterator cacheIterator = cache.keySet().iterator();
        while (cache.size() > maxSize) {
            cacheIterator.next();
            cacheIterator.remove();
        }
    }

    private void validate(String input) {
        Optional.ofNullable(input).orElseThrow(() -> {
            throw new IllegalArgumentException("Empty or null string");
        });
        Optional.of(input).filter(s -> s.isEmpty()).ifPresent(s -> {
            throw new IllegalArgumentException("Empty or null string");
        });
    }
}
