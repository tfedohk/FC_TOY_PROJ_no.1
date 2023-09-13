package org.example.util;

import org.example.exeption.FileNotExistException;
import org.example.exeption.NotAppropriateOptionException;
import org.example.exeption.NotIntegerException;
import java.io.File;
import java.util.List;

public class Verifier extends Exception {
    private final static int MINIMUM_OPTION_NUMBER = 1;
    public static void validExistenceOf(List<File> files) throws FileNotExistException {
        if (files.isEmpty()) {
            throw new FileNotExistException();
        }
    }
    public static int validOptionFormatIsInteger(String number) throws NotIntegerException{
        try {
            return Integer.parseInt(number);
        }catch (NumberFormatException e){
            throw new NotIntegerException();
        }
    }
    public static void validInOptionRange(int optionNumber, int maximumOptionNumber) throws NotAppropriateOptionException {
        if(optionNumber < MINIMUM_OPTION_NUMBER || optionNumber > maximumOptionNumber){
            throw new NotAppropriateOptionException();
        }
    }
}