package com.ineric.handler;

import java.io.IOException;
import java.net.ServerSocket;

import com.ineric.exceptions.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Server {
    public static Logger LOGGER = LoggerFactory.getLogger(Server.class);

    public Server(int port, String rootDir) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            DirectoryTreeTraversal directoryTraversal = new DirectoryTreeTraversal(rootDir);
            new Thread(directoryTraversal).start();
            while (true) {
                ClientHandler client = new ClientHandler(serverSocket.accept(), this, directoryTraversal);
                new Thread(client).start();
            }
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
            throw new ServerException("Error during Server()", exception);
        }
    }
}
