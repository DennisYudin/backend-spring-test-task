package com.ineric;


import com.ineric.handler.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static String PARAM_PORT = "-port";
    private static String PARAM_ROOT_DIR = "-root";
    private static final int DEFAULT_PORT = 3443;

    public static void main(String[] args) {
        readParamsAndStart(args);
    }

    private static void readParamsAndStart(String[] args) {
        int port = DEFAULT_PORT;
        String rootDir = "";

        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals(PARAM_PORT)) {
                port = Integer.parseInt(args[i + 1]);
            }
            if (args[i].equals(PARAM_ROOT_DIR)) {
                rootDir = args[i + 1];
            }
        }
        Server server = new Server(port, rootDir);
        LOGGER.debug("Server: " + server);
    }
}
