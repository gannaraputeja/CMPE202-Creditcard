package edu.sjsu.cmpe202;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import edu.sjsu.cmpe202.exception.InvalidFileNameException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, JAXBException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

        if(args[0] != null && args[1] != null
                && !args[0].substring(args[0].lastIndexOf(".") + 1).equals(
                args[1].substring(args[1].lastIndexOf(".") + 1))) {
            throw new InvalidFileNameException("Input and Output file extensions dont match: " + args[0] + " != " + args[1]);
        }
        Client client = new Client();
        client.readInput(args[0]);
        client.processCreditCardDetails();
        client.writeOutput(args[1]);
    }
}