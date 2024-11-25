package com.kata.gilded.rose;

/**
 * The enumeration of items.
 */
public enum Items {

    SULFURAS("Sulfuras, Hand of Ragnaros") {
        @Override
        public int getNewQuality(int sellIn, int quality) {
            return quality;
        }

        @Override
        public int getNewSellIn(int sellIn) {
            return sellIn;
        }
    },
    AGED_BRIE("Aged Brie") {
        @Override
        public int getNewQuality(int sellIn, int quality) {
            return quality < 50 ? quality + 1 : 50;
        }
    },
    BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert") {
        @Override
        public int getNewQuality(int sellIn, int quality) {
            if (sellIn < 0) {
                return 0;
            } else if (sellIn < 6) {
                return Math.min(quality + 3, 50);
            } else if (sellIn < 11) {
                return Math.min(quality + 2, 50);
            } else {
                return Math.min(quality + 1, 50);
            }
        }
    },
    CONJURED("Conjured Mana Cake") {
        @Override
        public int getNewQuality(int sellIn, int quality) {
            int qualityToRemove = sellIn <= 0 ? 4 : 2;
            return Math.max(quality - qualityToRemove, 0);
        }
    },
    DEFAULT("default");

    /**
     * The item's name.
     */
    private final String name;

    /**
     * The constructor.
     * @param name the item's name.
     */
    Items(String name) {
        this.name = name;
    }

    /**
     * Get the new item's quality based on its current sellIn and quality values.
     * @param sellIn the current sellIn value.
     * @param quality the current quality.
     * @return the new quality.
     */
    public int getNewQuality(int sellIn, int quality) {
        int qualityToRemove = sellIn <= 0 ? 2 : 1;
        return Math.max(quality - qualityToRemove, 0);
    }

    /**
     * Get the new sellIn value based on the old one.
     * @param sellIn the old sellIn value.
     * @return the new sellIn value.
     */
    public int getNewSellIn(int sellIn) {
        return sellIn - 1;
    }

    /**
     * Get an item based on its name.
     * @param name the item's name.
     * @return the items' instance if found, a default instance otherwise.
     */
    public static Items getItem(String name) {
        for (Items item : Items.values()) {
            if (item.name.equals(name)) {
                return item;
            }
        }

        return DEFAULT;
    }
}
