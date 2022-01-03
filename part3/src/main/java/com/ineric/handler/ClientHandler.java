package com.ineric.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import com.ineric.entities.PassageOptions;
import com.ineric.exceptions.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ClientHandler implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);

    private static final String COMMAND_DELIMITER = " ";
    private static final String PARAM_DEPTH = "-depth";
    private static final String PARAM_MASK = "-mask";
    private static final int HANDLER_DELAY = 10;
    private static final String LOGGER_REQUEST_PATTERN = "request: {}";

    private final Server server;
    private final DirectoryTreeTraversal directoryTraversal;

    private PrintWriter responseStream;
    private Scanner requestStream;

    public ClientHandler(Socket socket, Server server, DirectoryTreeTraversal directoryTraversal) {
        this.directoryTraversal = directoryTraversal;
        this.server = server;
        try {
            this.responseStream = new PrintWriter(socket.getOutputStream());
            this.requestStream = new Scanner(socket.getInputStream());
        } catch (IOException exception) {
            LOGGER.error(exception.toString());
            throw new HandlerException("Error during ClientHandler()", exception);
        }
    }

    @Override
    public void run() {
        runHandler();
    }

    private void runHandler() {
        while (requestStream.hasNext()) {
            String request = requestStream.nextLine();
            LOGGER.info(LOGGER_REQUEST_PATTERN, request);
            prepareResponse(request);
        }
        try {
            Thread.sleep(HANDLER_DELAY);
        } catch (InterruptedException exception) {
            throw new HandlerException("Error during runHandler()", exception);
        }
    }

    private void prepareResponse(String request) {
        String[] args = request.split(COMMAND_DELIMITER);

        PassageOptions passageOptions = new PassageOptions();
        passageOptions.setResults(this::prepareResult);

        readParamsToOptions(args, passageOptions);

        if (passageOptions.getDepth() != null && passageOptions.getMask() != null) {
            directoryTraversal.addPassageOptions(passageOptions);
        }
    }

    private void readParamsToOptions(String[] input, PassageOptions passageOptions) {
        for (int i = 0; i < input.length; i += 2) {
            if (PARAM_DEPTH.equals(input[i])) {
                int depth = Integer.parseInt(input[i + 1]);

                passageOptions.setDepth(depth);
            }
            if (PARAM_MASK.equals(input[i])) {
                String mask = input[i + 1];

                passageOptions.setMask(mask);
            }
        }
    }

    private void prepareResult(List<String> results) {
        results.forEach(this::sendResponse);
    }

    private void sendResponse(String response) {
        responseStream.println(response);
        responseStream.flush();
    }
}
