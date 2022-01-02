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
        // Почему нету проверки на нал? или пустую строку
        if (this.cache.containsKey(key)) {
            return this.cache.get(key);
        } 
        String result = charCountLogic.getFrequencyOutput(key);
        put(key, result);
        //метод prune() уже вызывается в методе put(key, result);
        this.prune();
        return this.cache.get(key);
    }

    private void put(String key, String result) {
        //сначала нужно проверить не вышли ли мы за пределы максимально допустимого числа в кеше
        // а только потом добавлять
        //для меня не привычно видеть здесь слово this
        // не знаю считается ли это за недочет но просто оставлю это здесь:)
        this.cache.put(key, result);
        this.prune();
    }
    //мне кажется не совсем удачное название для метода
    private void prune() {
        //если честно я не знал что джава 11 ввели var я против его использования по идеалогичесим причинам,
        // сорри указываем конкретный тип (с) даже интересно, узнаешь откуда эта цитата;)
        var cacheIterator = this.cache.keySet().iterator();
        while (this.cache.size() > this.maxSize) {
            cacheIterator.next();
            cacheIterator.remove();
        }
    }

}
