package ru.dohod;

import org.apache.commons.cli.ParseException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.dohod.command.CommandFactory;
import ru.dohod.command.ICommand;

import java.io.IOException;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            ICommand command = CommandFactory.createCommand(args);
            command.run();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            LOGGER.warn("Error while parsing args", e);
        }
    }
}
