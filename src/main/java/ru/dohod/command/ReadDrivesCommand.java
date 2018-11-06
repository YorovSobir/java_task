package ru.dohod.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;

public class ReadDrivesCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(ReadDrivesCommand.class);

    public ReadDrivesCommand() {

    }

    public void run() {
        if (System.getProperty("os.name").contains("win")) {
            File[] driveFiles = File.listRoots();
            if (driveFiles != null) {
                for (File aDrive : driveFiles) {
                    System.out.println("Drive: " + aDrive);
                }
            }
        } else {
            try {
                final String command = "lshw -class disk | sed -r '/(logical name)|(description)|(\\*)/!d'";
                String[] cmd = {
                        "/bin/sh",
                        "-c",
                        command
                };
                Process process = Runtime.getRuntime().exec(cmd);
                try (BufferedReader in =
                        new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            } catch (IOException e) {
                LOGGER.error("cannot execute command", e);
            }
        }
    }
}
