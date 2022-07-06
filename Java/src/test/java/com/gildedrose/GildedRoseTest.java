package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void testSulfarasItemNeverChangesItsValue() {
        Item[] items = new Item[] {createItem("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
    }

    private Item createItem(String name, int sellIn, int quality) {
        return new Item(name, sellIn, quality);
    }

}
