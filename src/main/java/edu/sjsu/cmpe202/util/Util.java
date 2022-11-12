package edu.sjsu.cmpe202.util;

import edu.sjsu.cmpe202.exception.InvalidFileNameException;

public class Util {

    public static Boolean isNumber(String cardNumber) {
        if(cardNumber.matches("[0-9]+"))
            return true;
        return false;
    }

    public static Boolean isNullorEmpty(String cardNumber) {
        if(cardNumber == null || "".equals(cardNumber))
            return true;
        return false;
    }

    public static String getFileExtension(String fileName) {
        if(!fileName.contains(".")) {
            throw new InvalidFileNameException("Invalid Filename " + fileName);
        }
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        if("".equals(extension) ) {
            throw new InvalidFileNameException("Invalid Filename " + fileName);
        }
        return extension;
    }


}
