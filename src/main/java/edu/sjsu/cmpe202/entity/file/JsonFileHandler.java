package edu.sjsu.cmpe202.entity.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import edu.sjsu.cmpe202.entity.credit.CreditCardDetail;
import edu.sjsu.cmpe202.entity.credit.CreditCardResponse;
import lombok.NoArgsConstructor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class JsonFileHandler implements FileHandler {

    /*@Override
    public List<CreditCardDetail> readInputFile(String inputFileName) throws IOException {
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(new FileReader(inputFileName));
        jsonReader.setLenient(true);
        List<CreditCardDetail> creditCardDetails = gson.fromJson(jsonReader, new TypeToken<List<CreditCardDetail>>() {}.getType());
        return creditCardDetails;
    }*/

    @Override
    public List<CreditCardDetail> readInputFile(String inputFileName) throws IOException {
        JsonReader jsonReader = new JsonReader(new FileReader(inputFileName));
        JsonArray jsonArray = JsonParser.parseReader(jsonReader).getAsJsonArray();
        List<CreditCardDetail> creditCardDetailList = new ArrayList<>();
        for(JsonElement element: jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            CreditCardDetail creditCardDetail = new CreditCardDetail();
            creditCardDetail.setCardNumber(jsonObject.get("cardNumber").getAsString());
            creditCardDetail.setExpirationDate(jsonObject.get("expirationDate").getAsString());
            creditCardDetail.setCardHolderName(jsonObject.get("cardHolderName").getAsString());
            creditCardDetailList.add(creditCardDetail);
        }
        return creditCardDetailList;
    }

    /*@Override
    public void writeOutputFile(String outputFileName, List<CreditCardResponse> creditCardResponseList) throws IOException {
        Gson gson = new Gson();
        JsonWriter jsonWriter = new JsonWriter(new FileWriter(outputFileName));
        jsonWriter.setIndent(" ");
        gson.toJson(creditCardResponseList, new TypeToken<List<CreditCardDetail>>() {}.getType(),jsonWriter);
        jsonWriter.close();
    }*/

    @Override
    public void writeOutputFile(String outputFileName, List<CreditCardResponse> creditCardResponseList) throws IOException {
        FileWriter fileWriter = new FileWriter(outputFileName);
        try{
            /*JsonArray jsonArray = new JsonArray();
            for(CreditCardResponse creditCardResponse: creditCardResponseList) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.("cardNumber", creditCardResponse.getCardNumber());
            }*/
            ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            String output = objectMapper.writeValueAsString(creditCardResponseList);
            fileWriter.write(output);
        }finally {
            fileWriter.close();
        }
    }


}
