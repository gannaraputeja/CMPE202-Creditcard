package edu.sjsu.cmpe202.entity.credit;

import edu.sjsu.cmpe202.config.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public abstract class CreditCard {

    private CreditCard successor;

    private String number;

    public abstract CardType getCardType(String number);

    public static CreditCard link(CreditCard first, CreditCard... creditCards) {
        CreditCard head = first;
        for(CreditCard creditCard: creditCards) {
            head.setSuccessor(creditCard);
            head = creditCard;
        }
        return first;
    }

    public void setSuccessor(CreditCard creditCard) {
        this.successor = creditCard;
    }

    @Override
    public String toString() {
        return this.getClass() + " = [" + getNumber() + "]";
    }
}
