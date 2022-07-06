package com.gildedrose;

public abstract class Item {

    private String name;

    private int sellIn;

    private int quality;

    protected Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public abstract void updateItem();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public void decrementSellIn() {
        sellIn = sellIn - 1;
    }

    public boolean isPassedSellByDate() {
        return sellIn < 0;
    }

    public void modifyQualityWithCoefficient(int coefficient) {
        quality = quality + coefficient;
        if(quality > 50) {
            quality = 50;
        }
        if(quality < 0) {
            quality = 0;
        }
    }

}
