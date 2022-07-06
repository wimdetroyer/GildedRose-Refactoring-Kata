package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GildedRoseTest {

    // -- Tests for legendary items ---
    @Test
    void testLegendaryItem_alwaysHas80Quality() {
        //Given
        Item[] items = new Item[] {createLegendaryItem()};
        GildedRose app = new GildedRose(items);
        //When
        app.updateQualities();
        //Then
        assertEquals(80, items[0].quality);
    }

    @Test
    void testLegendaryItem_neverDecreasesInSellIn() {
        //Given
        Item[] items = new Item[] {createLegendaryItem()};
        GildedRose app = new GildedRose(items);
        //When
        app.updateQualities();
        //Then
        assertEquals(0, items[0].sellIn);
    }

    // -- Tests for all non legendary items ---
    @Test
    void testAllNonLegendaryItems_neverHaveANegativeQuality() {
        //Given
        Item[] items = new Item[] {createNormalItem(5, 0), createAgedBrieItem(5,0), createBackStageItem(5,0)};
        GildedRose app = new GildedRose(items);
        //When
        app.updateQualities();
        //Then
        for (Item item : items) {
            assertTrue(item.quality >= 0);
        }
    }

    // -- Tests for 'normal' items ---
    @Test
    void testNormalItems_degradeInQualityBeforeSellByDateHasPassed() {
        //Given
        Item[] items = new Item[] {createNormalItem(1, 10)};
        GildedRose app = new GildedRose(items);
        //When
        app.updateQualities();
        //Then
        assertEquals(0, items[0].sellIn);
        assertEquals(9, items[0].quality);
    }

    @Test
    void testNormalItems_degradeTwiceAsFastAfterSellByDateHasPassed() {
        //Given
        Item[] items = new Item[] {createNormalItem(0, 10)};
        GildedRose app = new GildedRose(items);
        //When
        app.updateQualities();
        //Then
        assertEquals(-1, items[0].sellIn);
        assertEquals(8, items[0].quality);
    }

    @Test
    void testBackStageItem_qualityIncreasesByTwoWhenInitialSellInWasTenDaysOrLess() {
        //Given
        Item[] items = new Item[] {createBackStageItem(11, 10)};
        GildedRose app = new GildedRose(items);
        //When
        app.updateQualities();
        //Then (we need this to check the upper bound)
        assertEquals(10, items[0].sellIn);
        assertEquals(11, items[0].quality);
        // Now up until five days it should increase by two...
        for (int i = 1 ; i <= 5 ; i++) {
            //When
            app.updateQualities();
            //Then
            assertEquals(10 - i, items[0].sellIn);
            assertEquals(11 + (i * 2), items[0].quality);
        }
    }

    @Test
    void testBackStageItem_qualityIncreasesByThreeWhenInitialSellInWasFiveDaysOrLess() {
        //Given
        Item[] items = new Item[] {createBackStageItem(5, 12)};
        GildedRose app = new GildedRose(items);

        // Now up until the sell by date  it should increase by three...
        for (int i = 1 ; i <= 5 ; i++) {
            //When
            app.updateQualities();
            //Then
            assertEquals(5 - i, items[0].sellIn);
            assertEquals(12 + (i * 3), items[0].quality);
        }
    }

    // -- Tests for Backstage items ---
    @Test
    void testBackStageItems_qualityIsZeroAfterSellByDateHasPassed() {
        //Given
        Item[] items = new Item[] {createBackStageItem(0, 10)};
        GildedRose app = new GildedRose(items);
        //When
        app.updateQualities();
        //Then
        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    // -- Tests for Brie items ---

    @Test
    void testBrieItem_qualityIncreasesByTwoAfterSellByDateHasPassed() {
        //Given
        Item[] items = new Item[] {createAgedBrieItem(0, 10)};
        GildedRose app = new GildedRose(items);
        //When
        app.updateQualities();
        //Then
        assertEquals(-1, items[0].sellIn);
        assertEquals(12, items[0].quality);
    }


    // -- Tests for Backstage & Brie items ---

    @Test
    void testBackStageAndBrieItems_qualityIncreasesBeforeSellByDateHasPassed() {
        //Given
        Item[] items = new Item[] {createAgedBrieItem(1000, 10), createBackStageItem(1000,10)};
        GildedRose app = new GildedRose(items);
        //When
        app.updateQualities();
        //Then
        for (Item item : items) {
            assertEquals(999, item.sellIn);
            assertEquals(11, item.quality);
        }
    }

    @Test
    void testBackStageAndBrieItems_qualityCanNeverBeHigherThan50() {
        //Given
        Item[] items = new Item[] {createBackStageItem(1, 50), createAgedBrieItem(1, 50)};
        GildedRose app = new GildedRose(items);
        //When
        app.updateQualities();
        //Then
        for (Item item : items) {
            assertEquals(0, item.sellIn);
            assertEquals(50, item.quality);
        }
    }

    private Item createAgedBrieItem(int sellIn, int quality) {
        return new AgedBrieItem("Aged Brie", sellIn, quality);
    }

    private Item createBackStageItem(int sellIn, int quality) {
        return new BackstageItem("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    }

    private Item createLegendaryItem() {
        return new LegendaryItem("Sulfuras, Hand of Ragnaros", 0);
    }

    private Item createNormalItem(int sellIn, int quality) {
        return new NormalItem("Some Item", sellIn, quality);
    }


}
