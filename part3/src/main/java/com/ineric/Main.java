package com.ineric;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//нету тестов
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    //вынести в проперти
    private static String PARAM_PORT = "-port";
    private static String PARAM_ROOT_DIR = "-root";
    private static final int DEFAULT_PORT = 3443;

    public static void main(String[] args) {
        readParamsAndStart(args);
    }

    private static void readParamsAndStart(String[] args) {
        int port = DEFAULT_PORT;
        //почему Стринг а не СтрингБилдер? что мы знаем о пуле строк
        String rootDir = "";

        try {
            for (int i = 0; i < args.length; i += 2) {
                if (args[i].equals(PARAM_PORT)) {
                    port = Integer.parseInt(args[i + 1]);
                }
                if (args[i].equals(PARAM_ROOT_DIR)) {
                    rootDir = args[i + 1];
                }
            }

            new Server(port, rootDir);
            // на сколько я понимаю NumberFormatException это потомок от RunTimeException, а
            // RunTimeException мы не перехватываем
        } catch (NumberFormatException exception) {
            //не проглатываем эксепшен!
            LOGGER.error("Error read parameters. {}", exception.getMessage());
        }
    }


}
