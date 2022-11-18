package edu.sjsu.cmpe202;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import edu.sjsu.cmpe202.entity.credit.CreditCardDetail;
import edu.sjsu.cmpe202.entity.credit.CreditCardResponse;
import edu.sjsu.cmpe202.util.UtilTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ClientTest {

    private static Client client;
    private static final String input_json = "src/test/resources/test1_input.json";
    private static final String output_json = "src/test/resources/test1_output.json";

    @BeforeAll
    static void init() {
        client = new Client();
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void readInputTest() throws JAXBException, IOException {
          List<CreditCardDetail> creditCardDetailList = client.readInput(input_json);
          assertEquals(12, creditCardDetailList.size());
          assertEquals("5567894523129089", creditCardDetailList.get(0).getCardNumber());
          assertEquals("08/26", creditCardDetailList.get(0).getExpirationDate());
          assertEquals("John Doe", creditCardDetailList.get(0).getCardHolderName());
    }

    @Test
    void processTest() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<CreditCardDetail> creditCardDetailList = client.readInput(input_json);
        List<CreditCardResponse> creditCardResponseList = client.processCreditCardDetails(creditCardDetailList);
        assertEquals(12, creditCardResponseList.size());
        assertEquals("5567894523129089", creditCardResponseList.get(0).getCardNumber());
        assertEquals("MasterCard", creditCardResponseList.get(0).getCardType());
    }

    @Test
    void writeOutputTest() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<CreditCardDetail> creditCardDetailList = client.readInput(input_json);
        List<CreditCardResponse> creditCardResponseList = client.processCreditCardDetails(creditCardDetailList);
        client.writeOutput(output_json, creditCardResponseList);
        List<CreditCardResponse> creditCardResponses = UtilTest.readJsonOutputFile(output_json);
        assertEquals(12, creditCardResponses.size());
        assertEquals("5567894523129089", creditCardResponses.get(0).getCardNumber());
    }

    @Test
    void cardType_master() {
        List<CreditCardDetail> creditCardDetailList = new ArrayList<>();
        creditCardDetailList.add(new CreditCardDetail("59012345678901234567890", "08/26", "John Doe"));
        List<CreditCardResponse> creditCardResponseList = client.processCreditCardDetails(creditCardDetailList);
        assertEquals("Invalid: more than 19 digits", creditCardResponseList.get(0).getCardType());
    }

    @Test
    void cardNumber_exceeds_19_digits() {
        List<CreditCardDetail> creditCardDetailList = new ArrayList<>();
        creditCardDetailList.add(new CreditCardDetail("59012345678901234567890", "10/24", "Lisa Claire"));
        List<CreditCardResponse> creditCardResponseList = client.processCreditCardDetails(creditCardDetailList);
        assertEquals("Invalid: more than 19 digits", creditCardResponseList.get(0).getCardType());
    }

    @Test
    void cardType_visa() {
        List<CreditCardDetail> creditCardDetailList = new ArrayList<>();
        creditCardDetailList.add(new CreditCardDetail("4123456789123", "04/26", "Martha Clark"));
        List<CreditCardResponse> creditCardResponseList = client.processCreditCardDetails(creditCardDetailList);
        assertEquals("Visa", creditCardResponseList.get(0).getCardType());
    }

    @Test
    void cardType_americanexpress() {
        List<CreditCardDetail> creditCardDetailList = new ArrayList<>();
        creditCardDetailList.add(new CreditCardDetail("347856341908126", "03/23", "Jane S. Dayton"));
        List<CreditCardResponse> creditCardResponseList = client.processCreditCardDetails(creditCardDetailList);
        assertEquals("AmericanExpress", creditCardResponseList.get(0).getCardType());
    }

    @Test
    void cardType_discover() {
        List<CreditCardDetail> creditCardDetailList = new ArrayList<>();
        creditCardDetailList.add(new CreditCardDetail("6011111100007756", "02/24", "John Doe"));
        List<CreditCardResponse> creditCardResponseList = client.processCreditCardDetails(creditCardDetailList);
        assertEquals("Discover", creditCardResponseList.get(0).getCardType());
    }

    @Test
    void invalid_notPossibleCardNumber() {
        List<CreditCardDetail> creditCardDetailList = new ArrayList<>();
        creditCardDetailList.add(new CreditCardDetail("3601112345678789", "06/24", "Lara Wayne"));
        List<CreditCardResponse> creditCardResponseList = client.processCreditCardDetails(creditCardDetailList);
        assertEquals("Invalid: Not a possible card number", creditCardResponseList.get(0).getCardType());
    }

    @Test
    void invalid_non_numeric_characters() {
        List<CreditCardDetail> creditCardDetailList = new ArrayList<>();
        creditCardDetailList.add(new CreditCardDetail("6011*890HJrt6789", "05/25", "Jill Davis"));
        List<CreditCardResponse> creditCardResponseList = client.processCreditCardDetails(creditCardDetailList);
        assertEquals("Invalid: non numeric characters", creditCardResponseList.get(0).getCardType());
    }

    @Test
    void invalid_empty_or_null_cardNumber() {
        List<CreditCardDetail> creditCardDetailList = new ArrayList<>();
        creditCardDetailList.add(new CreditCardDetail("", "08/26", "John Doe"));
        List<CreditCardResponse> creditCardResponseList = client.processCreditCardDetails(creditCardDetailList);
        assertEquals("Invalid: empty/null card number", creditCardResponseList.get(0).getCardType());
    }


}