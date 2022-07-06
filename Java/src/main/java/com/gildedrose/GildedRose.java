package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQualities() {
        for (Item item : items) {
            if (item instanceof LegendaryItem) {
                continue;
            }
            updateItem(item);
        }
    }

    private void updateItem(Item item) {

        item.sellIn--;
        int qualityCoefficient = 1;

        if (item.sellIn < 0 && item instanceof BackstageItem) {
            item.quality = 0;
            return;
        }

        if ((item instanceof BackstageItem)) {
            if (item.sellIn < 10) {
                qualityCoefficient++;
            }
            if (item.sellIn < 5) {
                qualityCoefficient++;
            }
        }

        if(item instanceof NormalItem) {
            qualityCoefficient *= -1;
        }

        if(item instanceof ConjuredItem) {
            qualityCoefficient *= -2;
        }

        if(item.sellIn < 0) {
            qualityCoefficient *= 2;
        }

        item.quality = item.quality + qualityCoefficient;

        // Post Assertions:
        if (item.quality > 50) {
            item.quality = 50;
        }
        if (item.quality < 0) {
            item.quality = 0;
        }
    }
}
