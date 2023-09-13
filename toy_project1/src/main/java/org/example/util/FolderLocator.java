package org.example.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FolderLocator {
    static final String KEY = "user.dir";
    static final String OS_NAME = System.getProperty("os.name");
    static final String MAC_OS = "Mac OS X";
    static final String WINDOWS_OS = "Windows";
    static final String RELATIVE_PATH = "toy_project1/.results/";

    public static String getPath() {
        Path filePath;
        Path currentPath = Paths.get(System.getProperty(KEY));
        filePath = currentPath.resolve(RELATIVE_PATH);
        if (OS_NAME.equals(MAC_OS)) {
            return filePath + "/";
        } else if (OS_NAME.contains(WINDOWS_OS)) {
            return filePath + "\\";
        }
        return "";
    }

    private FolderLocator(){
    }
}