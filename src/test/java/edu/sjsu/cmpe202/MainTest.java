package edu.sjsu.cmpe202;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import edu.sjsu.cmpe202.util.UtilTest;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final static String error_file = "output.csv";
    private static final String input_json = "src/test/resources/test1_input.json";
    private static final String output_json = "src/test/resources/test1_output.json";
    private static final String invalid_json = "src/test/resources/invalid.json";
    private static final String invalid_output_json = "src/test/resources/invalid_output.json";

    private static final String invalid_xml = "src/test/resources/invalid.xml";
    private static final String invalid_output_xml = "src/test/resources/invalid_output.xml";

    @Test
    void no_arguments_test() throws CsvRequiredFieldEmptyException, JAXBException, CsvDataTypeMismatchException, IOException {
        Main.main(new String[]{});
        assertEquals("Application expects 2 arguments, input filename and output filename."
                , UtilTest.readErrorMessage(error_file));
    }

    @Test
    void file_extensions_dont_match_test() throws CsvRequiredFieldEmptyException, JAXBException, CsvDataTypeMismatchException, IOException {
        Main.main(new String[]{input_json, error_file});
        assertEquals("Input and Output file extensions don't match. " + input_json + ", " + error_file
                , UtilTest.readErrorMessage(error_file));
    }

    @Test
    void invalid_filename_test() throws CsvRequiredFieldEmptyException, JAXBException, CsvDataTypeMismatchException, IOException {
        Main.main(new String[]{"invalid.csv", error_file});
        assertEquals("invalid.csv (No such file or directory)", UtilTest.readErrorMessage(error_file));
    }

    @Test
    void invalid_json_test() throws CsvRequiredFieldEmptyException, JAXBException, CsvDataTypeMismatchException, IOException {
        Main.main(new String[]{invalid_json, invalid_output_json});
        assertEquals("Invalid json file " + invalid_json, UtilTest.readErrorMessage(invalid_output_json));
    }

    @Test
    void invalid_xml_test() throws CsvRequiredFieldEmptyException, JAXBException, CsvDataTypeMismatchException, IOException {
        Main.main(new String[]{invalid_xml, invalid_output_xml});
        assertEquals("Invalid xml file " + invalid_xml, UtilTest.readErrorMessage(invalid_output_xml));
    }


}