package com.yourcodereview.java01.task4.part2.charcountlogic;

public interface CharCount {
    // не нужно указывать static final все поля в интерфейсах
    // автоматически являются публичными public, статическими static и неизменяемыми final
    static final char QUOTES = '"';
    // не нужно указывать паблик все методы в интерфейсах неявно обьявляются как паблик
    //слишком детальное название для интерфейса
    //мне кажется не совсем корректное название
    // мы не просто получаем частоту букв но и рассчитываем ее
    public String getFrequencyOutput(String input);
}
