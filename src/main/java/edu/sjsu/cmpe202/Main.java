package edu.sjsu.cmpe202;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import edu.sjsu.cmpe202.exception.InvalidFileNameException;
import edu.sjsu.cmpe202.util.Util;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, JAXBException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {


        try {
            if(args.length < 2) {
              Util.writeToFile("output.csv", "Application expects 2 arguments, input filename and output filename.");
            } else if(args[0] != null && args[1] != null
                    && !Util.getFileExtension(args[0]).equals(Util.getFileExtension(args[1]))) {
                Util.writeToFile(args[1], "Input and Output file extensions don't match. " + args[0] + ", " + args[1]);
                //throw new InvalidFileNameException("Input and Output file extensions dont match: " + args[0] + " != " + args[1]);
            } else {
                Client client = new Client();
                client.readInput(args[0]);
                client.processCreditCardDetails();
                client.writeOutput(args[1]);
            }
        } catch (InvalidFileNameException e) {
            Util.writeToFile(args[1], e.getMessage());
        } catch(FileNotFoundException e){
            Util.writeToFile(args[1], e.getMessage());
        }catch(JsonIOException | JsonSyntaxException e) {
            Util.writeToFile(args[1], "Invalid json file " + args[0]);
        }
    }
}