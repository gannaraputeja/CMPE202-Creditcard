package edu.sjsu.cmpe202.entity.credit;

import edu.sjsu.cmpe202.config.CardType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @EqualsAndHashCode(callSuper=false)
public class MasterCC extends CreditCard {

    @Override
    public CardType getCardType(String number) {
        // 1st Digit = 5, 1 <= 2nd Digit <=5, length = 16
        if((Integer.parseInt(number.substring(0,1)) == 5) && (Integer.parseInt(number.substring(1,2)) >= 1)
                && (Integer.parseInt(number.substring(1,2)) <= 5) && number.length() == 16) {
            return CardType.MASTER;
        } else {
            if(super.getSuccessor() != null)
                return super.getSuccessor().getCardType(number);
            else
                return CardType.INVALID;
        }
    }
}
