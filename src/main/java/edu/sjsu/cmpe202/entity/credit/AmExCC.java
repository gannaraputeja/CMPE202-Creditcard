package edu.sjsu.cmpe202.entity.credit;

import edu.sjsu.cmpe202.config.CardType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @EqualsAndHashCode(callSuper=false)
public class AmExCC extends CreditCard {

    @Override
    public CardType getCardType(String number) {
        // 1st Digit = 2, 2nd Digit = 4 or 7, length = 15
        if((Integer.parseInt(number.substring(0,1)) == 3) && ((Integer.parseInt(number.substring(1,2)) == 4)
                || (Integer.parseInt(number.substring(1,2)) == 7)) && number.length() == 15) {
            return CardType.AMERICAN_EXPRESS;
        } else {
            if(super.getSuccessor() != null)
                return super.getSuccessor().getCardType(number);
            else
                return CardType.INVALID;
        }
    }

}
