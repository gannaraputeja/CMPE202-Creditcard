package edu.sjsu.cmpe202.entity.file;

import edu.sjsu.cmpe202.entity.credit.CreditCardDetail;
import edu.sjsu.cmpe202.entity.credit.CreditCardDetails;
import edu.sjsu.cmpe202.entity.credit.CreditCardResponse;
import edu.sjsu.cmpe202.entity.credit.CreditCardResponses;
import lombok.NoArgsConstructor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

@NoArgsConstructor
public class XmlFileHandler implements FileHandler {

    @Override
    public List<CreditCardDetail> readInputFile(String inputFileName) throws JAXBException {
        File file = new File(inputFileName);
        JAXBContext jaxbContext = JAXBContext.newInstance(CreditCardDetails.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        CreditCardDetails creditCardDetails = (CreditCardDetails) jaxbUnmarshaller.unmarshal(file);

        return creditCardDetails.getCreditCardDetailList();
    }

    @Override
    public void writeOutputFile(String outputFileName, List<CreditCardResponse> creditCardResponseList) throws JAXBException {
        File file = new File(outputFileName);
        JAXBContext jaxbContext = JAXBContext.newInstance(CreditCardResponses.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        CreditCardResponses creditCardResponses = new CreditCardResponses();
        creditCardResponses.setCreditCardResponseList(creditCardResponseList);

        jaxbMarshaller.marshal(creditCardResponses, file);
    }

}
