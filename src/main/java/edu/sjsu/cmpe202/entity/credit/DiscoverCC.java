package edu.sjsu.cmpe202.entity.credit;

import edu.sjsu.cmpe202.config.CardType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @EqualsAndHashCode(callSuper=false)
public class DiscoverCC extends CreditCard {

    @Override
    public CardType getCardType(String number) {
        // 1st 4 Digits = 6011, length = 16
        if(Integer.parseInt(number.substring(0,4))  == 6011 && number.length() == 16) {
            return CardType.DISCOVER;
        } else {
            if(super.getSuccessor() != null)
                return super.getSuccessor().getCardType(number);
            else
                return CardType.INVALID;
        }
    }

}