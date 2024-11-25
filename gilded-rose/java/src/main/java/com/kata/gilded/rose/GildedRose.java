package com.kata.gilded.rose;

class GildedRose {

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            var itemInstance = Items.getItem(item.name);

            int newQuality = itemInstance.getNewQuality(item.sellIn, item.quality);
            int newSellIn = itemInstance.getNewSellIn(item.sellIn);

            item.quality = newQuality;
            item.sellIn = newSellIn;
        }
    }
}
