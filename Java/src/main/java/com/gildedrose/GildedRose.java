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
        if (!(item instanceof AgedBrieItem)
            && !(item instanceof BackstageItem)) {
            if (item.quality > 0) {
                item.quality = item.quality - 1;

            }
        } else {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if ((item instanceof BackstageItem)) {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }
                }
            }
        }

        item.sellIn = item.sellIn - 1;


        if (item.sellIn < 0) {
            if (!(item instanceof AgedBrieItem)) {
                if (!(item instanceof BackstageItem)) {
                    if (item.quality > 0) {
                        item.quality = item.quality - 1;

                    }
                } else {
                    item.quality = item.quality - item.quality;
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }
    }
}
