package com.ineric.handler;

import com.ineric.entities.PassageOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DirectoryTreeTraversal implements Runnable {

    private static Logger LOGGER = LoggerFactory.getLogger(DirectoryTreeTraversal.class);

    private final String rootPath;

    private Queue<PassageOptions> currentPassageOptions = new ConcurrentLinkedQueue<>();
    private ExecutorService executorService;

    public DirectoryTreeTraversal(String rootPath) {
        this.rootPath = rootPath;
        initExecutorService();
    }

    @Override
    public void run() {
        while (!currentPassageOptions.isEmpty()) {
            PassageOptions passageOptions = currentPassageOptions.remove();

            CompletableFuture.supplyAsync(() -> getDirItems(
                    rootPath, passageOptions.getDepth(), passageOptions.getMask()), executorService)
                    .thenAccept(results -> passageOptions.getResults().accept(results));
        }
    }

    public void addPassageOptions(PassageOptions passageOptions) {
        currentPassageOptions.add(passageOptions);
    }

    private static List<String> getDirItems(String rootPath, int depth, String mask) {
        int fullDepth = Math.toIntExact(calcDepth(rootPath) + depth);

        File rootDir = new File(rootPath);
        List<String> result = new ArrayList<>();
        Queue<File> fileTree = new PriorityQueue<>();

        Collections.addAll(fileTree, Objects.requireNonNull(rootDir.listFiles()));

        while (!fileTree.isEmpty()) {
            File currentFile = fileTree.remove();
            if (currentFile == null) {
                throw new IllegalArgumentException("currentFile is null");
            }
            if (currentFile.isDirectory() && (calcDepth(currentFile.getAbsolutePath()) < fullDepth)) {
                Collections.addAll(fileTree, Objects.requireNonNull(currentFile.listFiles()));
            } else if (calcDepth(currentFile.getAbsolutePath()) == fullDepth && isMaskContains(mask, currentFile)) {
                result.add(currentFile.getAbsolutePath());
            }
        }
        return result;
    }

    private void initExecutorService() {
        int cpuCoreCount = Runtime.getRuntime().availableProcessors();
        executorService = Executors.newFixedThreadPool(cpuCoreCount);
    }

    private static long calcDepth(String path) {
        return path.chars().filter(ch -> ch == File.separatorChar).count();
    }

    private static boolean isMaskContains(String mask, File currentFile) {
        return currentFile.getName().toUpperCase().contains(mask.toUpperCase());
    }
}
