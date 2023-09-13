package org.example.util;

import java.io.File;

public class FolderCreator {
    public static void createFolder(){
        File[] wholeFiles = FileListLoader.getFilesFrom( FolderLocator.getPath());
        System.out.println("FolderLocator.getPath() = " + FolderLocator.getPath());

        if (wholeFiles == null) {
            File resourcesFolder = new File(FolderLocator.getPath());
            if (!resourcesFolder.exists()) {
                resourcesFolder.mkdirs();
            }
            FileListLoader.getFilesFrom(FolderLocator.getPath());
        }
    }

    private FolderCreator(){
    }
}