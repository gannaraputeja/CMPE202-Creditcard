package edu.sjsu.cmpe202.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import edu.sjsu.cmpe202.entity.credit.CreditCardDetail;
import edu.sjsu.cmpe202.exception.InvalidFileNameException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public static String getFileExtension(String fileName) throws IOException {
        if(!fileName.contains(".")) {
            throw new InvalidFileNameException("Invalid filename " + fileName);
        }
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        if("".equals(extension) ) {
            throw new InvalidFileNameException("Invalid filename " + fileName);
        }
        return extension;
    }

    public static void writeToFile(String outputFileName, String message) throws IOException {
        FileWriter fileWriter = new FileWriter(outputFileName);
        System.out.println(message);
        fileWriter.write(message);
        fileWriter.close();
    }


}
