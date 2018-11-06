package ru.dohod;

import org.apache.commons.cli.ParseException;
import ru.dohod.command.CommandFactory;
import ru.dohod.command.ICommand;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParseException, IOException {
        ICommand command = CommandFactory.createCommand(args);
        command.run();
    }
}
