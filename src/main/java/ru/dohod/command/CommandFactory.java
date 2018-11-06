package ru.dohod.command;

import org.apache.commons.cli.*;

public final class CommandFactory {

    private static final String READ_DRIVES_ARG_NAME = "readDrives";
    private static final String FIND_FILE_ARG_NAME = "findFile";
    private static final String EDIT_FILE_ARG_NAME = "editFile";
    private static final String ABSOLUTE_PATH_ARG_NAME = "path";
    private static final String FILE_NAME_ARG_NAME = "file";

    public static ICommand createCommand(String[] args) throws ParseException {
        Options options = new Options();
        Option.Builder optionBuilder = Option.builder();
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(optionBuilder.argName(READ_DRIVES_ARG_NAME)
                .longOpt(READ_DRIVES_ARG_NAME)
                .hasArg(false)
                .desc("read local drives and print them").build());
        optionGroup.addOption(optionBuilder.argName(FIND_FILE_ARG_NAME)
                .longOpt(FIND_FILE_ARG_NAME)
                .hasArg(false)
                .desc("find file using absolute path and file name").build());
        optionGroup.addOption(optionBuilder.argName(EDIT_FILE_ARG_NAME)
                .longOpt(EDIT_FILE_ARG_NAME)
                .hasArg(false)
                .desc("add a line to the beginning of each file found").build());
        options.addOptionGroup(optionGroup);
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = commandLineParser.parse(options, args, true);
        if (commandLine.hasOption(READ_DRIVES_ARG_NAME)) {
            return new ReadDrivesCommand();
        }
        boolean isFindCommand = commandLine.hasOption(FIND_FILE_ARG_NAME);
        options = new Options();
        options.addOption(optionBuilder.argName(ABSOLUTE_PATH_ARG_NAME)
                .longOpt(ABSOLUTE_PATH_ARG_NAME).hasArg().required().build());
        options.addOption(optionBuilder.argName(FILE_NAME_ARG_NAME)
                .longOpt(FILE_NAME_ARG_NAME).hasArg().required().build());
        commandLine = commandLineParser.parse(options, commandLine.getArgs());
        String absolutePath = commandLine.getOptionValue(ABSOLUTE_PATH_ARG_NAME);
        String fileName = commandLine.getOptionValue(FILE_NAME_ARG_NAME);
        if (isFindCommand) {
            return new FindFileCommand(absolutePath, fileName);
        }
        return new EditFileCommand(absolutePath, fileName);
    }
}
