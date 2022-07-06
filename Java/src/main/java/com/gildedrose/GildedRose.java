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
        if (item instanceof NormalItem) {
            item.quality--;
        } else {
            item.quality++;
            if ((item instanceof BackstageItem)) {
                if (item.sellIn < 11) {
                    item.quality++;

                }
                if (item.sellIn < 6) {
                    item.quality++;

                }
            }
        }

        item.sellIn--;

        if (item.sellIn < 0) {
            if (item instanceof AgedBrieItem) {
                item.quality++;
            } else {
                if (item instanceof BackstageItem) {
                    item.quality = 0;
                } else {
                    item.quality--;
                }
            }
        }

        // Post Assertions:
        if (item.quality > 50) {
            item.quality = 50;
        }
        if (item.quality < 0) {
            item.quality = 0;
        }
    }
}
