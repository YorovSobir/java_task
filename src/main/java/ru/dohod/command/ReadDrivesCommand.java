package ru.dohod.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ReadDrivesCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(ReadDrivesCommand.class);

    public ReadDrivesCommand() {

    }

    public void run() {
        LOGGER.info("run");
    }
}
