package ru.dohod.command;

import net.samuelcampos.usbdrivedetector.USBDeviceDetectorManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Set;

public class ReadDrivesCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(ReadDrivesCommand.class);

    public ReadDrivesCommand() {

    }

    public void run() {
//        FileSystem fs = FileSystems.getDefault();
//
//        for (Path rootPath : fs.getRootDirectories()) {
//            try {
//                FileStore store = Files.getFileStore(rootPath);
//                System.out.println(rootPath + ": " + store.type());
//            }
//            catch (IOException e) {
//                LOGGER.warn(rootPath + ": " + "<error getting store details>", e);
//            }
//        }
        Set<String> drives = new HashSet<>();
        File[] driveFiles = File.listRoots();
        if (driveFiles != null) {
            for (File aDrive : driveFiles) {
                drives.add(aDrive.toString());
            }
        }
        USBDeviceDetectorManager driveDetector = new USBDeviceDetectorManager();
        driveDetector.getRemovableDevices().forEach(drive -> drives.add(drive.toString()));
        drives.forEach(drive -> System.out.println(String.format("Drive: %s", drive)));
    }
}
