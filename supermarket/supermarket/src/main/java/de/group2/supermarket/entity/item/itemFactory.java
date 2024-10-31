package de.group2.supermarket.entity;

public class ItemFactory {

    public static Item createBasicItem(String name, String category, double price) {
        return new Item(name, category, price, true); // isBasic = true
    }

    public static Item createStandardItem(String name, String category, double price) {
        return new Item(name, category, price, false); // isBasic = false
    }
}
