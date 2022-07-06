package com.gildedrose;

public class AgedBrieItem extends Item {

    public AgedBrieItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateItem() {
        decrementSellIn();
        if (isPassedSellByDate()) {
            modifyQualityWithCoefficient(2);
        } else {
            modifyQualityWithCoefficient(1);
        }
    }

}
