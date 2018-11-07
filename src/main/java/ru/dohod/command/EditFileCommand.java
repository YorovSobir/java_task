package ru.dohod.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EditFileCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(EditFileCommand.class);
    private static final String DEFAULT_LINE = "DEFAULT LINE";
    private String absolutePath;
    private String fileName;

    public EditFileCommand(String absolutePath, String fileName) {
        this.absolutePath = absolutePath;
        this.fileName = fileName;
    }

    public void run() {
        LOGGER.info(String.format("run with args: path = %s; file = %s", absolutePath, fileName));
        try {
            Files.find(Paths.get(absolutePath), 1,
                    (path, basicFileAttributes) -> path.toFile().getName().matches(".*" + fileName + ".*"))
                    .forEach(path -> {
                        System.out.println(path.toFile().getAbsolutePath());
                        try {
                            byte[] buffer = Files.readAllBytes(path);

                            try (OutputStream out = new FileOutputStream(path.toFile())) {
                                out.write(DEFAULT_LINE.getBytes());
                                out.write(System.lineSeparator().getBytes());
                                out.write(buffer);
                            }
                        } catch (IOException e) {
                            LOGGER.error("Unexpected error", e);
                        }
                    });
        } catch (IOException e) {
            LOGGER.info("Cannot find files", e);
        }
    }
}
