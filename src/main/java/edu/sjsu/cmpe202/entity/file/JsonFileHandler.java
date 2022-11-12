package edu.sjsu.cmpe202.entity.file;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import edu.sjsu.cmpe202.entity.credit.CreditCardDetail;
import edu.sjsu.cmpe202.entity.credit.CreditCardResponse;
import lombok.NoArgsConstructor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@NoArgsConstructor
public class JsonFileHandler implements FileHandler {

    @Override
    public List<CreditCardDetail> readInputFile(String inputFileName) throws IOException {
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(new FileReader(inputFileName));
        jsonReader.setLenient(true);
        List<CreditCardDetail> creditCardDetails = gson.fromJson(jsonReader, new TypeToken<List<CreditCardDetail>>() {}.getType());
        return creditCardDetails;
    }

    @Override
    public void writeOutputFile(String outputFileName, List<CreditCardResponse> creditCardResponseList) throws IOException {
        Gson gson = new Gson();
        JsonWriter jsonWriter = new JsonWriter(new FileWriter(outputFileName));
        jsonWriter.setIndent(" ");
        gson.toJson(creditCardResponseList, new TypeToken<List<CreditCardDetail>>() {}.getType(),jsonWriter);
        jsonWriter.close();
    }

}
