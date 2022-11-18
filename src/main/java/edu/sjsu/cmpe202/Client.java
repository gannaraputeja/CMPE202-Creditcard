package edu.sjsu.cmpe202;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import edu.sjsu.cmpe202.config.CardType;
import edu.sjsu.cmpe202.entity.credit.*;
import edu.sjsu.cmpe202.entity.file.FileHandler;
import edu.sjsu.cmpe202.factory.FileHandlerFactory;
import edu.sjsu.cmpe202.util.Util;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private static CreditCard creditCard;

    public Client() {
        creditCard = CreditCard.link(new MasterCC(), new VisaCC(), new AmExCC(), new DiscoverCC());
    }

    public List<CreditCardDetail> readInput(String inputFileName) throws IOException, JAXBException {

        List<CreditCardDetail> creditCardDetailList;
        String extension = Util.getFileExtension(inputFileName);
        FileHandler fileHandler = FileHandlerFactory.getFileHandler(extension);

        creditCardDetailList = fileHandler.readInputFile(inputFileName);

        return creditCardDetailList;
    }

    public List<CreditCardResponse> processCreditCardDetails(List<CreditCardDetail> creditCardDetailList) {
        List<CreditCardResponse> creditCardResponseList = new ArrayList<>();
        for(CreditCardDetail creditCardDetail : creditCardDetailList) {
            CreditCardResponse creditCardResponse = new CreditCardResponse(creditCardDetail.getCardNumber());
            if(Util.isNullorEmpty(creditCardDetail.getCardNumber())){
                creditCardResponse.setCardType("Invalid: empty/null card number");
            } else if (!Util.isNumber(creditCardDetail.getCardNumber())) {
                creditCardResponse.setCardType("Invalid: non numeric characters");
            } else if(creditCardDetail.getCardNumber().length() > 19) {
                creditCardResponse.setCardType("Invalid: more than 19 digits");
            } else {

                CardType cardType = creditCard.getCardType(creditCardDetail.getCardNumber());

                switch (cardType) {
                    case MASTER:
                        creditCardResponse.setCardType("MasterCard");
                        break;
                    case VISA:
                        creditCardResponse.setCardType("Visa");
                        break;
                    case AMERICAN_EXPRESS:
                        creditCardResponse.setCardType("AmericanExpress");
                        break;
                    case DISCOVER:
                        creditCardResponse.setCardType("Discover");
                        break;
                    default:
                        creditCardResponse.setCardType("Invalid: Not a possible card number");
                }
            }
            creditCardResponseList.add(creditCardResponse);
        }
        return creditCardResponseList;
    }

    public void writeOutput(String outputFilename, List<CreditCardResponse> creditCardResponseList) throws IOException, JAXBException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String extension = Util.getFileExtension(outputFilename);

        FileHandler fileHandler = FileHandlerFactory.getFileHandler(extension);

        fileHandler.writeOutputFile(outputFilename, creditCardResponseList);
    }

}
