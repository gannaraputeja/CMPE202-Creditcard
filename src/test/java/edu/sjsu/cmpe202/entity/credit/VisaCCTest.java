package edu.sjsu.cmpe202.entity.credit;

import edu.sjsu.cmpe202.config.CardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VisaCCTest {

    private VisaCC card;

    @BeforeEach
    void setUp() {
        card = new VisaCC();
    }

    @Test
    void getCardTypeTest() {
        assertEquals(CardType.VISA, card.getCardType("4123456789123"));
    }

    @Test
    void invalidCardTypeTest() {
        assertEquals(CardType.INVALID, card.getCardType("59012345678901234567890"));
    }

    @Test
    void notPossibleCardTypeTest() {
        assertEquals(CardType.INVALID, card.getCardType("3601112345678789"));
    }

}