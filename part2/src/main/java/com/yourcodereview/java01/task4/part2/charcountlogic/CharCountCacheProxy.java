package com.yourcodereview.java01.task4.part2.charcountlogic;

import java.util.LinkedHashMap;
import java.util.Map;

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
    public String getFrequencyOutput(String key) {
        if (this.cache.containsKey(key)) {
            return this.cache.get(key);
        } 
        String result = charCountLogic.getFrequencyOutput(key);
        put(key, result);
        this.prune();
        return this.cache.get(key);
    }

    private void put(String key, String result) {
        this.cache.put(key, result);
        this.prune();
    }

    private void prune() {
        var cacheIterator = this.cache.keySet().iterator();
        while (this.cache.size() > this.maxSize) {
            cacheIterator.next();
            cacheIterator.remove();
        }
    }

}
