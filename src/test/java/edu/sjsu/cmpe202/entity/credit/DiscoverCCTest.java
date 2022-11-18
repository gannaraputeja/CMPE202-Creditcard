package edu.sjsu.cmpe202.entity.credit;

import edu.sjsu.cmpe202.config.CardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscoverCCTest {

    private DiscoverCC card;

    @BeforeEach
    void setUp() {
        card = new DiscoverCC();
    }

    @Test
    void getCardTypeTest() {
        assertEquals(CardType.DISCOVER, card.getCardType("6011111100007756"));
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