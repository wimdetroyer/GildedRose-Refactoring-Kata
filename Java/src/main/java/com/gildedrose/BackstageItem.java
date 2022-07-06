package com.gildedrose;

public class BackstageItem extends Item {

    public BackstageItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateItem() {
        decrementSellIn();
        if (isPassedSellByDate()) {
            setQuality(0);
        } else {
            modifyQualityWithCoefficient(calculateBackStageDemand());
        }
    }

    private int calculateBackStageDemand() {
        if (getSellIn() < 5) {
            return 3;
        }
        if (getSellIn() < 10) {
            return 2;
        }
        return 1;
    }

}
