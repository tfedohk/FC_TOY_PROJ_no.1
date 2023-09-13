package org.example.util;

import org.example.exeption.FileNotExistException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.util.constant.FileNameConstant.*;

public class FileListLoader {
    private static String fileFormat;
    private static final String FILE_NAME_PATTERN = FILENAME_PREFIX + "_(\\d+)\\" + CSV_FILE_FORMAT_SUFFIX;
    private static final String PATTERN_TO_EXTRACT_NUMBER = "\\d+";
    private static final int MINIMUM_NUMBER = 1;
    private static final int MATCHED_GROUP_INDEX = 1;

    private FileListLoader() {
    }

    public static List<File> getJsonFiles(String directoryPath) {
        fileFormat = JSON_FILE_FORMAT_SUFFIX;
        return getFiles(directoryPath);
    }

    public static List<File> getCsvFiles(String directoryPath) {
        fileFormat = CSV_FILE_FORMAT_SUFFIX;
        return getFiles(directoryPath);
    }

    private static List<File> getFiles(String directoryPath) {
        File[] wholeFiles = getFilesFrom(directoryPath);
        List<File> files = new ArrayList<>();

        for (File file : wholeFiles) {
            if (file.isFile() && file.getName().endsWith(fileFormat))
                files.add(file);
        }

        try {
            Verifier.validExistenceOf(files);
        } catch (FileNotExistException e) {
            e.printStackTrace();
        }
        return files;
    }

    public static File[] getFilesFrom(String directoryPath) {
        File directory = new File(directoryPath);
        return directory.listFiles();
    }

    public static int getNumberFromLastFiles(String directoryPath) {
        File[] files = getFilesFrom(directoryPath);

        List<String> fileNames = null;
        if (files != null) {
            fileNames = new ArrayList<>();

            Pattern pattern = Pattern.compile(FILE_NAME_PATTERN);
            for (File file : files) {
                if (file.isFile()) {
                    Matcher matcher = pattern.matcher(file.getName());
                    if (matcher.find()) {
                        fileNames.add(file.getName());
                    }
                }
            }

            fileNames.sort((fileName1, fileName2) -> {
                Matcher matcher1 = pattern.matcher(fileName1);
                Matcher matcher2 = pattern.matcher(fileName2);
                if (matcher1.find() && matcher2.find()) {
                    int number1 = Integer.parseInt(matcher1.group(MATCHED_GROUP_INDEX));
                    int number2 = Integer.parseInt(matcher2.group(MATCHED_GROUP_INDEX));
                    return Integer.compare(number1, number2);
                }
                return fileName1.compareTo(fileName2);
            });
        }

        Pattern patternForExtractedNumber = Pattern.compile(PATTERN_TO_EXTRACT_NUMBER);
        int sizeOfFileNamesList = fileNames.size();
        int numberOfLastFile = 0;
        if (sizeOfFileNamesList >= MINIMUM_NUMBER) {
            Matcher matcherNumberOfLastFile = patternForExtractedNumber.matcher(fileNames.get(fileNames.size() - 1));
            while (matcherNumberOfLastFile.find()) {
                numberOfLastFile = Integer.parseInt(matcherNumberOfLastFile.group());
            }
        }
        return numberOfLastFile;
    }
}