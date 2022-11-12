package edu.sjsu.cmpe202.entity.credit;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CARDS")
@Data @NoArgsConstructor
public class CreditCardResponses {

    @XmlElement(name = "CARD")
    private List<CreditCardResponse> creditCardResponseList;
}
