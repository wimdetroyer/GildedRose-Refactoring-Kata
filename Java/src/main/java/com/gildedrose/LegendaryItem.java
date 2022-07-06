package com.gildedrose;

public class LegendaryItem extends Item {

    public LegendaryItem(String name, int sellIn) {
        super(name, sellIn, 80);
    }

    @Override
    public void updateItem() {
        // Do nothing for legendary items.
    }
}
