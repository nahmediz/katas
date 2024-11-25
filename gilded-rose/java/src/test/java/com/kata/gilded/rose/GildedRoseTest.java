package com.kata.gilded.rose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @ParameterizedTest
    @MethodSource("getItems")
    @DisplayName("Should update the SellIn value and quality of items based on their specific types")
    void testSellInAndQualityOfItems(Item item, int numberOfDaysToPass, int expectedNewSellInValue,
                                     int expectedQualityValue) {
        // Given
        GildedRose app = new GildedRose(new Item[] { item });

        // When
        for (int i = 0; i < numberOfDaysToPass; i++) {
            app.updateQuality();
        }

        // Then
        assertEquals(expectedNewSellInValue, app.items[0].sellIn);
        assertEquals(expectedQualityValue, app.items[0].quality);
    }

    public static Stream<Arguments> getItems() {
        return Stream.of(
                Arguments.of(new Item("Yogurt", 5, 10), 7, -2, 1),
                        Arguments.of(new Item("Yogurt", 5, 10), 8, -3, 0),
                        Arguments.of(new Item("Aged Brie", 20, 10), 19, 1, 29),
                        Arguments.of(new Item("Aged Brie", 20, 45), 10, 10, 50),
                        Arguments.of(new Item("Backstage passes to a TAFKAL80ETC concert", 20, 10), 19, 1, 42),
                        Arguments.of(new Item("Backstage passes to a TAFKAL80ETC concert", 20, 45), 10, 10, 50),
                        Arguments.of(new Item("Sulfuras, Hand of Ragnaros", 5, 20), 20, 5, 20),
                        Arguments.of(new Item("Conjured Mana Cake", 10, 10), 4, 6, 2)
        );
    }
}
