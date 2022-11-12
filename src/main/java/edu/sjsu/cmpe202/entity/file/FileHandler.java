package edu.sjsu.cmpe202.entity.file;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import edu.sjsu.cmpe202.entity.credit.CreditCardDetail;
import edu.sjsu.cmpe202.entity.credit.CreditCardResponse;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface FileHandler {

    public List<CreditCardDetail> readInputFile(String inputFileName) throws IOException, JAXBException;
    public void writeOutputFile(String outputFilename, List<CreditCardResponse> creditCardResponseList) throws IOException, JAXBException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException;

}
