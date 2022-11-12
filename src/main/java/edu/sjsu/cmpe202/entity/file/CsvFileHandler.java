package edu.sjsu.cmpe202.entity.file;

import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import edu.sjsu.cmpe202.entity.credit.CreditCardDetail;
import edu.sjsu.cmpe202.entity.credit.CreditCardResponse;
import edu.sjsu.cmpe202.util.AnnotationStrategy;
import lombok.NoArgsConstructor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@NoArgsConstructor
public class CsvFileHandler implements FileHandler {

    @Override
    public List<CreditCardDetail> readInputFile(String inputFileName) throws FileNotFoundException {

        CsvToBean<CreditCardDetail> cb = new CsvToBeanBuilder<CreditCardDetail>(new FileReader(inputFileName))
                .withType(CreditCardDetail.class)
                .build();
        List<CreditCardDetail> creditCardDetailList = cb.parse();
        return creditCardDetailList;
    }

    @Override
    public void writeOutputFile(String outputFileName, List<CreditCardResponse> creditCardResponseList) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        FileWriter fileWriter = new FileWriter(outputFileName);
        HeaderColumnNameMappingStrategy<CreditCardResponse> mappingStrategy = new HeaderColumnNameMappingStrategy<>();
        mappingStrategy.setType(CreditCardResponse.class);

        StatefulBeanToCsv<CreditCardResponse> sbc = new StatefulBeanToCsvBuilder<CreditCardResponse>(fileWriter)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withMappingStrategy(new AnnotationStrategy<>(creditCardResponseList.iterator().next().getClass()))
                .build();
        sbc.write(creditCardResponseList);
        fileWriter.close();

    }
}
