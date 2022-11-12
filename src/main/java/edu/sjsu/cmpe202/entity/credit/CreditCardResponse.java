package edu.sjsu.cmpe202.entity.credit;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CARDS")
@Data @NoArgsConstructor @AllArgsConstructor
public class CreditCardResponse {

    @XmlElement(name = "CARD_NUMBER")
    @CsvBindByName(column = "cardNumber")
    private String cardNumber;
    @XmlElement(name = "CARD_TYPE")
    @CsvBindByName(column = "cardType")
    private String cardType;

    public CreditCardResponse(String cardNumber) {
        this.cardNumber = cardNumber;
    }

}
