package ru.dohod.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
    }
}
