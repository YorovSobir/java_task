package ru.dohod.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.dohod.data.Drive;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReadDrivesCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(ReadDrivesCommand.class);

    public ReadDrivesCommand() {

    }

    public void run() {
        if (System.getProperty("os.name").contains("Win")) {
            File[] drives = File.listRoots();
            FileSystemView fileSystemView = FileSystemView.getFileSystemView();
            if (drives != null) {
                for (File aDrive : drives) {
                    System.out.println("Drive name: " + aDrive);
                    System.out.println("Description: " + fileSystemView.getSystemTypeDescription(aDrive));
                }
            }
        } else {
            try {
                final String command = "lshw -class disk -json";
                String[] cmd = {
                        "/bin/sh",
                        "-c",
                        command
                };
                Process process = Runtime.getRuntime().exec(cmd);
                Gson gson = new GsonBuilder().create();
                try (Reader reader = new InputStreamReader(process.getInputStream());
                     JsonReader jsonReader = new JsonReader(reader)) {
                    jsonReader.setLenient(true);
                    while (jsonReader.hasNext()) {
                        Drive drive = gson.fromJson(jsonReader, Drive.class);
                        System.out.println("logical name: " + drive.getLogicalname()
                                + "\t" + "description: " + drive.getDescription()
                                + "\t" + "serial: " + drive.getSerial()
                                + "\t" + "Product: " + drive.getProduct());
                        System.out.println("=====================================");
                        if (jsonReader.peek().equals(JsonToken.END_DOCUMENT)) {
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                LOGGER.error("cannot execute command", e);
            }
        }
    }
}
