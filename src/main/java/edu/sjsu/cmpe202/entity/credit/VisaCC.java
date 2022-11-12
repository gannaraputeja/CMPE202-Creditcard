package edu.sjsu.cmpe202.entity.credit;

import edu.sjsu.cmpe202.config.CardType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @EqualsAndHashCode(callSuper=false)
public class VisaCC extends CreditCard {

    @Override
    public CardType getCardType(String number) {
        // 1st Digit = 4, length = 13 or 16
        if((Integer.parseInt(number.substring(0,1)) == 4) && (number.length() == 13 || number.length() == 16 )) {
            return CardType.VISA;
        } else {
            if(super.getSuccessor() != null)
                return super.getSuccessor().getCardType(number);
            else
                return CardType.INVALID;
        }
    }

}
