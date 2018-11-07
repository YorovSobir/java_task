package ru.dohod.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FindFileCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(FindFileCommand.class);
    private String absolutePath;
    private String fileName;

    public FindFileCommand(String absolutePath, String fileName) {
        this.absolutePath = absolutePath;
        this.fileName = fileName;
    }

    public void run() {
        LOGGER.info(String.format("run with args: path = %s; file = %s", absolutePath, fileName));
        try {
            Files.find(Paths.get(absolutePath), 1,
                    (path, basicFileAttributes) -> path.toFile().getName().matches(".*" + fileName + ".*"))
                    .forEach(path -> System.out.println(path.toFile().getAbsolutePath()));
        } catch (IOException e) {
            LOGGER.info("Cannot find files", e);
        }
    }
}
