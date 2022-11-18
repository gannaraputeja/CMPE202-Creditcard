package edu.sjsu.cmpe202.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import edu.sjsu.cmpe202.entity.credit.CreditCardDetail;
import edu.sjsu.cmpe202.entity.credit.CreditCardResponse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UtilTest {


    public static List<CreditCardResponse> readJsonOutputFile(String outputFilename) throws FileNotFoundException {
        JsonReader jsonReader = new JsonReader(new FileReader(outputFilename));
        JsonArray jsonArray = JsonParser.parseReader(jsonReader).getAsJsonArray();
        List<CreditCardResponse> creditCardResponseList = new ArrayList<>();
        for(JsonElement element: jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            CreditCardResponse creditCardResponse = new CreditCardResponse();
            creditCardResponse.setCardNumber(jsonObject.get("cardNumber").getAsString());
            creditCardResponse.setCardType(jsonObject.get("cardType").getAsString());
            creditCardResponseList.add(creditCardResponse);
        }
        return creditCardResponseList;
    }

    public static String readErrorMessage(String outputFilename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(outputFilename));
        String currentLine = reader.readLine();
        reader.close();
        return currentLine;
    }

}
