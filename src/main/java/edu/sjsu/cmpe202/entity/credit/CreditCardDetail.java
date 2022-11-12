package edu.sjsu.cmpe202.entity.credit;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CARD")
@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper=false)
public class CreditCardDetail extends CsvToBean {

    @XmlElement(name = "CARD_NUMBER")
    @CsvBindByName(column = "cardNumber")
    private String cardNumber;
    @XmlElement(name = "EXPIRATION_DATE")
    @CsvBindByName(column = "expirationDate")
    private String expirationDate;
    @XmlElement(name = "CARD_HOLDER_NAME")
    @CsvBindByName(column = "cardHolderName")
    private String cardHolderName;

}
