package com.gildedrose;

public class ConjuredItem extends Item {

    public ConjuredItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateItem() {
        decrementSellIn();
        if (isPassedSellByDate()) {
            modifyQualityWithCoefficient(-4);
        } else {
            modifyQualityWithCoefficient(-2);
        }
    }
}
